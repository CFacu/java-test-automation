package com.solvd.uber.stax;

import com.solvd.uber.jackson.LicenseJson;

import java.util.Date;

public class StaxDriver {
    private Long id;
    private String name;
    private String password;
    private Date birthDate;
    private Integer phoneNumber;
    private Integer rate;
    private StaxLicense license;

    public StaxDriver() {
    }

    public StaxDriver(String name, String password, Date birthDate, Integer phoneNumber, Integer rate, Integer licenseNumber, Date licenseExpDate) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
        this.license = new StaxLicense(licenseNumber, licenseExpDate);
    }

    public StaxDriver(Long id, String name, String password, Date birthDate, Integer phoneNumber, Integer rate, StaxLicense license) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
        this.license = license;
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

    public StaxLicense getLicense() {
        return license;
    }

    public void setLicense(StaxLicense license) {
        this.license = license;
    }
}
