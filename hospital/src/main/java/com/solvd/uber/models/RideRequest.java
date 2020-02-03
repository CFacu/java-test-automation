package com.solvd.uber.models;

import java.sql.Date;

public class RideRequest {
    private Long id;
    private Date requestTime;
    private String locationStart;
    private String locationEnd;
    private Long rideId;
    private Long userId;

    public RideRequest() {
    }

    public RideRequest(Date requestTime, String locationStart, String locationEnd, Long rideId, Long userId) {
        this.requestTime = requestTime;
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
        this.rideId = rideId;
        this.userId = userId;
    }

    public RideRequest(Long id, Date requestTime, String locationStart, String locationEnd, Long rideId, Long userId) {
        this.id = id;
        this.requestTime = requestTime;
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
        this.rideId = rideId;
        this.userId = userId;
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

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
