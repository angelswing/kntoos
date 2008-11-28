package com.conant.ums.util;

import java.io.Serializable;

public class SelectOption
    implements Serializable {

    private String id;
    private String name;
    public SelectOption() {
    }

    public SelectOption(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
