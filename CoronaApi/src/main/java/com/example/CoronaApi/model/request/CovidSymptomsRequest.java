package com.example.CoronaApi.model.request;


/**
 * CovidSymptoms Request data model
 */
public class CovidSymptomsRequest {
    private String symptomId;
    private String patientId;
    private String visitingDate;
    private Boolean coughing;
    private Boolean fever;

    public String getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(String symptomId) {
        this.symptomId = symptomId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(String visitingDate) {
        this.visitingDate = visitingDate;
    }

    public Boolean getCoughing() {
        return coughing;
    }

    public void setCoughing(Boolean coughing) {
        this.coughing = coughing;
    }

    public Boolean getFever() {
        return fever;
    }

    public void setFever(Boolean fever) {
        this.fever = fever;
    }
}
