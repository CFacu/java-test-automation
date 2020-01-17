package com.solvd.hospital.people.employee.administrative;

import com.solvd.hospital.people.employee.medical.Doctor;
import com.solvd.hospital.people.employee.medical.Nurse;

public interface IPay {

    void pay(Doctor doctor, Accountant accountant);

    void pay(Nurse nurse, Accountant accountant);

}

