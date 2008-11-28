package com.conant.ums.action;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import org.apache.commons.beanutils.*;
import org.apache.struts.action.*;
import com.conant.ums.action.*;
import com.conant.ums.data.*;
import com.conant.ums.form.*;
import com.conant.ums.lbean.*;
import com.conant.ums.util.*;

public class F160_UserOnlAction
    extends BaseAction {
    public F160_UserOnlAction() {
    }

    public String executeAct(Connection con,
                             ActionMapping actionMapping,
                             ActionForm actionForm,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws
        Exception {
        /**@todo: complete the business logic here, this is just a skeleton.*/

        F160_UserOnlForm piForm = (F160_UserOnlForm) actionForm;

        String sForward = piForm.getForward();

         if (piForm.getOp().equals("select") == true) {

            F160_UserOnlLBean pnLBean = new F160_UserOnlLBean();
            F130_UserMgt pnLData = new F130_UserMgt();
            try {
                BeanUtils.copyProperties(pnLData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            List selectresult = pnLBean.selectInfo();

            PageUpDown pageUpDown = new PageUpDown(piForm.getCurPageNo());
            selectresult = pageUpDown.getPageSet(selectresult);
            piForm.setSelectResult(selectresult);

            piForm.generatePageInfo(pageUpDown);
        }

        else if (piForm.getOp().equals("selectupdinfo") == true) {

            F160_UserOnlLBean pnLBean = new F160_UserOnlLBean();
            F130_UserMgt pnLData = new F130_UserMgt();
            try {
                BeanUtils.copyProperties(pnLData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            List selectresult = pnLBean.selectRecord(con, pnLData);
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

        return sForward;
    }

    private F160_UserOnlForm getAllOptions(Connection oConn,
                                           F160_UserOnlForm prm_Form) throws
        Exception {

        F150_DeptMgtLBean dnLBean = new F150_DeptMgtLBean();
        List dept_list = dnLBean.getDeptListOptions(oConn);
        prm_Form.setDeptOptions(dept_list);

        return prm_Form;
    }

    private F160_UserOnlForm getUserRoleOptions(Connection oConn,
                                                F160_UserOnlForm prm_Form) throws
        Exception {
        F130_UserLBean usLBean = new F130_UserLBean();
        List userrolegroup = usLBean.getUserRoleListOptions(oConn,
            prm_Form.getUser_id());
        prm_Form.setUserRoleGroup(userrolegroup);

        return prm_Form;
    }

}

