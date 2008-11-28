package com.conant.ums.action;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;
import com.conant.ums.data.*;
import com.conant.ums.form.F150_DeptMgtForm;
import com.conant.ums.lbean.*;
import com.conant.ums.util.*;

public class F150_DeptMgtAction
    extends BaseAction {
    public String executeAct(Connection oConn,
                             ActionMapping actionMapping,
                             ActionForm actionForm,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws
        Exception {

        F150_DeptMgtForm piForm = (F150_DeptMgtForm) actionForm;
        piForm.setDept_name(BytesConverter.asc2gb(ComString.nvl(piForm.
            getDept_name())));
        HttpSession session = httpServletRequest.getSession(true);
        LoginData loginData = (LoginData) session.getAttribute("LoginData");
        int iRet = 0;
        int iRetrole = 0;
        boolean delDeptUser;
        String sForward = piForm.getForward();

        if (piForm.getOp().equals("select") == true) {

            F150_DeptMgtLBean pnLBean = new F150_DeptMgtLBean();
            F150_DeptMgt selData = new F150_DeptMgt();
            try {
                BeanUtils.copyProperties(selData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            List selectresult = pnLBean.SelectData(oConn, selData);
            PageUpDown pageUpDown = new PageUpDown(piForm.getCurPageNo());
            selectresult = pageUpDown.getPageSet(selectresult);
            piForm.setSelectResult(selectresult);
            piForm.generatePageInfo(pageUpDown);
        }

        else if (piForm.getOp().equals("selectoptions") == true) {
            getAllOptions(oConn, piForm);
        }
        else if (piForm.getOp().equals("selectupdinfo") == true) {
            F150_DeptMgtLBean pnLBean = new F150_DeptMgtLBean();
            F150_DeptMgt selData = new F150_DeptMgt();

            try {
                BeanUtils.copyProperties(selData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }
            List selectresult = pnLBean.SelectData(oConn, selData);
            if (selectresult.size() <= 0) {
                httpServletRequest.setAttribute("message", "com.select.fail");
                sForward = ComGlobal.RESULT;
            }
            else {
                piForm.setSelectResult(selectresult);
                selData = (F150_DeptMgt) selectresult.get(0);
                piForm.setDept_id(selData.getDept_id());
                piForm.setDept_name(selData.getDept_name());
                piForm.setDept_desc(selData.getDept_desc());
                piForm.setPrincipal(ComString.nvl(selData.getPrincipal()));
                piForm.setTelephone(ComString.nvl(selData.getTelephone()));
                piForm.setFaxes(ComString.nvl(selData.getFaxes()));
                piForm.setAdd_date(selData.getAdd_date());
                piForm.setArea(selData.getArea());
                piForm.setParent_dept_id(selData.getParent_dept_id());
                getAllOptions(oConn, piForm);
            }
        }

        else if (piForm.getOp().equals("insert") == true) {
            String[] deptrole = httpServletRequest.getParameterValues(
                "deptrolelist");
            F150_DeptMgtLBean addLBean = new F150_DeptMgtLBean();
            F150_DeptMgt addData = new F150_DeptMgt();
            try {
                BeanUtils.copyProperties(addData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            if (addLBean.isExist(oConn, addData.getDept_name())) {
                sForward = ComGlobal.RESULT;
                httpServletRequest.setAttribute("title", "com.title.prompt");
                httpServletRequest.setAttribute("message", "com.dept.duplicate");
                return sForward;
            }else{
                addData.setAdd_userid(loginData.getUserTag());
                iRet = addLBean.insert(oConn, addData);
                if (iRet <= 0) {
                    httpServletRequest.setAttribute("message", "com.add.fail");
                    sForward = ComGlobal.RESULT;
                }
                else if (deptrole != null) {
                    iRetrole = addLBean.insertDeptRole(oConn, piForm.getDept_id(),
                        deptrole);
                    if (iRetrole < 0) {
                        httpServletRequest.setAttribute("message",
                            "com.update.fail");
                        httpServletRequest.setAttribute("title",
                            "com.title.xaexception");
                        return "result_display";
                    }
                }
            }
            clearForm(piForm);
            getAllOptions(oConn, piForm);
        }

        else if (piForm.getOp().equals("update") == true) {
            String[] deptrole = httpServletRequest.getParameterValues(
                "deptrolelist");
            F150_DeptMgtLBean updLBean = new F150_DeptMgtLBean();
            F150_DeptMgt updData = new F150_DeptMgt();

            try {
                BeanUtils.copyProperties(updData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            if (updLBean.isUpdateExist(oConn, updData.getDept_id(), updData.getDept_name())) {
                sForward = ComGlobal.RESULT;
                httpServletRequest.setAttribute("title", "com.title.prompt");
                httpServletRequest.setAttribute("message", "com.dept.duplicate");
                return sForward;
            }else{
                updData.setUpd_userid(loginData.getUserTag());
                iRet = updLBean.insert(oConn, updData);

                if (iRet <= 0) {
                    httpServletRequest.setAttribute("message",
                        "com.update.fail");
                    sForward = ComGlobal.RESULT;
                }
                else {
                    iRetrole = updLBean.updateDeptRole(oConn, piForm.getDept_id(),
                        deptrole);
                    if (iRetrole < 0) {
                        httpServletRequest.setAttribute("message",
                            "com.update.fail");
                        httpServletRequest.setAttribute("title",
                            "com.title.xaexception");
                        return "result_display";
                    }
                }
            }
            clearForm(piForm);
            getAllOptions(oConn, piForm);
        }

        else if (piForm.getOp().equals("delete") == true) {

            F150_DeptMgtLBean delLBean = new F150_DeptMgtLBean();
            F150_DeptMgt delData = new F150_DeptMgt();
            try {
                BeanUtils.copyProperties(delData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            delData.setUpd_userid(loginData.getUserTag());

            delDeptUser = delLBean.checkDeptUser(oConn, delData.getSelecteddept_id());

            if(delDeptUser){
                //iRet = delLBean.delete(oConn, delData);
                iRet = delLBean.quiteDelete(oConn, delData.getSelecteddept_id());
            }
            else {
                sForward = ComGlobal.RESULT;
                httpServletRequest.setAttribute("title", "com.delete.fail");
                httpServletRequest.setAttribute("message", "com.checkDeptUser.fail");
                return sForward;
            }

            if (iRet < 0) {
                httpServletRequest.setAttribute("message", "com.delete.fail");
                sForward = ComGlobal.RESULT;
                return sForward;
            }
            else {
                piForm.setSelecteddept_id("");
                List selectresult = delLBean.SelectData(oConn, delData);
                PageUpDown pageUpDown = new PageUpDown(piForm.getCurPageNo());
                selectresult = pageUpDown.getPageSet(selectresult);
                piForm.setSelectResult(selectresult);
                piForm.generatePageInfo(pageUpDown);
            }
        }

        return sForward;
    }

    //解决当数据更新、提交时，输入框会出现短暂的乱码显示
    private F150_DeptMgtForm clearForm(F150_DeptMgtForm prm_Form) {
        prm_Form.setDept_id("");
        prm_Form.setDept_name("");
        prm_Form.setDept_desc("");
        prm_Form.setPrincipal("");
        prm_Form.setParent_role("#");

        return prm_Form;
    }

    private F150_DeptMgtForm getAllOptions(Connection oConn,
                                           F150_DeptMgtForm prm_Form) throws
        Exception {
        F110_RoleMgtLBean pnLBean = new F110_RoleMgtLBean();
        List role_list = pnLBean.getRoleListOptions(oConn);
        prm_Form.setRole_list(role_list);

        F150_DeptMgtLBean dnLBean = new F150_DeptMgtLBean();
        List dept_list = dnLBean.getDeptListOptions(oConn);
        prm_Form.setDept_list(dept_list);

        List dept_role_list = dnLBean.getDeptRoleListOptions(oConn,
            prm_Form.getDept_id());
        prm_Form.setDeptrole(dept_role_list);

        return prm_Form;
    }

}
