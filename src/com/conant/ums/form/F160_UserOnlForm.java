package com.conant.ums.form;

import com.conant.ums.form.*;

public class F160_UserOnlForm
    extends BaseForm {

    private String user_id; //用户登陆ID
    private String user_name; //用户姓名
    private String email; //邮件地址
    private String address; //家庭住址
    private String home_tel; //家庭电话
    private String mobile; //手机号码
    private String deptid; //部门ID
    private java.util.List deptOptions; //可选的部门的集合
    private String op;
    private java.util.List selectResult;
    private String add_date;
    private String user_tag;
    private java.util.List userRoleGroup;
    private String userrole;
    private String selecteduser_id;
    private String login_time;
    private String login_ip;
    private String lock_flag;

    public F160_UserOnlForm() {
    }

    public java.util.List getDeptOptions() {
        return deptOptions;
    }

    public void setDeptOptions(java.util.List deptOptions) {
        this.deptOptions = deptOptions;
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

    public String getDeptid() {
        return deptid;
    }

    public String getEmail() {
        return email;
    }

    public String getHome_tel() {
        return home_tel;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setHome_tel(String home_tel) {
        this.home_tel = home_tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getAdd_date() {
        return add_date;
    }

    public String getUser_tag() {
        return user_tag;
    }

    public java.util.List getUserRoleGroup() {
        return userRoleGroup;
    }

    public String getUserrole() {
        return userrole;
    }

    public String getSelecteduser_id() {
        return selecteduser_id;
    }

    public String getLogin_time() {
        return login_time;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public String getLock_flag() {
        return lock_flag;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public void setUser_tag(String user_tag) {
        this.user_tag = user_tag;
    }

    public void setUserRoleGroup(java.util.List userRoleGroup) {
        this.userRoleGroup = userRoleGroup;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public void setSelecteduser_id(String selecteduser_id) {
        this.selecteduser_id = selecteduser_id;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public void setLock_flag(String lock_flag) {
        this.lock_flag = lock_flag;
    }

}
