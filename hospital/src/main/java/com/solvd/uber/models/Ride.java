package com.solvd.uber.models;

import java.sql.Date;

public class Ride {
    private Long id;
    private Integer distance;
    private Date pickUpTime;
    private Date dropOffTime;
    private Double cost;
    private Long driversId;

    public Ride() {
    }

    public Ride(Integer distance, Date pickUpTime, Date dropOffTime, Double cost, Long driversId) {
        this.distance = distance;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.cost = cost;
        this.driversId = driversId;
    }

    public Ride(Long id, Integer distance, Date pickUpTime, Date dropOffTime, Double cost, Long driversId) {
        this.id = id;
        this.distance = distance;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.cost = cost;
        this.driversId = driversId;
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

    public Long getDriversId() {
        return driversId;
    }

    public void setDriversId(Long driversId) {
        this.driversId = driversId;
    }
}
