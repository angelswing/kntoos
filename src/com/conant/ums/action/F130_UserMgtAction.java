package com.conant.ums.action;

import java.sql.Connection;
import java.util.*;

import javax.servlet.http.*;

import com.conant.ums.data.*;
import com.conant.ums.form.F130_UserMgtForm;
import com.conant.ums.lbean.*;
import com.conant.ums.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;

public class F130_UserMgtAction
    extends BaseAction {
    public F130_UserMgtAction() {
    }

    public String executeAct(Connection con,
                             ActionMapping actionMapping,
                             ActionForm actionForm,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws
        Exception {
        /**@todo: complete the business logic here, this is just a skeleton.*/

        F130_UserMgtForm piForm = (F130_UserMgtForm) actionForm;
        HttpSession session = httpServletRequest.getSession(true);
        LoginData loginData = (LoginData) session.getAttribute("LoginData");

        int iRet = 0;
        String sForward = piForm.getForward();
        int iRetrole = 0;
        int iRetdept = 0;

        if (piForm.getOp().equals("insert") == true) {
            String[] userrole = httpServletRequest.getParameterValues(
                "userrole");

            F130_UserLBean addLBean = new F130_UserLBean();
            F130_UserMgt addData = new F130_UserMgt();
            try {
                BeanUtils.copyProperties(addData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            //如果密码为空,则初始密码为"111111"
            if(null==addData.getUser_passwd()||("").equals(addData.getUser_passwd())){
                addData.setUser_passwd("111111");
            }

            String sUser_id = addLBean.getMaxUserid(con);

            if (addLBean.isExist(con, addData.getUser_tag())) {
                httpServletRequest.setAttribute("message", "com.user.duplicate");
                httpServletRequest.setAttribute("title",
                                                "com.title.prompt");
                return "result_display";
            }
            else {
                addData.setUser_id(sUser_id);
                addData.setAdd_userid(loginData.getUser_tag());

                iRetdept = addLBean.updateDept(con, addData);
                if (iRetdept < 0) {
                    httpServletRequest.setAttribute("message",
                        "com.update.fail");
                    httpServletRequest.setAttribute("title",
                        "com.title.xaexception");
                    return "result_display";
                }

                iRet = addLBean.insertData(con, addData);
                //clearForm(piForm);
                if (iRet < 0) {
                    httpServletRequest.setAttribute("message", "com.add.fail");
                    httpServletRequest.setAttribute("title",
                        "com.title.xaexception");
                    return "result_display";
                }
                else if (userrole != null) {
                    iRetrole = addLBean.insertRole(con, sUser_id, userrole);
                    if (iRetrole < 0) {
                        httpServletRequest.setAttribute("message",
                            "com.add.fail");
                        httpServletRequest.setAttribute("title",
                            "com.title.xaexception");
                        return "result_display";
                    }
                }
                //clearForm(piForm);
            }
            clearForm(piForm);
            getAllOptions(con, piForm);
            getUserRoleOptions(con, piForm);
        }

        else if (piForm.getOp().equals("update") == true) {
            String[] userrole = httpServletRequest.getParameterValues(
                "userrole");

            F130_UserLBean updLBean = new F130_UserLBean();
            F130_UserMgt userData = new F130_UserMgt();
            try {
                BeanUtils.copyProperties(userData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            userData.setUpd_userid(loginData.getUser_tag());
            iRetdept = updLBean.updateDept(con, userData);
            if (iRetdept < 0) {
                httpServletRequest.setAttribute("message", "com.update.fail");
                httpServletRequest.setAttribute("title",
                                                "com.title.xaexception");
                return "result_display";
            }

            iRet = updLBean.updateData(con, userData);
            if (iRet < 0) {
                httpServletRequest.setAttribute("message", "com.update.fail");
                httpServletRequest.setAttribute("title",
                                                "com.title.xaexception");
                return "result_display";
            }
            else {
                iRetrole = updLBean.updateRole(con, piForm.getUser_id(),
                                               userrole);
                if (iRetrole < 0) {
                    httpServletRequest.setAttribute("message",
                        "com.update.fail");
                    httpServletRequest.setAttribute("title",
                        "com.title.xaexception");
                    return "result_display";
                }
            }
            getAllOptions(con, piForm);
            piForm.setUser_tag(BytesConverter.asc2gb(userData.getUser_tag()));
            piForm.setUser_name(BytesConverter.asc2gb(userData.getUser_name()));
            piForm.setAddress(BytesConverter.asc2gb(userData.getAddress()));
            getUserRoleOptions(con, piForm);
        }

        else if (piForm.getOp().equals("delete") == true) {
            F130_UserLBean delLBean = new F130_UserLBean();
            F130_UserMgt pnLData = new F130_UserMgt();

            try {
                BeanUtils.copyProperties(pnLData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            if (piForm.getSortCol() == null || piForm.getSortCol().equals("")) {
                piForm.setSortCol("user_tag");
                piForm.setSortOrder("0");
            }

            iRet = delLBean.deleteData(con, piForm.getSelecteduser_id());
            if (iRet < 0) {
                httpServletRequest.setAttribute("message", "com.delete.fail");
                httpServletRequest.setAttribute("title",
                                                "com.title.xaexception");
                return "result_display";
            }
            else {
                piForm.setSelecteduser_id("");
                List selectresult = delLBean.selectRecord(con, pnLData,
                    piForm.getSortCol(), piForm.getSortOrder());
                PageUpDown pageUpDown = new PageUpDown(piForm.getCurPageNo());
                selectresult = pageUpDown.getPageSet(selectresult);
                piForm.setSelectResult(selectresult);
                piForm.generatePageInfo(pageUpDown);
            }
        }

        else if (piForm.getOp().equals("select") == true) {

            F130_UserLBean pnLBean = new F130_UserLBean();
            F130_UserMgt pnLData = new F130_UserMgt();
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

            List selectresult = new ArrayList();
            if(null == piForm.getDeptid() || ("").equals(piForm.getDeptid()) ){
                selectresult = pnLBean.selectRecord(con, pnLData,
                        piForm.getSortCol(), piForm.getSortOrder());
            }else{
                selectresult = pnLBean.selectRecordSelect(con, pnLData);
            }
            PageUpDown pageUpDown = new PageUpDown(piForm.getCurPageNo());
            selectresult = pageUpDown.getPageSet(selectresult);
            piForm.setSelectResult(selectresult);

            piForm.generatePageInfo(pageUpDown);

        }

        else if (piForm.getOp().equals("selectoptions") == true) {
            piForm.setDeptid("");
            getAllOptions(con, piForm);

            piForm.setUser_id("");
            getUserRoleOptions(con, piForm);
        }

        else if (piForm.getOp().equals("selectupdinfo") == true) {

            F130_UserLBean pnLBean = new F130_UserLBean();
            F130_UserMgt pnLData = new F130_UserMgt();
            try {
                BeanUtils.copyProperties(pnLData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            if (piForm.getSortCol() == null || piForm.getSortCol().equals("")) {
                piForm.setSortCol("user_tag");
                piForm.setSortOrder("0");
            }

            List selectresult = pnLBean.selectRecord(con, pnLData,
                piForm.getSortCol(), piForm.getSortOrder());
            if (selectresult.size() <= 0) {
                httpServletRequest.setAttribute("message", "com.select.fail");
                sForward = ComGlobal.RESULT;
            }
            else {
                piForm.setSelectResult(selectresult);
                pnLData = (F130_UserMgt) selectresult.get(0);
                piForm.setUser_id(pnLData.getUser_id());
                piForm.setUser_tag(pnLData.getUser_tag());
                piForm.setUser_name(pnLData.getUser_name());
                piForm.setEmail(pnLData.getEmail());
                piForm.setAddress(pnLData.getAddress());
                piForm.setHome_tel(pnLData.getHome_tel());
                piForm.setMobile(pnLData.getMobile());
                piForm.setDeptid(pnLData.getDeptid());

                getAllOptions(con, piForm);
                getUserRoleOptions(con, piForm);
            }
        }

        else if (piForm.getOp().equals("selectchangerole") == true) {
            String userName = new String(piForm.getUser_name().getBytes("ISO-8859-1"), "gb2312");
            String address = new String(piForm.getAddress().getBytes("ISO-8859-1"), "gb2312");

            piForm.setUser_name(userName);
            piForm.setAddress(address);

            getAllOptions(con, piForm);
            getUserRoleOptions(con, piForm);
        }

        return sForward;
    }

    //解决当数据更新、提交时，输入框会出现短暂的乱码显示
    private F130_UserMgtForm clearForm(F130_UserMgtForm piForm) {
        piForm.setUser_tag("");
        piForm.setUser_name("");
        piForm.setAddress("");

        return piForm;
    }

    private F130_UserMgtForm getAllOptions(Connection oConn,
                                           F130_UserMgtForm prm_Form) throws
        Exception {

        F150_DeptMgtLBean dnLBean = new F150_DeptMgtLBean();
        List dept_list = dnLBean.getDeptListOptions(oConn);
        prm_Form.setDeptOptions(dept_list);

        if ( (prm_Form.getDeptid() == "") || (prm_Form.getDeptid().equals(""))) {
            F110_RoleMgtLBean pnLBean = new F110_RoleMgtLBean();
            List role_list = pnLBean.getRoleListOptions(oConn);
            prm_Form.setDeptRoleGroup(role_list);
        }
        else {
            F130_UserLBean usLBean = new F130_UserLBean();
            List deptrolegroup = usLBean.getDeptRoleListOptions(oConn,
                prm_Form.getDeptid());
            prm_Form.setDeptRoleGroup(deptrolegroup);
        }

        return prm_Form;
    }

    private F130_UserMgtForm getUserRoleOptions(Connection oConn,
                                                F130_UserMgtForm prm_Form) throws
        Exception {
        F130_UserLBean usLBean = new F130_UserLBean();
        List userrolegroup = usLBean.getUserRoleListOptions(oConn,
            prm_Form.getUser_id());
        prm_Form.setUserRoleGroup(userrolegroup);

        return prm_Form;
    }

}
