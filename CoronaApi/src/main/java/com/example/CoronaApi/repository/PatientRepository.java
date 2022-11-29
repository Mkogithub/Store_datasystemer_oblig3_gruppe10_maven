package com.example.CoronaApi.repository;

import com.example.CoronaApi.model.response.GeneralResponse;
import com.example.CoronaApi.model.response.Patient;
import com.example.CoronaApi.model.request.PatientRequest;
import com.example.CoronaApi.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Patient Repository used to retrieve,update and delete data
 */
@Component
public class PatientRepository {

    private final static Map<String, Patient> patientMap = new HashMap<>();
    private int patientId = 0;

    @Autowired
    private ObjectConverter objectConverter;

    public Collection<Patient> getPatientList() {
        return patientMap.values();
    }

    public Patient getPatientById(String patientId) {
        return patientMap.get(patientId);
    }


    public GeneralResponse deletePatientById(String patientId) {
        patientMap.remove(patientId);
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setId(patientId);
        generalResponse.setResult("Success");
        return generalResponse;
    }

    public GeneralResponse addPatient(PatientRequest patient) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            patientId++;
            patient.setPatientId("p" + patientId);
            patientMap.put("p" + patientId, objectConverter.from(objectConverter.toJson(patient), Patient.class));
            generalResponse.setId("p" + patientId);
            generalResponse.setResult("Success");
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }

    public GeneralResponse updatePatient(PatientRequest patient) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            patientMap.replace(patient.getPatientId(), objectConverter.from(objectConverter.toJson(patient), Patient.class));
            generalResponse.setId(patient.getPatientId());
            generalResponse.setResult("Success");
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }
}
