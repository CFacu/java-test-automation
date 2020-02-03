package com.solvd.uber.models;

import java.sql.Date;

public class Order {
    private Long id;
    private Date date;
    private Double cost;
    private Long serviceId;
    private Long carId;

    public Order() {
    }

    public Order(Date date, Double cost, Long serviceId, Long carId) {
        this.date = date;
        this.cost = cost;
        this.serviceId = serviceId;
        this.carId = carId;
    }

    public Order(Long id, Date date, Double cost, Long serviceId, Long carId) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.serviceId = serviceId;
        this.carId = carId;
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

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
