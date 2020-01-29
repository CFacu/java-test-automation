package com.solvd.hospital.people;

import com.solvd.hospital.people.employee.medical.Disease;

public class Patient extends Person{

    private Disease disease;

    public Patient() {}

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    @Override
    public String getInfo() {
        return "Patient: " + this.getName() + " " + this.getLastName() +
                "\nAge: "+ this.getAge() +
                "\nDisease: " + getDisease();
    }
}