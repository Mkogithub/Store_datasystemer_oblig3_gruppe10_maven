package com.example.CoronaApi.controller;

import com.example.CoronaApi.model.response.GeneralResponse;
import com.example.CoronaApi.model.request.DoctorRequest;
import com.example.CoronaApi.model.response.Doctor;
import com.example.CoronaApi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * #DoctorController RestController helps to retrieve doctor record
 */
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    //This method helps to retrieve all doctor data
    @GetMapping("/")
    public Collection<Doctor> getDoctorList() {
        Collection<Doctor> allDoctor = doctorRepository.getDoctorList();
        Collection<Doctor> response = new ArrayList<>();
        for (Doctor doctor : allDoctor) {
            if(doctor.hasLinks()){
                doctor.removeLinks();
            }
            doctor.add(linkTo(methodOn(DoctorController.class).getDoctorById(doctor.getDoctorId())).withSelfRel());
            doctor.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(doctor.getDepartmentId())).withRel("department"));
            response.add(doctor);
        }
        return response;
    }

    //This method helps to retrieve particular doctor data
    @GetMapping("/{doctorId}")
    public Doctor getDoctorById(@PathVariable("doctorId") String doctorId) {
        Doctor doctor = doctorRepository.getDoctorById(doctorId);
        if(doctor.hasLinks()){
            doctor.removeLinks();
        }
        doctor.add(linkTo(methodOn(DoctorController.class).getDoctorById(doctorId)).withSelfRel());
        doctor.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(doctor.getDepartmentId())).withRel("department"));
        return doctor;
    }

    //This method helps to add particular doctor data
    @PostMapping("/addDoctor")
    public GeneralResponse addDoctor(@RequestBody DoctorRequest patient) {
        return doctorRepository.addDoctor(patient);
    }

    //This method helps to update particular doctor data
    @PutMapping("/updateDoctor")
    public GeneralResponse updateDoctor(@RequestBody DoctorRequest patient) {
        return doctorRepository.updateDoctor(patient);
    }

    //This method helps to delete particular doctor data
    @DeleteMapping("delete/{doctorId}")
    public GeneralResponse deleteDoctorById(@PathVariable("doctorId") String doctorId) {
        return doctorRepository.deleteDoctorById(doctorId);
    }


}
