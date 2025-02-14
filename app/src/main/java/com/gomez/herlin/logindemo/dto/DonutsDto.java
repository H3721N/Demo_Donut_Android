package com.gomez.herlin.logindemo.dto;

import java.io.Serializable;
import java.util.List;

public class DonutsDto implements Serializable {
    private String id;
    private String type;
    private String name;
    private double ppu;
    private BattersDto batters;
    private List<ToppingDto> topping;

    public DonutsDto() {
    }

    public DonutsDto(String id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public DonutsDto(String id, String type, String name, double ppu, BattersDto batters, List<ToppingDto> topping) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.ppu = ppu;
        this.batters = batters;
        this.topping = topping;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPpu() {
        return ppu;
    }

    public void setPpu(double ppu) {
        this.ppu = ppu;
    }

    public BattersDto getBatters() {
        return batters;
    }

    public void setBatters(BattersDto batters) {
        this.batters = batters;
    }

    public List<ToppingDto> getTopping() {
        return topping;
    }

    public void setTopping(List<ToppingDto> topping) {
        this.topping = topping;
    }

    public List<DonutsDto> getDonuts() {
        return null;
    }
}
