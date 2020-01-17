package com.solvd.hospital.hospitals;

import com.solvd.hospital.people.employee.administrative.Accountant;
import com.solvd.hospital.people.employee.administrative.IPay;
import com.solvd.hospital.people.employee.medical.Nurse;
import com.solvd.hospital.people.employee.medical.Doctor;
import com.solvd.hospital.people.Patient;

import java.util.ArrayList;
import java.util.List;

public class Clinic extends AbstractHospital implements IPay {

    private Float nursesHours;
    private Float budget;
    private List<Accountant> accountants = new ArrayList<Accountant>();

    public List<Accountant> getAccountants() {
        return accountants;
    }

    public void setAccountants(List<Accountant> accountants) {
        this.accountants = accountants;
    }

    public void addAccountant(Accountant accountant) {
        this.accountants.add(accountant);
    }

    public Float getNursesHours() {
        return nursesHours;
    }

    public void setNursesHours(Float nursesHours) {
        this.nursesHours = nursesHours;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    @Override
    public void assignPatient(Doctor doctor, Patient patient) {
        if (this.getDoctors().contains(doctor))
            if (this.getPatients().contains(patient))
                doctor.addPatients(patient);
            else System.out.println("This patient does not belong to this clinic.");
        else System.out.println("This doctor does not belong to this clinic.");
    }

    @Override
    public void giveMedicine(Patient patient) {
        if (this.getPatients().contains(patient))
            System.out.println("Medicine given to: " + patient);
        else System.out.println("This patient does not belong to this clinic.");
    }

    public void pay(Doctor doctor, Accountant accountant) {
        if (this.getDoctors().contains(doctor)){
            if (this.getAccountants().contains(accountant)) {
                doctor.addToBankAccount(doctor.getSpeciality().getSalary());
                this.setBudget(this.getBudget() - doctor.getSpeciality().getSalary());
            } else System.out.println("This accountant does not belong to this clinic.");
        } else System.out.println("This doctor does not belong to this clinic.");

    }

    public void pay(Nurse nurse, Accountant accountant) {
        if (this.getNurses().contains(nurse)){
            if (this.getAccountants().contains(accountant)) {
                nurse.addToBankAccount(nurse.getSalary() * nurse.getHoursWorked());
                this.setBudget(this.getBudget() - nurse.getSalary() * nurse.getHoursWorked());
            } else System.out.println("This accountant does not belong to this clinic.");
        } else System.out.println("This doctor does not belong to this clinic.");

    }
}