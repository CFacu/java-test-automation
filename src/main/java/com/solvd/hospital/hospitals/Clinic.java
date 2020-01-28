package com.solvd.hospital.hospitals;

import com.solvd.hospital.people.employee.administrative.Accountant;
import com.solvd.hospital.people.employee.administrative.IPay;
import com.solvd.hospital.people.employee.medical.Nurse;
import com.solvd.hospital.people.employee.medical.Doctor;
import com.solvd.hospital.people.Patient;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class Clinic extends AbstractHospital implements IPay {
    final static Logger LOGGER = Logger.getLogger(Clinic.class);

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
            else LOGGER.warn("This patient does not belong to this clinic.");
        else LOGGER.warn("This doctor does not belong to this clinic.");
    }

    @Override
    public void giveMedicine(Patient patient) {
        if (this.getPatients().contains(patient))
            LOGGER.warn("Medicine given to: " + patient);
        else LOGGER.warn("This patient does not belong to this clinic.");
    }

    public void pay(Doctor doctor, Accountant accountant) {
        if (this.getDoctors().contains(doctor)){
            if (this.getAccountants().contains(accountant)) {
                doctor.addToBankAccount(doctor.getSpeciality().getSalary());
                this.setBudget(this.getBudget() - doctor.getSpeciality().getSalary());
            } else LOGGER.warn("This accountant does not belong to this clinic.");
        } else LOGGER.warn("This doctor does not belong to this clinic.");

    }

    public void pay(Nurse nurse, Accountant accountant) {
        if (this.getNurses().contains(nurse)){
            if (this.getAccountants().contains(accountant)) {
                nurse.addToBankAccount(nurse.getSalary() * nurse.getHoursWorked());
                this.setBudget(this.getBudget() - nurse.getSalary() * nurse.getHoursWorked());
            } else LOGGER.warn("This accountant does not belong to this clinic.");
        } else LOGGER.warn("This doctor does not belong to this clinic.");

    }
}