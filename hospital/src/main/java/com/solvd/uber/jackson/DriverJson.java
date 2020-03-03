package com.solvd.uber.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonRootName("Driver")
public class DriverJson {
    private Long id;
    private String name;
    private String password;
    @JsonSerialize(using = DateHandler.class)
    private Date birthDate;
    private Integer phoneNumber;
    private Integer rate;
    private LicenseJson license;

    public DriverJson() {
    }

    public DriverJson(String name, String password, Date birthDate, Integer phoneNumber, Integer rate, Integer licenseNumber, Date licenseExpDate) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
        this.license = new LicenseJson(licenseNumber, licenseExpDate);
    }

    public DriverJson(Long id, String name, String password, Date birthDate, Integer phoneNumber, Integer rate, LicenseJson license) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
        this.license = license;
    }

    @JsonGetter("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonGetter("birthDate")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @JsonGetter("phoneNumber")
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonGetter("rate")
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @JsonGetter("license")
    public LicenseJson getLicense() {
        return license;
    }

    public void setLicense(LicenseJson license) {
        this.license = license;
    }
}
