package com.conant.ums.action;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;
import com.conant.ums.data.*;
import com.conant.ums.form.F110_RoleMgtForm;
import com.conant.ums.lbean.F110_RoleMgtLBean;
import com.conant.ums.util.*;

public class F110_RoleMgtAction
    extends BaseAction {
    public String executeAct(Connection oConn,
                             ActionMapping actionMapping,
                             ActionForm actionForm,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws
        Exception {

        F110_RoleMgtForm piForm = (F110_RoleMgtForm) actionForm;
        HttpSession session = httpServletRequest.getSession(true);
        LoginData loginData = (LoginData) session.getAttribute("LoginData");
        int iRet = 0;
        int iRetrole = 0;
        boolean delUserRole;
        boolean delDeptRole;
        String sForward = piForm.getForward();

        if (piForm.getOp().equals("select") == true) {

            F110_RoleMgtLBean addLBean = new F110_RoleMgtLBean();
            F110_RoleMgt selData = new F110_RoleMgt();
            try {
                BeanUtils.copyProperties(selData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            List selectresult = addLBean.SelectData(oConn, selData);

            PageUpDown pageUpDown = new PageUpDown(piForm.getCurPageNo());
            selectresult = pageUpDown.getPageSet(selectresult);
            piForm.setSelectResult(selectresult);

            piForm.generatePageInfo(pageUpDown);

        }
        else if (piForm.getOp().equals("selectoptions") == true) {
            getAllOptions(oConn, piForm);
        }
        else if (piForm.getOp().equals("selectupdinfo") == true) {

            F110_RoleMgtLBean addLBean = new F110_RoleMgtLBean();
            F110_RoleMgt selData = new F110_RoleMgt();
            try {
                BeanUtils.copyProperties(selData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            List selectresult = addLBean.SelectData(oConn, selData);
            if (selectresult.size() <= 0) {
                httpServletRequest.setAttribute("message", "com.select.fail");
                sForward = ComGlobal.RESULT;
            }
            else {
                piForm.setSelectResult(selectresult);
                selData = (F110_RoleMgt) selectresult.get(0);
                piForm.setRole_id(selData.getRole_id());
                piForm.setRole_name(selData.getRole_name());
                piForm.setRole_depict(selData.getRole_depict());

                getAllOptions(oConn, piForm);
            }

        }
        else if (piForm.getOp().equals("insert") == true) {
            String[] parentrolelist = httpServletRequest.getParameterValues(
                "parentrolelist");

            F110_RoleMgtLBean addLBean = new F110_RoleMgtLBean();
            F110_RoleMgt addData = new F110_RoleMgt();
            try {
                BeanUtils.copyProperties(addData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            if (addLBean.isExist(oConn, addData.getRole_name())) {
                sForward = ComGlobal.RESULT;
                httpServletRequest.setAttribute("title", "com.title.prompt");
                httpServletRequest.setAttribute("message", "com.role.duplicate");
                return sForward;
            }
            else {
                addData.setAdd_userid(loginData.getUserTag());
                iRet = addLBean.insert(oConn, addData);
                if (iRet <= 0) {
                    httpServletRequest.setAttribute("message", "com.add.fail");
                    sForward = ComGlobal.RESULT;
                }
            }
            clearForm(piForm);
            getAllOptions(oConn, piForm);

        }
        else if (piForm.getOp().equals("update") == true) {
            F110_RoleMgtLBean updLBean = new F110_RoleMgtLBean();
            F110_RoleMgt updData = new F110_RoleMgt();
            try {
                BeanUtils.copyProperties(updData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            if (updLBean.isUpdateExist(oConn, updData.getRole_id(),
                                       updData.getRole_name())) {
                sForward = ComGlobal.RESULT;
                httpServletRequest.setAttribute("title", "com.title.prompt");
                httpServletRequest.setAttribute("message", "com.role.duplicate");
                return sForward;
            }
            else {
                updData.setUpd_userid(loginData.getUserTag());
                iRet = updLBean.insert(oConn, updData);
                if (iRet <= 0) {
                    httpServletRequest.setAttribute("message",
                        "com.update.fail");
                    sForward = ComGlobal.RESULT;
                }
            }
            clearForm(piForm);
            getAllOptions(oConn, piForm);

        }
        else if (piForm.getOp().equals("delete") == true) {

            F110_RoleMgtLBean delLBean = new F110_RoleMgtLBean();
            F110_RoleMgt delData = new F110_RoleMgt();
            try {
                BeanUtils.copyProperties(delData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            delData.setUpd_userid(loginData.getUserTag());

            delUserRole = delLBean.checkRoleUser(oConn,
                                                 delData.getSelectedrole_id());
            delDeptRole = delLBean.checkRoleDept(oConn,
                                                 delData.getSelectedrole_id());

            if (delUserRole && delDeptRole) {
                iRet = delLBean.quiteDelete(oConn, delData.getSelectedrole_id());
            }
            else {
                sForward = ComGlobal.RESULT;
                httpServletRequest.setAttribute("title", "com.delete.fail");
                httpServletRequest.setAttribute("message",
                                                "com.checkRoleUser.fail");
                return sForward;
            }

            if (iRet <= 0) {
                httpServletRequest.setAttribute("message", "com.delete.fail");
                sForward = ComGlobal.RESULT;
                return sForward;
            }
            else {
                piForm.setSelectedrole_id("");
                List selectresult = delLBean.SelectData(oConn, delData);
                PageUpDown pageUpDown = new PageUpDown(piForm.getCurPageNo());
                selectresult = pageUpDown.getPageSet(selectresult);
                piForm.setSelectResult(selectresult);
                piForm.generatePageInfo(pageUpDown);

            }
        }

        return sForward;
    }

    private F110_RoleMgtForm clearForm(F110_RoleMgtForm prm_Form) {
        prm_Form.setRole_id("");
        prm_Form.setRole_name("");
        prm_Form.setRole_depict("");
        return prm_Form;
    }

    private F110_RoleMgtForm getAllOptions(Connection oConn,
                                           F110_RoleMgtForm prm_Form) throws
        Exception {
        F110_RoleMgtLBean pnLBean = new F110_RoleMgtLBean();
        List role_list = pnLBean.getRoleListOptions(oConn);

        prm_Form.setRole_list(role_list);

        return prm_Form;
    }

}
