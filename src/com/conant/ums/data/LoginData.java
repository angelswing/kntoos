package com.conant.ums.data;

import java.io.Serializable;
import java.util.*;

import com.conant.ums.util.tree.TreeNode;

public class LoginData
    implements Serializable {
    private String userTag;
    private String userName;
    private String userRole;
    private String dept_id;
    private Set funcSet;
    private TreeNode funcRoot;
    private List roleList;
    private List deptList;
    private List ctrl_content;
    private String userPasswd;
    private String area_id;
    private String user_tag;
    public LoginData() {
    }

    public String getUserTag() {
        return userTag;
    }

    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public Set getFuncSet() {
        return funcSet;
    }

    public void setFuncSet(Set funcSet) {
        this.funcSet = funcSet;
    }

    //用户是否可以执行该功能
    public boolean canExecute(String func_code) {
        //return funcSet.contains(func_code);
        //for test
        return true;
    }

    public TreeNode getFuncRoot() {
        return funcRoot;
    }

    public void setFuncRoot(TreeNode funcRoot) {
        this.funcRoot = funcRoot;
    }

    public List getRoleList() {
        return roleList;
    }

    public void setRoleList(List roleList) {
        this.roleList = roleList;
    }

    public List getDeptList() {
        return deptList;
    }

    public void setDeptList(List deptList) {
        this.deptList = deptList;
    }

    public java.util.List getCtrl_content() {
        return ctrl_content;
    }

    public void setCtrl_content(List ctrl_content) {
        this.ctrl_content = ctrl_content;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getArea_id() {
        return area_id;
    }

    public String getUser_tag() {
        return user_tag;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public void setUser_tag(String user_tag) {
        this.user_tag = user_tag;
    }
}
