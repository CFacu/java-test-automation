package com.solvd.uber.models;

import java.sql.Date;

public class Driver {
    private Long id;
    private String name;
    private String password;
    private Date birthDate;
    private Integer phoneNumber;
    private Integer rate;

    public Driver() {
    }

    public Driver(String name, String password, Date birthDate, Integer phoneNumber, Integer rate) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
    }

    public Driver(Long id, String name, String password, Date birthDate, Integer phoneNumber, Integer rate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
