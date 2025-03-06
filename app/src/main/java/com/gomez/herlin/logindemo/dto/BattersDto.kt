package com.gomez.herlin.logindemo.dto;

import java.io.Serializable;
import java.util.List;

public class BattersDto implements Serializable {
    private List<BatterDto> batter;

    public BattersDto() {
    }

    public BattersDto(List<BatterDto> batter) {
        this.batter = batter;
    }

    public List<BatterDto> getBatter() {
        return batter;
    }

    public void setBatter(List<BatterDto> batter) {
        this.batter = batter;
    }
}
