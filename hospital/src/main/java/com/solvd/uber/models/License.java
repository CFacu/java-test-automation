package com.solvd.uber.models;

import java.sql.Date;

public class License {
    private Long id;
    private Integer number;
    private Date expDate;

    public License() {
    }

    public License(Integer number, Date expDate) {
        this.number = number;
        this.expDate = expDate;
    }

    public License(Long id, Integer number, Date expDate) {
        this.id = id;
        this.number = number;
        this.expDate = expDate;
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

    public java.sql.Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
}
