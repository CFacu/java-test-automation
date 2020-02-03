package com.solvd.uber.models;

import java.sql.Date;

public class CreditCard {
    private Long id;
    private Integer number;
    private Date expDate;
    private Long userId;

    public CreditCard() {
    }

    public CreditCard(Integer number, Date expDate, Long userId) {
        this.number = number;
        this.expDate = expDate;
        this.userId = userId;
    }

    public CreditCard(Long id, Integer number, Date expDate, Long userId) {
        this.id = id;
        this.number = number;
        this.expDate = expDate;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
