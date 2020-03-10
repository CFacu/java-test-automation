package com.solvd.uber.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.uber.daos.mysql.LicenseDAO;
import com.solvd.uber.jackson.DateHandler;
import com.solvd.uber.jaxb.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;

@XmlRootElement(name = "driver")
@XmlAccessorType(XmlAccessType.FIELD)
public class Driver {
    @JsonProperty("id")
    @XmlAttribute(name = "id")
    private Long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "password")
    private String password;
    @JsonSerialize(using = DateHandler.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "birthDate")
    private Date birthDate;
    @XmlElement(name = "phoneNumber")
    private Integer phoneNumber;
    @XmlElement(name = "rate")
    private Integer rate;
    @XmlElement(name = "license")
    private License license;

    public Driver() {
    }

    public Driver(String name, String password, Date birthDate, Integer phoneNumber, Integer rate, Integer number, Date expDate) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
        this.license = new License(number, expDate, this.id);
    }

    public Driver(Long id, String name, String password, Date birthDate, Integer phoneNumber, Integer rate, Integer number, Date expDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
        this.license = new License(number, expDate, this.id);
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
    public License getLicense() {
        LicenseDAO licenseDAO = new LicenseDAO();
        return licenseDAO.getByDriverId(this.id);
    }

    public void setLicense(License license) {
        this.license = license;
    }
}
