package com.conant.ums.action;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;
import com.conant.ums.data.F170_EntityMgt;
import com.conant.ums.form.F170_EntityMgtForm;
import com.conant.ums.lbean.F170_EntityMgtLBean;

public class F170_EntityMgtAction
    extends BaseAction {
    public String executeAct(Connection oConn,
                             ActionMapping actionMapping,
                             ActionForm actionForm,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws
        Exception {

        F170_EntityMgtForm piForm = (F170_EntityMgtForm) actionForm;
        String sForward = piForm.getForward();

        if (piForm.getOp().equals("select") == true) {

            F170_EntityMgtLBean pnLBean = new F170_EntityMgtLBean();
            F170_EntityMgt selData = new F170_EntityMgt();
            try {
                BeanUtils.copyProperties(selData, piForm);
            }
            catch (Exception e) {
                log.error("error: " + e.toString());
                throw e;
            }

            List rootList = pnLBean.SelectData();
            piForm.setRootList(rootList);
        }
        return sForward;
    }

}
