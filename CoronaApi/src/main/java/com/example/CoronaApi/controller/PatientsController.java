package com.example.CoronaApi.controller;

import com.example.CoronaApi.model.response.Doctor;
import com.example.CoronaApi.model.response.GeneralResponse;
import com.example.CoronaApi.model.response.Patient;
import com.example.CoronaApi.model.request.PatientRequest;
import com.example.CoronaApi.repository.DoctorRepository;
import com.example.CoronaApi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * #PatientsController RestController helps to retrieve patient record
 */
@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    //This method helps to retrieve all patient data
    @GetMapping("/")
    public Collection<Patient> getPatientList(){
        Collection<Patient> allPatient = patientRepository.getPatientList();
        Collection<Patient> response = new ArrayList<>();
        for (Patient patient: allPatient){
            if(patient.hasLinks()){
                patient.removeLinks();
            }
            patient.add(linkTo(methodOn(PatientsController.class).getPatientById(patient.getPatientId())).withSelfRel());
            Doctor doctor = doctorRepository.getDoctorById(patient.getDoctorId());
            patient.add(linkTo(methodOn(DoctorController.class).getDoctorById(patient.getDoctorId())).withRel("doctor"));
            patient.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(doctor.getDepartmentId())).withRel("department"));
            patient.add(linkTo(methodOn(SymptomController.class).getSymptomPatientById(patient.getPatientId())).withRel("symptom"));
            response.add(patient);
        }
        return response;
    }

    //This method helps to retrieve particular patient  data
    @GetMapping("/{patientId}")
    public Patient getPatientById(@PathVariable("patientId") String patientId){
        Patient patient = patientRepository.getPatientById(patientId);
        if(patient.hasLinks()){
            patient.removeLinks();
        }
        patient.add(linkTo(methodOn(PatientsController.class).getPatientById(patientId)).withSelfRel());
        Doctor doctor = doctorRepository.getDoctorById(patient.getDoctorId());
        patient.add(linkTo(methodOn(DoctorController.class).getDoctorById(patient.getDoctorId())).withRel("doctor"));
        patient.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(doctor.getDepartmentId())).withRel("department"));
        patient.add(linkTo(methodOn(SymptomController.class).getSymptomPatientById(patientId)).withRel("symptom"));
        return patient;
    }

    //This method helps to add particular patient data
    @PostMapping("/addPatient")
    public GeneralResponse addPatient(@RequestBody PatientRequest patient){
        return patientRepository.addPatient(patient);
    }

    //This method helps to update particular patient data
    @PutMapping("/updatePatient")
    public GeneralResponse updatePatient(@RequestBody PatientRequest patient){
        return patientRepository.updatePatient(patient);
    }

    //This method helps to delete particular patient data
    @DeleteMapping("delete/{patientId}")
    public GeneralResponse deletePatientById(@PathVariable("patientId") String patientId){
        return patientRepository.deletePatientById(patientId);
    }


}

