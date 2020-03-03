package com.solvd.uber.stax;

import java.util.Date;

public class StaxLicense {
    private Long id;
    private Integer number;
    private Date expDate;

    public StaxLicense() {
    }

    public StaxLicense(Integer number, Date expDate) {
        this.number = number;
        this.expDate = expDate;
    }

    public StaxLicense(Long id, Integer number, Date expDate) {
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

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
}
