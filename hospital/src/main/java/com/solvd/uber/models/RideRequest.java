package com.solvd.uber.models;

import java.sql.Date;

public class RideRequest {
    private Long id;
    private Date requestTime;
    private String locationStart;
    private String locationEnd;
    private Ride ride;
    private User user;

    public RideRequest() {
    }

    public RideRequest(Date requestTime, String locationStart, String locationEnd, Ride ride, User user) {
        this.requestTime = requestTime;
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
        this.ride = ride;
        this.user = user;
    }

    public RideRequest(Long id, Date requestTime, String locationStart, String locationEnd, Ride ride, User user) {
        this.id = id;
        this.requestTime = requestTime;
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
        this.ride = ride;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getLocationStart() {
        return locationStart;
    }

    public void setLocationStart(String locationStart) {
        this.locationStart = locationStart;
    }

    public String getLocationEnd() {
        return locationEnd;
    }

    public void setLocationEnd(String locationEnd) {
        this.locationEnd = locationEnd;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
