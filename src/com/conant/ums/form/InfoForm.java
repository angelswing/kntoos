package com.conant.ums.form;

import org.apache.struts.action.ActionForm;

public class InfoForm
    extends ActionForm {
    private String title;
    private String message;
    public InfoForm() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
