package com.solvd.hospital.people.employee.medical;

import com.solvd.hospital.people.Person;

public class Nurse extends Person {

    private Float bankAccount;
    private Integer hoursWorked;
    private Float salary;

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Float getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Float bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void addToBankAccount(Float amount) {
        this.bankAccount += amount;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getInfo() {
        return "Nurse: " + this.getName() + " " + this.getLastName() +
                "\nAge: "+ this.getAge();
    }

    public void helpDoctor(Doctor doctor) {
        System.out.println("Helping Doctor " + doctor.getLastName());
    }

}
