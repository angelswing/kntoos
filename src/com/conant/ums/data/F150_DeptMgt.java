package com.conant.ums.data;

import java.util.List;

public class F150_DeptMgt
    extends BaseData {

    private String dept_name;
    private String dept_id;
    private String selecteddept_id;
    private String dept_desc;
    private String principal;
    private String telephone;
    private String faxes;
    private String upd_date;
    private String add_userid;
    private String upd_userid;
    private String add_date;
    private String parent_dept_id;
    private String role_id;
    private String role_name;
    private List deptrole;
    private String dept_role_id;
    private String dept_role_name;
    private String area;

    public F150_DeptMgt() {
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getSelecteddept_id() {
        return selecteddept_id;
    }

    public String getDept_desc() {
        return dept_desc;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFaxes() {
        return faxes;
    }

    public String getUpd_date() {
        return upd_date;
    }

    public String getAdd_userid() {
        return add_userid;
    }

    public String getUpd_userid() {
        return upd_userid;
    }

    public String getAdd_date() {
        return add_date;
    }

    public String getParent_dept_id() {
        return parent_dept_id;
    }

    public List getDeptrole() {
        return deptrole;
    }

    public String getRole_id() {
        return role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public String getDept_role_id() {
        return dept_role_id;
    }

    public String getDept_role_name() {
        return dept_role_name;
    }

    public String getArea()
    {
        return area;
    }

    public void setSelecteddept_id(String selecteddept_id) {
        this.selecteddept_id = selecteddept_id;
    }

    public void setDept_desc(String dept_desc) {
        this.dept_desc = dept_desc;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setFaxes(String faxes) {
        this.faxes = faxes;
    }

    public void setUpd_date(String upd_date) {
        this.upd_date = upd_date;
    }

    public void setAdd_userid(String add_userid) {
        this.add_userid = add_userid;
    }

    public void setUpd_userid(String upd_userid) {
        this.upd_userid = upd_userid;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public void setParent_dept_id(String parent_dept_id) {
        this.parent_dept_id = parent_dept_id;
    }

    public void setDeptrole(List deptrole) {
        this.deptrole = deptrole;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public void setDept_role_id(String dept_role_id) {
        this.dept_role_id = dept_role_id;
    }

    public void setDept_role_name(String dept_role_name) {
        this.dept_role_name = dept_role_name;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

}
