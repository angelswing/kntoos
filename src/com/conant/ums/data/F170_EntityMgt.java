package com.conant.ums.data;



public class F170_EntityMgt
    extends BaseData {

    private int layer_rank;
    private int layer_seq;
    private String enti_id;
    private String enti_name;
    private String enti_type;
    private String parent_enti_id;
    private String enti_link;
    private String entiId;
    public F170_EntityMgt() {
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

    public String getEnti_id() {
        return enti_id;
    }

    public String getEnti_name() {
        return enti_name;
    }

    public String getEnti_type() {
        return enti_type;
    }

    public String getParent_enti_id() {
        return parent_enti_id;
    }

    public String getEnti_link() {
        return enti_link;
    }

    public String getEntiId() {
        return entiId;
    }

    public void setEnti_id(String enti_id) {
        this.enti_id = enti_id;
    }

    public void setEnti_name(String enti_name) {
        this.enti_name = enti_name;
    }

    public void setEnti_type(String enti_type) {
        this.enti_type = enti_type;
    }

    public void setParent_enti_id(String parent_enti_id) {
        this.parent_enti_id = parent_enti_id;
    }

    public void setEnti_link(String enti_link) {
        this.enti_link = enti_link;
    }

    public void setEntiId(String entiId) {
        this.entiId = entiId;
    }
}
