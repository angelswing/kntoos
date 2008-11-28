package com.conant.ums.form;

import com.conant.ums.form.*;

public class F140_UserLimForm
    extends BaseForm {

    private String op;
    private String user_id;
    private String user_name;
    private String user_tag;
    private String add_date;
    private java.util.List selectResult;
    private java.util.List parOptions;
    private java.util.List limitTimeGroup;
    private java.util.List limitIpGroup;
    private String timetype;
    private String iptype;
    private String limitedtime;
    private String limitedip;
    private String lock_flag;
    private String is_login;
    private String dept_name;

    public F140_UserLimForm() {
    }

    public java.util.List getLimitTimeGroup() {
        return limitTimeGroup;
    }

    public void setLimitTimeGroup(java.util.List limitTimeGroup) {
        this.limitTimeGroup = limitTimeGroup;
    }

    public java.util.List getSelectResult() {
        return selectResult;
    }

    public void setSelectResult(java.util.List selectResult) {
        this.selectResult = selectResult;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getTimetype() {
        return timetype;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTimetype(String timetype) {
        this.timetype = timetype;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public java.util.List getLimitIpGroup() {
        return limitIpGroup;
    }

    public void setLimitIpGroup(java.util.List limitIpGroup) {
        this.limitIpGroup = limitIpGroup;
    }

    public java.util.List getParOptions() {
        return parOptions;
    }

    public void setParOptions(java.util.List parOptions) {
        this.parOptions = parOptions;
    }

    public String getAdd_date() {
        return add_date;
    }

    public String getUser_tag() {
        return user_tag;
    }

    public String getIptype() {
        return iptype;
    }

    public String getLimitedtime() {
        return limitedtime;
    }

    public String getLimitedip() {
        return limitedip;
    }

    public String getLock_flag() {
        return lock_flag;
    }

    public String getIs_login() {
        return is_login;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public void setUser_tag(String user_tag) {
        this.user_tag = user_tag;
    }

    public void setIptype(String iptype) {
        this.iptype = iptype;
    }

    public void setLimitedtime(String limitedtime) {
        this.limitedtime = limitedtime;
    }

    public void setLimitedip(String limitedip) {
        this.limitedip = limitedip;
    }

    public void setLock_flag(String lock_flag) {
        this.lock_flag = lock_flag;
    }

    public void setIs_login(String is_login) {
        this.is_login = is_login;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

}
