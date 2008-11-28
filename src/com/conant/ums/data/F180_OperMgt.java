package com.conant.ums.data;

public class F180_OperMgt
    extends BaseData {

    private int layer_rank;
    private int layer_seq;
    private String oper_id;
    private String oper_name;
    private String oper_type;
    private String parent_oper_id;
    private String oper_link;
    private String operId;
    public F180_OperMgt() {
    }

    public int getLayer_rank() {
        return layer_rank;
    }

    public void setLayer_rank(int layer_rank) {
        this.layer_rank = layer_rank;
    }

    public int getLayer_seq() {
        return layer_seq;
    }

    public void setLayer_seq(int layer_seq) {
        this.layer_seq = layer_seq;
    }

    public String getOper_id() {
        return oper_id;
    }

    public String getOper_name() {
        return oper_name;
    }

    public String getOper_type() {
        return oper_type;
    }

    public String getParent_oper_id() {
        return parent_oper_id;
    }

    public String getOper_link() {
        return oper_link;
    }

    public String getOperId() {
        return operId;
    }

    public void setOper_id(String oper_id) {
        this.oper_id = oper_id;
    }

    public void setOper_name(String oper_name) {
        this.oper_name = oper_name;
    }

    public void setOper_type(String oper_type) {
        this.oper_type = oper_type;
    }

    public void setParent_oper_id(String parent_oper_id) {
        this.parent_oper_id = parent_oper_id;
    }

    public void setOper_link(String oper_link) {
        this.oper_link = oper_link;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }
}
