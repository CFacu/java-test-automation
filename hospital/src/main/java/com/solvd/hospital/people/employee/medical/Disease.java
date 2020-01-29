package com.solvd.hospital.people.employee.medical;

public enum Disease {
    CELIAC ("Vitamins; Dietary supplements"),
    HYPERTENSION ("ACE inhibitor; Diuretic; Beta blocker; Antihypertensive drug; Calcium channel blocker; Vasodilator."),
    ASTHMA ("Bronchodilator; Steroid; Anti-inflammatory."),
    CANCER ("Cabazitaxel; Capecitabine; Chlorambucil; Amsacrine; Doxorubicin."),
    CHRONIC_BRONCHITIS ("Analgesic; Narcotic; Cough medicine; Nonsteroidal anti-inflammatory drug."),
    DIABETES_TYPE_1 ("Insulin, Dietary supplements; Hormone."),
    DIABETES_TYPE_2 ("Anti-diabetic medication; Blood thinners; Statin; Insulin");

    private String medication;

    Disease(String medication) {
        this.medication = medication;
    }

    public String getMediation () {
        return this.medication;
    }
}
