package com.solvd.hospital.people.employee.medical;

public enum Speciality {
    PEDIATRICS (18500f),
    SURGERY (50000f),
    ALLERGY (17220f),
    ANESTHESIOLOGY (36250f),
    DERMATOLOGY (38000f),
    NEUROLOGY (24800f),
    GYNECOLOGY(23750f),
    OPHTHALMOLOGY (20483f),
    PATHOLOGY (19458f),
    PSYCHIATRY (16882f),
    ONCOLOGY (20468f),
    UROLOGY (27432f);

    private Float salary;

    Speciality(Float salary) {
        this.salary = salary;
    }

    public Float getSalary() {
        return this.salary;
    }
}