package com.conant.ums.action;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import com.conant.ums.util.ComGlobal;

public class LogoutAction
    extends Action {
    public LogoutAction() {
    }

    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws
        Exception {
        /**@todo: complete the business logic here, this is just a skeleton.*/
        //throw new java.lang.UnsupportedOperationException("Method perform() not yet implemented.");
        HttpSession session = httpServletRequest.getSession(true);
        String sForward = ComGlobal.LOGIN;

        //清除session的信息
        //session = httpServletRequest.getSession(false);
        //if (session != null) {
            //session.invalidate();
        //}
        return actionMapping.findForward(sForward);
    }

}
