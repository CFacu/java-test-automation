package com.solvd.hospital.people.employee.medical;

import com.solvd.hospital.people.Patient;
import com.solvd.hospital.people.Person;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person implements IHeal{

    private Speciality speciality;
    private long npi; //National Provider Identifier(10 digit identification number for medics)
    private List<Patient> patients = new ArrayList<>();
    private Float bankAccount;
    private Integer hoursWorked;

    public Doctor() {
        this.npi = (long)Math.floor(Math.random() * 9999999999L);
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Long getNpi() {
        return npi;
    }

    public void addPatients(Patient patient) {
        patients.add(patient);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public Float getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Float bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getInfo() {
        return "Doctor: " + this.getName() + " " + this.getLastName() +
                "\nAge: "+ this.getAge() +
                "\nSpeciality: " + getSpeciality() +
                "\nNPI: " + this.getNpi();
    }

    @Override
    public void healPatient(Patient patient) {
        if (this.patients.contains(patient))
            if (DISEASES.containsKey(patient.getDisease())) {
                System.out.println("How to heal the patient: " + DISEASES.get(patient.getDisease()));
            }
            else System.out.println("Sorry, the disease is not in our database.");
        else System.out.println("This patient does not belong to this doctor.");
    }

    public void addToBankAccount(Float amount) {
        this.bankAccount += amount;
    }

    public void surgery (Patient patient) {
        if (this.speciality.equals(Speciality.SURGERY)) {
            System.out.println("Doing surgery to: " + patient);
        } else System.out.println("Only a surgeon can do surgery.");
    }

}