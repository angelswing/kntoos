package com.conant.ums.form;

import org.apache.struts.action.ActionForm;

public class PassForm
    extends ActionForm {
    public PassForm() {
    }

    private String op;
    private String user_passwd;

    public String getUser_passwd() {
        return user_passwd;
    }

    public void setUser_passwd(String user_passwd) {
        this.user_passwd = user_passwd;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

}
