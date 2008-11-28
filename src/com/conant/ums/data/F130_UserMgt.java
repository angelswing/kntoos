package com.conant.ums.data;

import java.util.List;

public class F130_UserMgt
    extends BaseData {
    private String user_id; //用户登陆ID
    private String deptid; //部门ID
    private String user_passwd; //用户密码
    private String user_name; //用户姓名
    private String email; //邮件地址
    private String address; //办公电话
    private String home_tel; //家庭电话
    private String mobile; //手机号码
    private String dept_id;
    private String dept_name;
    private String role_id; //用户角色ID
    private String role_name;
    private String role;
    private List deptRoleGroup;
    private String user_tag;
    private String user_role_id;
    private String user_role_name;
    private String login_time;
    private String login_ip;
    private String lock_flag;
    private String is_login;
    private String area;

    public F130_UserMgt() {
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome_tel() {
        return home_tel;
    }

    public void setHome_tel(String home_tel) {
        this.home_tel = home_tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_passwd() {
        return user_passwd;
    }

    public void setUser_passwd(String user_passwd) {
        this.user_passwd = user_passwd;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public List getDeptRoleGroup() {
        return deptRoleGroup;
    }

    public void setDeptRoleGroup(List deptRoleGroup) {
        this.deptRoleGroup = deptRoleGroup;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDeptid() {
        return deptid;
    }

    public String getUser_tag() {
        return user_tag;
    }

    public String getRole_name() {
        return role_name;
    }

    public String getUser_role_id() {
        return user_role_id;
    }

    public String getUser_role_name() {
        return user_role_name;
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

    public String getIs_login() {
        return is_login;
    }

    public String getArea()
    {
        return area;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public void setUser_tag(String user_tag) {
        this.user_tag = user_tag;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public void setUser_role_id(String user_role_id) {
        this.user_role_id = user_role_id;
    }

    public void setUser_role_name(String user_role_name) {
        this.user_role_name = user_role_name;
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

    public void setIs_login(String is_login) {
        this.is_login = is_login;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

}
