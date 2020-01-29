package com.solvd.uber.models;

import java.sql.Date;

public class Ride {
    private Long id;
    private Integer distance;
    private Date pickUpTime;
    private Date dropOffTime;
    private Double cost;
    private Driver driver;

    public Ride() {
    }

    public Ride(Integer distance, Date pickUpTime, Date dropOffTime, Double cost, Driver driver) {
        this.distance = distance;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.cost = cost;
        this.driver = driver;
    }

    public Ride(Long id, Integer distance, Date pickUpTime, Date dropOffTime, Double cost, Driver driver) {
        this.id = id;
        this.distance = distance;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.cost = cost;
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Date getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Date pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public Date getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(Date dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
