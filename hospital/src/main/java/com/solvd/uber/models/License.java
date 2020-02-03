package com.solvd.uber.models;

import java.sql.Date;

public class License {
    private Long id;
    private Integer number;
    private Date expDate;
    private Long driversId;

    public License() {
    }

    public License(Integer number, Date expDate, Long driversId) {
        this.number = number;
        this.expDate = expDate;
        this.driversId = driversId;
    }

    public License(Long id, Integer number, Date expDate, Long driversId) {
        this.id = id;
        this.number = number;
        this.expDate = expDate;
        this.driversId = driversId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Long getDriversId() {
        return driversId;
    }

    public void setDriversId(Long driversId) {
        this.driversId = driversId;
    }
}
