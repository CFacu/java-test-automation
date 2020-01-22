package com.solvd.hospital;

import com.solvd.hospital.exceptions.NameNotFoundException;
import com.solvd.hospital.exceptions.NameNullException;
import com.solvd.hospital.hospitals.Clinic;
import com.solvd.hospital.hospitals.Hospital;
import com.solvd.hospital.people.employee.medical.Doctor;
import com.solvd.hospital.people.employee.medical.Speciality;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class MainHospital {

    final static Logger LOGGER = Logger.getLogger(MainHospital.class);

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

        Doctor pathologist = new Doctor();
        pathologist.setName("Joe");
        pathologist.setLastName("Johnson");
        pathologist.setSpeciality(Speciality.PATHOLOGY);
        pathologist.setAge(35);
        hospital.addDoctor(pathologist);

        Doctor surgeon = new Doctor();
        surgeon.setName("Stephen");
        surgeon.setLastName("Strange");
        surgeon.setSpeciality(Speciality.SURGERY);
        surgeon.setAge(45);
        clinic.addDoctor(surgeon);

        System.out.println("Doctors: " + hospital.getDoctors());

        System.out.println(hospital.searchDoctor("Joe"));
    }
}