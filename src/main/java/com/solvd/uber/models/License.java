package com.solvd.uber.models;

import java.sql.Date;

public class License {
    private Long id;
    private Integer number;
    private Date expDate;
    private Driver drive;

    public License() {
    }

    public License(Integer number, Date expDate, Driver drive) {
        this.number = number;
        this.expDate = expDate;
        this.drive = drive;
    }

    public License(Long id, Integer number, Date expDate, Driver drive) {
        this.id = id;
        this.number = number;
        this.expDate = expDate;
        this.drive = drive;
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

    public Driver getDrive() {
        return drive;
    }

    public void setDrive(Driver drive) {
        this.drive = drive;
    }
}
