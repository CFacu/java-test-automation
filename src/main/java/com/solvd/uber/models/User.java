package com.solvd.uber.models;

public class User {
    private Long id;
    private String name;
    private Integer age;
    private Integer rate;
    private String password;
    private Integer phoneNumber;
    private String location;

    public User() {
    }

    public User(String name, Integer age, Integer rate, String password, Integer phoneNumber, String location) {
        this.name = name;
        this.age = age;
        this.rate = rate;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public User(Long id,String name, Integer age, Integer rate, String password, Integer phoneNumber, String location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.rate = rate;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.location = location;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
