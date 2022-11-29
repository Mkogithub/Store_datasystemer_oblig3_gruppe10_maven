package com.example.CoronaApi.repository;

import com.example.CoronaApi.model.request.DoctorRequest;
import com.example.CoronaApi.model.response.Doctor;
import com.example.CoronaApi.model.response.GeneralResponse;
import com.example.CoronaApi.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Doctor Repository used to retrieve,update and delete data
 */
@Component
public class DoctorRepository {
    private final static Map<String, Doctor> doctorMap = new HashMap<>();
    private int doctorId = 0;

    @Autowired
    private ObjectConverter objectConverter;

    public Collection<Doctor> getDoctorList() {
        return doctorMap.values();
    }

    public Doctor getDoctorById(String doctorId) {
        return doctorMap.get(doctorId);
    }

    public GeneralResponse deleteDoctorById(String doctorId) {
        doctorMap.remove(doctorId);
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setId(doctorId);
        generalResponse.setResult("Success");
        return generalResponse;
    }


    public GeneralResponse addDoctor(DoctorRequest doctor) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            doctorId++;
            doctor.setDoctorId("dc"+doctorId);
            doctorMap.put("dc"+doctorId, objectConverter.from(objectConverter.toJson(doctor), Doctor.class));
            generalResponse.setId("dc"+doctorId);
            generalResponse.setResult("Success");
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }
    public GeneralResponse updateDoctor(DoctorRequest doctor) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            doctorMap.replace(doctor.getDoctorId(), objectConverter.from(objectConverter.toJson(doctor), Doctor.class));
            generalResponse.setId(doctor.getDoctorId());
            generalResponse.setResult("Success");
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }
}