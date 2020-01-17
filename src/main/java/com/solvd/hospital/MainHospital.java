package com.solvd.hospital;

import com.solvd.hospital.exceptions.NameNotFoundException;
import com.solvd.hospital.exceptions.NameNullException;
import com.solvd.hospital.hospitals.Clinic;
import com.solvd.hospital.hospitals.Hospital;
import com.solvd.hospital.people.employee.medical.Doctor;
import com.solvd.hospital.people.employee.medical.Speciality;

import java.util.Scanner;

public class MainHospital {

    public static void main(String[] args) throws NameNullException, NameNotFoundException {
        Scanner scan = new Scanner(System.in);

        Hospital hospital = new Hospital();
        hospital.setHospitalName("4 of June");

        Clinic clinic = new Clinic();
        clinic.setHospitalName("Saint Mary");

        Doctor pediatrician = new Doctor();
        pediatrician.setName("Facundo");
        pediatrician.setLastName("Costa");
        pediatrician.setSpeciality(Speciality.PEDIATRICS);
        pediatrician.setAge(30);
        hospital.addDoctor(pediatrician);

        Doctor surgeon = new Doctor();
        surgeon.setName("Stephen");
        surgeon.setLastName("Strange");
        surgeon.setSpeciality(Speciality.SURGERY);
        surgeon.setAge(45);
        clinic.addDoctor(surgeon);

        /*System.out.println("Doctors: " + hospital.getDoctors());

        Patient patient = new Patient();
        patient.setName("John");
        patient.setLastName("Doe");
        patient.setAge(11);
        patient.setDisease(Disease.DIABETES_TYPE_1);

        Patient patientTwo = new Patient();
        patientTwo.setName("Peter");
        patientTwo.setLastName("Parker");
        patientTwo.setAge(11);
        patientTwo.setDisease(Disease.CANCER);

        hospital.addPatient(patientTwo);
        hospital.assignPatient(pediatrician,patientTwo);

        System.out.println(hospital.getPatients());

        pediatrician.healPatient(patientTwo);

        try {
            hospital.searchDoctor(scan.next());
        } catch (NameNullException e) {
            e.printStackTrace();
        } catch (NameNotFoundException a) {
            a.printStackTrace();
        }*/

        System.out.println(hospital.searchDoctor("Facundo"));

    }
}