package com.solvd.uber.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.uber.jackson.DateHandler;
import com.solvd.uber.jaxb.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;

@XmlRootElement(name = "license")
@XmlAccessorType(XmlAccessType.FIELD)
public class License {
    @XmlAttribute(name = "id")
    private Long id;
    @XmlElement(name = "number")
    private Integer number;
    @XmlElement(name = "expDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonSerialize(using = DateHandler.class)
    private Date expDate;
    @XmlElement(name = "driverId")
    private Long driverId;

    public License() {
    }

    public License(Integer number, Date expDate, Long driverId) {
        this.number = number;
        this.expDate = expDate;
        this.driverId = driverId;
    }

    public License(Long id, Integer number, Date expDate, Long driverId) {
        this.id = id;
        this.number = number;
        this.expDate = expDate;
        this.driverId = driverId;
    }

    @JsonGetter("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonGetter("number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @JsonGetter("expDate")
    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    @JsonGetter("driverId")
    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
