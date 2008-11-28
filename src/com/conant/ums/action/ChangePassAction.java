package com.conant.ums.action;

import java.sql.Connection;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import com.conant.ums.data.*;
import com.conant.ums.form.PassForm;
import com.conant.ums.lbean.F130_UserLBean;
import com.conant.ums.util.ComString;
import com.conant.ums.util.ComGlobal;

public class ChangePassAction
    extends BaseAction {
    public ChangePassAction() {
    }

    public String executeAct(Connection con, ActionMapping actionMapping,
                             ActionForm actionForm,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws
        Exception {
        /**@todo: complete the business logic here, this is just a skeleton.*/
        //Connection con = DbAccess.getConnect("");
        PassForm passForm = (PassForm) actionForm;
        HttpSession session = httpServletRequest.getSession(true);
        LoginData loginData = (LoginData) session.getAttribute("LoginData");
        String sPassword = passForm.getUser_passwd();
        String sOp = passForm.getOp();

        int iRet = 0;

        F130_UserLBean userLBean = new F130_UserLBean();
        F130_UserMgt dataBean = new F130_UserMgt();

        dataBean.setUser_tag(loginData.getUserTag());
        dataBean.setUser_passwd(ComString.MD5Encode(sPassword));

        if (sOp.equals("changePass") == true) {

            iRet = userLBean.changePass(con, dataBean);
            if (iRet < 0) {
                httpServletRequest.setAttribute("message", "com.add.fail");
                httpServletRequest.setAttribute("title",
                                                "com.title.xaexception");
                return "result_display";
            }

        }
        return ComGlobal.CHANGEPASS;
    }
}
