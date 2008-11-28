package com.conant.ums.form;

import org.apache.struts.action.ActionForm;

public class LoginForm
    extends ActionForm {
    private String userTag;
    private String userPasswd;
    public LoginForm() {
    }

    public String getUserTag() {
        return userTag;
    }

    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

}
