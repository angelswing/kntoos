package com.conant.ums.data;

public class Func_t {
    private String fun_id;
    private String fun_bit;
    private String fun_code;
    private String layer_rank;
    private String fun_name;
    private String parent_funid;
    private String fun_link;
    private int layer_seq;
    private int fun_flag;
    private String fun_type;
    public Func_t() {
    }

    public String getFun_id() {
        return fun_id;
    }

    public void setFun_id(String fun_id) {
        this.fun_id = fun_id;
    }

    public String getFun_bit() {
        return fun_bit;
    }

    public void setFun_bit(String fun_bit) {
        this.fun_bit = fun_bit;
    }

    public String getFun_code() {
        return fun_code;
    }

    public void setFun_code(String fun_code) {
        this.fun_code = fun_code;
    }

    public String getLayer_rank() {
        return layer_rank;
    }

    public void setLayer_rank(String layer_rank) {
        this.layer_rank = layer_rank;
    }

    public int getLayer_seq() {
        return layer_seq;
    }

    public void setLayer_seq(int layer_seq) {
        this.layer_seq = layer_seq;
    }

    public String getFun_name() {
        return fun_name;
    }

    public void setFun_name(String fun_name) {
        this.fun_name = fun_name;
    }

    public String getParent_funid() {
        return parent_funid;
    }

    public void setParent_funid(String parent_funid) {
        this.parent_funid = parent_funid;
    }

    public int getFun_flag() {
        return fun_flag;
    }

    public void setFun_flag(int fun_flag) {
        this.fun_flag = fun_flag;
    }

    public String getFun_link() {
        return fun_link;
    }

    public String getFun_type() {
        return fun_type;
    }

    public void setFun_link(String fun_link) {
        this.fun_link = fun_link;
    }

    public void setFun_type(String fun_type) {
        this.fun_type = fun_type;
    }

}
