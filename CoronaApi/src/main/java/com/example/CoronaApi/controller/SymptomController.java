package com.example.CoronaApi.controller;

import com.example.CoronaApi.model.request.CovidSymptomsRequest;
import com.example.CoronaApi.model.response.CovidSymptoms;
import com.example.CoronaApi.model.response.GeneralResponse;
import com.example.CoronaApi.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * #SymptomController RestController helps to retrieve patient symptom record
 */
@RestController
@RequestMapping("/api/symptoms")
public class SymptomController {
    @Autowired
    private SymptomRepository symptomRepository;

    //This method helps to retrieve all patient symptom data
    @GetMapping("/")
    public Collection<CovidSymptoms> getAllCovidSymptoms() {
        Collection<CovidSymptoms> allCovidSymptoms = symptomRepository.getAllPatientSymptom();
        Collection<CovidSymptoms> response = new ArrayList<>();
        for (CovidSymptoms covidSymptoms: allCovidSymptoms){
            if(covidSymptoms.hasLinks()){
                covidSymptoms.removeLinks();
            }
            covidSymptoms.add(linkTo(methodOn(SymptomController.class).getSymptomPatientById(covidSymptoms.getSymptomId())).withSelfRel());
            covidSymptoms.add(linkTo(methodOn(PatientsController.class).getPatientById(covidSymptoms.getPatientId())).withRel("patient"));
            response.add(covidSymptoms);
        }
        return response;
    }

    //This method helps to retrieve particular patient symptom data
    @GetMapping("/{patientId}")
    public CovidSymptoms getSymptomPatientById(@PathVariable("patientId") String patientId) {
        CovidSymptoms covidSymptoms = symptomRepository.getSymptomPatientById(patientId);
        if(covidSymptoms.hasLinks()){
            covidSymptoms.removeLinks();
        }
        covidSymptoms.add(linkTo(methodOn(SymptomController.class).getSymptomPatientById(patientId)).withSelfRel());
        return covidSymptoms;
    }


    //This method helps to add particular patient symptom data
    @PostMapping("/addSymptoms")
    public GeneralResponse addPatientSymptom(@RequestBody CovidSymptomsRequest symptoms) {
        return symptomRepository.addPatientSymptom(symptoms);
    }

    //This method helps to delete particular patient symptom data
    @DeleteMapping("delete/{patientId}")
    public GeneralResponse deletePatientSymptom(@PathVariable("patientId") String patientId){
        return symptomRepository.deletePatientSymptom(patientId);
    }

}
