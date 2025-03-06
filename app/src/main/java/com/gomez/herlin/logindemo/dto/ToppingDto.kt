package com.gomez.herlin.logindemo.dto;

import java.io.Serializable;

public class ToppingDto implements Serializable {
    private String id;
    private String type;

    public ToppingDto() {
    }

    public ToppingDto(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
