package com.conant.ums.action;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.*;
import org.apache.commons.logging.Log;
import org.apache.struts.action.*;
import com.conant.ums.data.LoginData;
import com.conant.ums.db.DbAccess;
import com.conant.ums.exception.XAException;
import com.conant.ums.form.InfoForm;
import com.conant.ums.util.*;

public abstract class BaseAction
    extends Action {
    private static Config funclog_cfg = new Config("functionlog");
    public Log log, specialLog;
    private String filePathKey = "default";
    public BaseAction() {
        log = LogFactory.getLog(this.getClass());
        specialLog = LogFactory.getLog("specialLogger");
    }

    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) {
        /**@todo: complete the business logic here, this is just a skeleton.*/
        //throw new java.lang.UnsupportedOperationException("Method perform() not yet implemented.");

        //根据配置文件，确定是否记录流水
        String sFlag = funclog_cfg.getValue(this.getClass().getName());
        if (sFlag != null && sFlag.equals("yes")) {
            functionLog(actionForm, httpServletRequest);
        }
        Connection oConn = init();
        String sForward = null;
        try {
            log.info("in execute connection is " + oConn + oConn.isClosed());
            sForward = executeAct(oConn, actionMapping, actionForm,
                                  httpServletRequest,
                                  httpServletResponse);
        }
        catch (XAException e) {
            //业务异常处理
            sForward = ComGlobal.EXCEPTION;
            log.error("Action execute error " + e.getMessage());
            e.printStackTrace();
            rollback(oConn);
            InfoForm iForm = new InfoForm();
            iForm.setTitle(BytesConverter.asc2gb(GetMsg.getMsg(
                "com.title.xaexception")));
            iForm.setMessage(e.getMessage());
            httpServletRequest.setAttribute("InfoForm", iForm);
        }
        catch (Exception e) {
            //系统异常处理
            HttpSession session = httpServletRequest.getSession(true);
            LoginData loginData = (LoginData) session.getAttribute("LoginData");
            InfoForm iForm = new InfoForm();
            if (loginData == null) {
                iForm.setTitle(BytesConverter.asc2gb(GetMsg.getMsg(
                    "com.title.timeout")));
                sForward = ComGlobal.TIMEOUT;
            }
            else {
                iForm.setTitle(BytesConverter.asc2gb(GetMsg.getMsg(
                    "com.title.exception")));
                sForward = ComGlobal.EXCEPTION;
            }
            log.error("Action execute error " + e.getMessage());
            e.printStackTrace();
            rollback(oConn);
            iForm.setMessage(e.getMessage());
            httpServletRequest.setAttribute("InfoForm", iForm);
        }
        finally {
            close(oConn);
        }
        return actionMapping.findForward(sForward);
    }

    //执行Action初始化动作（获取数据库连接）
    private Connection init() {
        Connection oConn = DbAccess.getConnect("");

        try {
            log.debug("in init connection is " + oConn + oConn.isClosed());
            oConn.setAutoCommit(false);
        }
        catch (SQLException e) {
            log.error(e.getMessage());
            //throw new SQLException ("QUERY ERR:"+sql);
        }
        log.debug("connection is " + oConn);
        return oConn;
    }

    //执行Action收尾动作（关闭数据库连接等）
    private void close(Connection oConn) {
        try {
            if (oConn != null) {
                oConn.commit();
                oConn.close();
            }
        }
        catch (SQLException e) {
            log.error("connection close error: " + e.getMessage());
        }
    }

    //执行数据库回滚操作
    private void rollback(Connection oConn) {
        if (oConn != null) {
            try {
                oConn.rollback();
            }
            catch (SQLException ex) {
                log.error("Action rollback error " + ex.getMessage());
            }
        }
    }

    //获取数据库连接
    protected Connection getConnect() {
        Connection oConn = null;
        oConn = DbAccess.getConnect("");
        return oConn;
    }

    public abstract String executeAct(Connection oConn,
                                      ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws
        Exception;

    public String getFilePathKey() {
        return filePathKey;
    }

    public void setFilePathKey(String filePathKey) {
        this.filePathKey = filePathKey;
    }

    //记录流水
    private void functionLog(ActionForm actionForm,
                             HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(true);
        LoginData loginData = (LoginData) session.getAttribute("LoginData");
        try {
            Map propMap = BeanUtils.describe(actionForm);
            specialLog.info("--------------begin log function--------------");
            specialLog.info("user id is ====== " + loginData.getUserTag());
            specialLog.info("user name is ===== " + loginData.getUserName());
            for (Iterator it = propMap.keySet().iterator(); it.hasNext(); ) {
                //遍历form所有属性
                Object key = it.next();
                specialLog.info(key + " = " + propMap.get(key));
            }
            specialLog.info("--------------function log end--------------");
        }
        catch (Exception e) {
            specialLog.error("print function log error " + e.getMessage());
            e.printStackTrace();
        }
    }
}
