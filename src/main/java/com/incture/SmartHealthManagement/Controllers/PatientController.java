package com.incture.SmartHealthManagement.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incture.SmartHealthManagement.Entities.Patient;
import com.incture.SmartHealthManagement.Services.PatientService;


@RestController
@RequestMapping("/api/v1/Patient")
public class PatientController 
{
	@Autowired
	PatientService patientService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
	
	@PostMapping("/add-patient")
	public ResponseEntity<String> addPatient(@RequestBody Patient patient, @RequestParam Long userId)
	{
		LOGGER.info("add-Patient endpoint used.");
		return patientService.addPatient(patient, userId);
	}
	
	@GetMapping("get-all-patients")
	public ResponseEntity<List<Patient>> getAllPatients()
	{
		LOGGER.info("get-all-Patient endpoint used.");
		return patientService.getAllPatients();
	}
	
	@GetMapping("/get-patient/{id}")
	public ResponseEntity<Patient> getPatient(@PathVariable Long id)
	{
		LOGGER.info("get-Patient endpoint used.");
		return patientService.getPatient(id);
	}
	
	@PutMapping("/update-patient/{id}")
	public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient patient)
	{
		LOGGER.info("update-Patient endpoint used.");
		return patientService.updatePatient(id, patient);
	}
	
	@DeleteMapping("/delete-patient/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable Long id)
	{
		LOGGER.info("delete-Patient endpoint used.");
		return patientService.deletePatient(id);
	}
}
