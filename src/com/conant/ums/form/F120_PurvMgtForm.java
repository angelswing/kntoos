package com.conant.ums.form;

import java.util.*;

import com.conant.ums.form.*;

public class F120_PurvMgtForm
    extends BaseForm {

    private String op;
    private java.util.List selectResult;
    private java.util.List operList;
    private java.util.List entiList;
    private java.util.List role_list;
    private java.util.List oper_list;
    private java.util.List enti_list;
    private String roleselect;
    private String operName;
    private String entiName;

    public F120_PurvMgtForm() {
    }

    public java.util.List getOperList() {
        return operList;
    }

    public void setOperList(java.util.List operList) {
        this.operList = operList;
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

    public java.util.List getRole_list() {
        return role_list;
    }

    public String getRoleselect() {
        return roleselect;
    }

    public java.util.List getOper_list() {
        return oper_list;
    }

    public String getOperName() {
        return operName;
    }

    public java.util.List getEntiList() {
        return entiList;
    }

    public String getEntiName() {
        return entiName;
    }

    public java.util.List getEnti_list() {
        return enti_list;
    }

    public void setRole_list(java.util.List role_list) {
        this.role_list = role_list;
    }

    public void setRoleselect(String roleselect) {
        this.roleselect = roleselect;
    }

    public void setOper_list(java.util.List oper_list) {
        this.oper_list = oper_list;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public void setEntiList(java.util.List entiList) {
        this.entiList = entiList;
    }

    public void setEntiName(String entiName) {
        this.entiName = entiName;
    }

    public void setEnti_list(java.util.List enti_list) {
        this.enti_list = enti_list;
    }
}
