package com.solvd.uber.models;

public class Car {
    private Long id;
    private String model;
    private String color;
    private String make;
    private Integer modelYear;
    private Driver driver;

    public Car(){}

    public Car(String model, String color, String make, Integer modelYear, Driver driver) {
        this.model = model;
        this.color = color;
        this.make = make;
        this.modelYear = modelYear;
        this.driver = driver;
    }

    public Car(Long id, String model, String color, String make, Integer modelYear, Driver driver) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.make = make;
        this.modelYear = modelYear;
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
