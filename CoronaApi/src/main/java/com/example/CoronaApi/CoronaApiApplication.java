package com.example.CoronaApi;

import com.example.CoronaApi.model.request.CovidSymptomsRequest;
import com.example.CoronaApi.model.request.DepartmentRequest;
import com.example.CoronaApi.model.request.DoctorRequst;
import com.example.CoronaApi.model.request.PatientRequest;
import com.example.CoronaApi.repository.DepartmentRepository;
import com.example.CoronaApi.repository.DoctorRepository;
import com.example.CoronaApi.repository.PatientRepository;
import com.example.CoronaApi.repository.SymptomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
//@EnableSwagger2
public class CoronaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaApiApplication.class, args);
	}

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.example.CoronaApi")).build();
//	}

	@Bean
	public CommandLineRunner clr(PatientRepository patientRepository, DepartmentRepository departmentRepository, DoctorRepository doctorRepository, SymptomRepository symptomRepository) {
		return args -> {

			DepartmentRequest dept3 = new DepartmentRequest("d1", "Akutten");
			DepartmentRequest dept4 = new DepartmentRequest("d2", "Legevakten");

			DoctorRequst doctor3 = new DoctorRequst("d1", "d1", "Joel Odinaka");
			DoctorRequst doctor4 = new DoctorRequst("d2", "d2", "Lege Legesen");

			PatientRequest patient3 = new PatientRequest("p1", "27-11-2022", "John Deere", "27-11-2022", "Sore Throat", "Sore Throat", "d1");
			PatientRequest patient4 = new PatientRequest("p2", "27-11-2022", "Jane Doe", "27-11-2022", "Sore Throat", "Sore Throat, bad breath", "d1");

			Date date = new Date();
			CovidSymptomsRequest symptomsRequest1 = new CovidSymptomsRequest("1", "p1", date.toString(), true, false);
			CovidSymptomsRequest symptomsRequest2 = new CovidSymptomsRequest("2", "p2", date.toString(), true, false);

			patientRepository.addPatient(patient3);
			patientRepository.addPatient(patient4);

			departmentRepository.addDepartment(dept3);
			departmentRepository.addDepartment(dept4);

			doctorRepository.addDoctor(doctor3);
			doctorRepository.addDoctor(doctor4);

			symptomRepository.addPatientSymptom(symptomsRequest1);
			symptomRepository.addPatientSymptom(symptomsRequest2);




		};

	}
}