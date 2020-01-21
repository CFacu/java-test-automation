package com.solvd.hospital.hospitals;

import com.solvd.hospital.exceptions.NameNotFoundException;
import com.solvd.hospital.exceptions.NameNullException;
import com.solvd.hospital.people.employee.medical.Nurse;
import com.solvd.hospital.people.employee.medical.Doctor;
import com.solvd.hospital.people.Patient;

import java.util.*;

import org.apache.log4j.Logger;


public abstract class AbstractHospital {

    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Nurse> nurses = new ArrayList<>();
    private String hospitalName;
    private Scanner scan = new Scanner(System.in);

    final static Logger LOGGER = Logger.getLogger(AbstractHospital.class);

    public AbstractHospital(){};

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addDoctor(Doctor doctor) {
        this.doctors.add(doctor);
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(ArrayList<Nurse> nurse) {
        this.nurses = nurse;
    }

    public void addNurses(Nurse nurse) {
        this.nurses.add(nurse);
    }

    abstract public void assignPatient (Doctor doctor, Patient patient);

    abstract public void giveMedicine (Patient patient);


    public void addHours (Doctor doctor) {

        try {
            System.out.println("Enter the hours: ");
            Integer hours = scan.nextInt();
            doctor.setHoursWorked( hours);
        } catch (InputMismatchException e) {
            LOGGER.error("You must enter an Integer.",e);
        }

    }

    @Override
    public String toString() {
        return this.getHospitalName();
    }

    public Optional<Doctor> searchDoctor (String name) throws NameNullException, NameNotFoundException {
        if (this.doctors.stream().noneMatch(doc -> name.equals(doc.getName()))) throw new NameNotFoundException("The doctor is not in our database");
        else return (this.doctors.stream().sorted().filter(doc -> name.equals(doc.getName())).findAny());
    }
}