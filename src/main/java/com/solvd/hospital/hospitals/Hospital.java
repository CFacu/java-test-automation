package com.solvd.hospital.hospitals;

import com.solvd.hospital.people.employee.medical.Doctor;
import com.solvd.hospital.people.Patient;
import org.apache.log4j.Logger;

public class Hospital extends AbstractHospital {

    final static Logger LOGGER = Logger.getLogger(Hospital.class);

    @Override
    public void assignPatient(Doctor doctor, Patient patient) {
        if (this.getDoctors().contains(doctor)) {
            if (this.getPatients().contains(patient)) {
                doctor.addPatients(patient);
            }
            else LOGGER.warn("This patient does not belong to this hospital.");
        }
        else LOGGER.warn("This doctor does not belong to this hospital.");
    }

    @Override
    public void giveMedicine(Patient patient) {
        if (this.getPatients().contains(patient))
            LOGGER.warn("Medicine given to: " + patient);
        else LOGGER.warn("This patient does not belong to this hospital.");
    }
}
