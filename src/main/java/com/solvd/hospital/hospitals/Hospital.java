package com.solvd.hospital.hospitals;

import com.solvd.hospital.people.employee.medical.Doctor;
import com.solvd.hospital.people.Patient;

public class Hospital extends AbstractHospital {

    @Override
    public void assignPatient(Doctor doctor, Patient patient) {
        if (this.getDoctors().contains(doctor)) {
            if (this.getPatients().contains(patient)) {
                doctor.addPatients(patient);
            }
            else System.out.println("This patient does not belong to this hospital.");
        }
        else System.out.println("This doctor does not belong to this hospital.");
    }

    @Override
    public void giveMedicine(Patient patient) {
        if (this.getPatients().contains(patient))
            System.out.println("Medicine given to: " + patient);
        else System.out.println("This patient does not belong to this hospital.");
    }
}
