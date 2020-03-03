package com.solvd.uber.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonRootName("License")
public class LicenseJson {
    private Long id;
    private Integer number;
    @JsonSerialize(using = DateHandler.class)
    private Date expDate;

    public LicenseJson() {
    }

    public LicenseJson(Integer number, Date expDate) {
        this.number = number;
        this.expDate = expDate;
    }

    public LicenseJson(Long id, Integer number, Date expDate) {
        this.id = id;
        this.number = number;
        this.expDate = expDate;
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
}