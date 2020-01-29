package com.solvd.hospital.people.employee.medical;

import com.solvd.hospital.people.Patient;

import static java.util.Map.entry;
import java.util.Map;

public interface IHeal {

    Map<Disease, String> DISEASES = Map.ofEntries(entry(Disease.CELIAC,"A gluten-free diet, pneumococcal vaccine (preventive vaccine)."),
            entry(Disease.HYPERTENSION, "Avoid foods high in saturated fat and sugar, more exercise, stop smoking."),
            entry(Disease.ASTHMA,"Bronchodilator, steroids, anti-inflammatory, stop smoking."),
            entry(Disease.CANCER, "Surgery to try to remove the cancer, chemotherapy after surgery when there is a risk that cancer cells could have spread to another part of the body."),
            entry(Disease.CHRONIC_BRONCHITIS, "Analgesics, narcotics, cough medicine, stop smoking."),
            entry(Disease.DIABETES_TYPE_1,"Insulin, dietary suplements, hormones, diabetic diet, physical exercise."),
            entry(Disease.DIABETES_TYPE_2,"Insulin, anti-diabetic medication, statin, blood thinners, physical exercise, diabetic diet."));

    void healPatient(Patient patient);
}
