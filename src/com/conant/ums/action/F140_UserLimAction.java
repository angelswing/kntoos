package com.conant.ums.action;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;
import com.conant.ums.data.*;
import com.conant.ums.form.F140_UserLimForm;
import com.conant.ums.lbean.F140_UserLimLBean;
import com.conant.ums.util.*;

public class F140_UserLimAction
    extends BaseAction {
    public F140_UserLimAction() {
    }

    public String executeAct(Connection con,
                             ActionMapping actionMapping,
                             ActionForm actionForm,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws
        Exception {
        /**@todo: complete the business logic here, this is just a skeleton.*/

        F140_UserLimForm piForm = (F140_UserLimForm) actionForm;
        HttpSession session = httpServletRequest.getSession(true);
        LoginData loginData = (LoginData) session.getAttribute("LoginData");

        int iRet = 0;
        String sForward = piForm.getForward();
        int iRettime = 0;
        int iRetip = 0;

        if (piForm.getOp().equals("update") == true) {
            String[] limitedtime = httpServletRequest.getParameterValues(
                "limitedtime");
            String[] limitedip = httpServletRequest.getParameterValues(
                "limitedip");

            F140_UserLimLBean updLBean = new F140_UserLimLBean();
            F140_UserLim userData = new F140_UserLim();
            try {
                BeanUtils.copyProperties(userData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            userData.setUpd_userid(loginData.getUser_tag());

            iRet = updLBean.updateData(con, userData);
            if (iRet < 0) {
                httpServletRequest.setAttribute("message", "com.update.fail");
                httpServletRequest.setAttribute("title",
                                                "com.title.xaexception");
                return "result_display";
            }
            else {
                iRettime = updLBean.deleteTimeGroup(con, piForm.getUser_id());
                if (piForm.getTimetype().equals("") == false && limitedtime != null) {
                    iRettime = updLBean.insertTimeGroup(con, piForm.getUser_id(),
                        limitedtime);
                    if (iRettime < 0) {
                        httpServletRequest.setAttribute("message",
                            "com.update.fail");
                        httpServletRequest.setAttribute("title",
                            "com.title.xaexception");
                        return "result_display";
                    }
                }

                iRetip = updLBean.deleteIpGroup(con, piForm.getUser_id());
                if (userData.getIptype().equals("") == false && limitedip != null) {
                    iRetip = updLBean.insertIpGroup(con, piForm.getUser_id(),
                        limitedip);
                    if (iRetip < 0) {
                        httpServletRequest.setAttribute("message",
                            "com.update.fail");
                        httpServletRequest.setAttribute("title",
                            "com.title.xaexception");
                        return "result_display";
                    }
                }
            }

            getAllOptions(con, piForm);
        }

        else if (piForm.getOp().equals("select") == true) {

            F140_UserLimLBean pnLBean = new F140_UserLimLBean();
            F140_UserLim pnLData = new F140_UserLim();
            try {
                BeanUtils.copyProperties(pnLData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            if (piForm.getSortCol() == null || piForm.getSortCol().equals("")) {
                piForm.setSortCol("user_id");
                piForm.setSortOrder("1");
            }

            List selectresult = pnLBean.selectRecord(con, pnLData,
                piForm.getSortCol(), piForm.getSortOrder());

            PageUpDown pageUpDown = new PageUpDown(piForm.getCurPageNo());
            selectresult = pageUpDown.getPageSet(selectresult);
            piForm.setSelectResult(selectresult);

            piForm.generatePageInfo(pageUpDown);

        }

        else if (piForm.getOp().equals("selectupdinfo") == true) {

            F140_UserLimLBean pnLBean = new F140_UserLimLBean();
            F140_UserLim pnLData = new F140_UserLim();
            try {
                BeanUtils.copyProperties(pnLData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            if (piForm.getSortCol() == null || piForm.getSortCol().equals("")) {
                piForm.setSortCol("user_id");
                piForm.setSortOrder("1");
            }

            List selectresult = pnLBean.selectRecord(con, pnLData,
                piForm.getSortCol(), piForm.getSortOrder());
            if (selectresult.size() <= 0) {
                httpServletRequest.setAttribute("message", "com.select.fail");
                sForward = ComGlobal.RESULT;
            }
            else {
                pnLData = (F140_UserLim) selectresult.get(0);
                piForm.setUser_id(pnLData.getUser_id());
                piForm.setTimetype(pnLData.getTimetype());
                piForm.setIptype(pnLData.getIptype());
                piForm.setLock_flag(pnLData.getLock_flag());

                getAllOptions(con, piForm);
            }
        }

        else if (piForm.getOp().equals("lockonupdate") == true) {
            F140_UserLimLBean updLBean = new F140_UserLimLBean();
            iRet = updLBean.lockonupdate(con, piForm.getUser_id());
            if (iRet <= 0) {
                httpServletRequest.setAttribute("message", "com.update.fail");
                httpServletRequest.setAttribute("title",
                                                "com.title.xaexception");
                return "result_display";
            }
            piForm.setUser_id(piForm.getUser_id());
            piForm.setTimetype(piForm.getTimetype());
            piForm.setIptype(piForm.getIptype());
            piForm.setLock_flag(piForm.getLock_flag());

            getAllOptions(con, piForm);
        }

        else if (piForm.getOp().equals("lockoffupdate") == true) {
            F140_UserLimLBean updLBean = new F140_UserLimLBean();
            iRet = updLBean.lockoffupdate(con, piForm.getUser_id());
            if (iRet <= 0) {
                httpServletRequest.setAttribute("message", "com.update.fail");
                httpServletRequest.setAttribute("title",
                                                "com.title.xaexception");
                return "result_display";
            }
            piForm.setUser_id(piForm.getUser_id());
            piForm.setTimetype(piForm.getTimetype());
            piForm.setIptype(piForm.getIptype());
            piForm.setLock_flag(piForm.getLock_flag());

            getAllOptions(con, piForm);
        }

        return sForward;
    }

    private F140_UserLimForm getAllOptions(Connection oConn,
                                           F140_UserLimForm prm_Form) throws
        Exception {
        F140_UserLimLBean usLBean = new F140_UserLimLBean();
        List pargroup = usLBean.getParOptions(oConn);
        prm_Form.setParOptions(pargroup);

        List limitTimeGroup = usLBean.getLimitTimeListOptions(oConn,
            prm_Form.getUser_id());
        prm_Form.setLimitTimeGroup(limitTimeGroup);

        List limitIpGroup = usLBean.getLimitIpListOptions(oConn,
            prm_Form.getUser_id());
        prm_Form.setLimitIpGroup(limitIpGroup);

        return prm_Form;
    }

}
