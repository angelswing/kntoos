package com.conant.ums.action;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import com.conant.ums.data.LoginData;
import com.conant.ums.form.LoginForm;
import com.conant.ums.lbean.FuncLBean;
import com.conant.ums.util.*;

public class LoginAction
    extends BaseAction {
    public LoginAction() {
    }

    public String executeAct(Connection oConn,
                             ActionMapping actionMapping,
                             ActionForm actionForm,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws
        Exception {
        /**@todo: complete the business logic here, this is just a skeleton.*/
        LoginForm loginForm = (LoginForm) actionForm;
        String sForward = ComGlobal.INDEX;
        int iRet = 0;
        String sIp = httpServletRequest.getRemoteAddr();

        LoginData loginData = new LoginData();
        FuncLBean funcLBean = new FuncLBean();

        loginData.setUserTag(loginForm.getUserTag());
        //加密密码
        loginData.setUserPasswd(ComString.MD5Encode(loginForm.getUserPasswd()));
        //loginData.setUserPasswd(loginForm.getUserPasswd());

        //检查用户是否存在、密码是否正确
        if (!funcLBean.checkUser(oConn, loginData)) {
            sForward = ComGlobal.RESULT;
            httpServletRequest.setAttribute("title", "com.login.title");
            httpServletRequest.setAttribute("message", "com.login.fail");
            return sForward;
        }

        List rootList = funcLBean.loginData(oConn);
        loginData.setDeptList(rootList);

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("LoginData", loginData);
        return sForward;
    }

}
