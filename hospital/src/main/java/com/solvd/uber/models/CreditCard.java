package com.solvd.uber.models;

import java.sql.Date;

public class CreditCard {
    private Long id;
    private Integer number;
    private Date expDate;
    private User user;

    public CreditCard() {
    }

    public CreditCard(Integer number, Date expDate, User user) {
        this.number = number;
        this.expDate = expDate;
        this.user = user;
    }

    public CreditCard(Long id, Integer number, Date expDate, User user) {
        this.id = id;
        this.number = number;
        this.expDate = expDate;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
