package com.conant.ums.action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.*;

import com.conant.ums.form.F180_OperMgtForm;
import com.conant.ums.lbean.F180_OperMgtLBean;
import org.apache.struts.action.*;

public class F180_OperMgtAction
    extends BaseAction {
    public String executeAct(Connection oConn,
                             ActionMapping actionMapping,
                             ActionForm actionForm,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws
        Exception {
        /**@todo: complete the business logic here, this is just a skeleton.*/
        F180_OperMgtForm piForm = (F180_OperMgtForm) actionForm;
        String sForward = piForm.getForward();

        if (piForm.getOp().equals("select") == true) {

            F180_OperMgtLBean pnLBean = new F180_OperMgtLBean();

            List rootList = pnLBean.SelectData();
            piForm.setRootList(rootList);
        }
        return sForward;
    }

}
