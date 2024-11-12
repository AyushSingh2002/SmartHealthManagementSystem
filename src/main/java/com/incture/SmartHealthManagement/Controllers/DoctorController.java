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

import com.incture.SmartHealthManagement.Entities.Doctor;
import com.incture.SmartHealthManagement.Services.DoctorService;

@RestController
@RequestMapping("/api/v1/Doctor")
public class DoctorController 
{
	@Autowired
	DoctorService doctorService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorController.class);
	
	@PostMapping("/add-doctor")
	public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor, @RequestParam Long userId)
	{
		LOGGER.info("add-doctor endpoint used.");
		return doctorService.addDoctor(doctor, userId);
	}
	
	@GetMapping("/get-all-doctors")
	public ResponseEntity<List<Doctor>> getAllDoctors()
	{
		LOGGER.info("get-all-doctor endpoint used.");
		return doctorService.getAllDoctors();
	}
	
	@GetMapping("/get-doctor/{id}")
	public ResponseEntity<Doctor> getDoctor(@PathVariable Long id)
	{
		LOGGER.info("get-doctor endpoint used.");
		return doctorService.getDoctor(id);
	}
	
	@PutMapping("/update-doctor/{id}")
	public ResponseEntity<String> updateDoctor(@RequestBody Doctor doctor, @PathVariable Long id)
	{
		LOGGER.info("update-doctor endpoint used.");
		return doctorService.updateDoctor(id, doctor);
	}
	
	@DeleteMapping("/delete-doctor/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable Long id)
	{
		LOGGER.info("delete-doctor endpoint used.");
		return doctorService.deleteDoctor(id);
	}
}
