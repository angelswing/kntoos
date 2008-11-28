package com.conant.ums.action;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import com.conant.ums.form.F120_PurvMgtForm;
import com.conant.ums.lbean.*;
import com.conant.ums.util.ComGlobal;
import java.util.ArrayList;

public class F120_PurvMgtAction
    extends BaseAction {
    public String executeAct(Connection oConn,
                             ActionMapping actionMapping,
                             ActionForm actionForm,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws
        Exception {

        F120_PurvMgtForm piForm = (F120_PurvMgtForm) actionForm;

        String sForward = piForm.getForward();
        int iRetoper = 0;

        if (piForm.getOp().equals("select") == true) {
            F120_PurvMgtLBean pnLBean = new F120_PurvMgtLBean();
            List operList = new ArrayList();
            try{
                operList = pnLBean.SelectOper();
            }catch(Exception e){
            	log.debug("error: " + e.toString());
                sForward = ComGlobal.RESULT;
                httpServletRequest.setAttribute("title", "com.title.prompt");
                httpServletRequest.setAttribute("message", "com.tree.fail");
                return sForward;
            }
            piForm.setOperList(operList);

            piForm.setOper_list(pnLBean.getOperListOptions(oConn, "#"));
            getRoleOptions(oConn, piForm);
        }

        else if (piForm.getOp().equals("selectchangerole") == true) {
            F120_PurvMgtLBean pnLBean = new F120_PurvMgtLBean();

            List operList = pnLBean.SelectOperS(oConn, piForm.getRoleselect());
            piForm.setOperList(operList);

            getAllOptions(oConn, piForm);
            getRoleOptions(oConn, piForm);
        }

        else if (piForm.getOp().equals("insert") == true) {
            String[] oper = httpServletRequest.getParameterValues("operName");
            F120_PurvMgtLBean updBean = new F120_PurvMgtLBean();

            iRetoper = updBean.deleteRoleOper(oConn, piForm.getRoleselect());
            if (iRetoper < 0) {
                httpServletRequest.setAttribute("message",
                                                "com.delete.fail");
                httpServletRequest.setAttribute("title",
                                                "com.title.xaexception");
                return "result_display";
            }
            if (oper != null) {
                iRetoper = updBean.insertRoleOper(oConn, piForm.getRoleselect(),
                                                  oper);
                if (iRetoper < 0) {
                    httpServletRequest.setAttribute("message", "com.add.fail");
                    httpServletRequest.setAttribute("title",
                        "com.title.xaexception");
                    return "result_display";
                }
            }

            List operList = updBean.SelectOperS(oConn, piForm.getRoleselect());
            piForm.setOperList(operList);

            getAllOptions(oConn, piForm);
            getRoleOptions(oConn, piForm);
        }

        return sForward;
    }

    private F120_PurvMgtForm getRoleOptions(Connection oConn,
                                            F120_PurvMgtForm prm_Form) throws
        Exception {
        F110_RoleMgtLBean pnLBean = new F110_RoleMgtLBean();
        List role_list = pnLBean.getRoleListOptions(oConn);
        prm_Form.setRole_list(role_list);

        return prm_Form;
    }

    private F120_PurvMgtForm getAllOptions(Connection oConn,
                                           F120_PurvMgtForm prm_Form) throws
        Exception {
        F120_PurvMgtLBean purBean = new F120_PurvMgtLBean();
        List oper_list = purBean.getOperListOptions(oConn,
            prm_Form.getRoleselect());
        prm_Form.setOper_list(oper_list);

        return prm_Form;
    }

}
