package com.solvd.uber.models;

import java.sql.Date;

public class ServiceCar {
    private Long id;
    private Date date;
    private Double cost;
    private Service service;
    private Car car;

    public ServiceCar() {
    }

    public ServiceCar(Date date, Double cost, Service service, Car car) {
        this.date = date;
        this.cost = cost;
        this.service = service;
        this.car = car;
    }

    public ServiceCar(Long id, Date date, Double cost, Service service, Car car) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.service = service;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
