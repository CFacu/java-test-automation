package com.solvd.uber.models;

public class Service {
    private Long id;
    private String name;
    private Integer serialNumber;

    public Service() {
    }

    public Service(String name, Integer serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
    }

    public Service(Long id, String name, Integer serialNumber) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
}
