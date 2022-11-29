package com.example.CoronaApi.controller;

import com.example.CoronaApi.model.request.DepartmentRequest;
import com.example.CoronaApi.model.response.Department;
import com.example.CoronaApi.model.response.GeneralResponse;
import com.example.CoronaApi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * #DepartmentController RestController hjelper til med å hente department record
 * withSelfRel() default er "self" eks: "rel" : "self", kan skrive eget valg navn som gjøres eks:"department"
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    //This method helps to retrieve all department data
    @GetMapping("/")
    public Collection<Department> getAllDepartment() {
        Collection<Department> allDepartment = departmentRepository.getAllDepartment();
        Collection<Department> response = new ArrayList<>();
        for (Department department: allDepartment){
            if(department.hasLinks()){
                department.removeLinks();
            }
            department.add(linkTo(DepartmentController.class).slash(department.getDepartmentId()).withSelfRel());
            response.add(department);
        }
        return response;
    }

    //This method helps to retrieve particular department data
    @GetMapping("/{departmentId}")
    public Department getDepartmentById(@PathVariable("departmentId") String departmentId) {
        Department department = departmentRepository.getDepartmentById(departmentId);
        if(department.hasLinks()){
            department.removeLinks();
        }
        department.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(departmentId)).withSelfRel());
        return department;
    }

    //This method helps to add particular department data
    @PostMapping("/addDepartment")
    public GeneralResponse addDepartment(@RequestBody DepartmentRequest department) {
        return departmentRepository.addDepartment(department);
    }

    //This method helps to delete particular department data
    @DeleteMapping("delete/{departmentId}")
    public GeneralResponse deleteDepartment(@PathVariable("departmentId") String departmentId){
        return departmentRepository.deleteDepartment(departmentId);
    }

}
