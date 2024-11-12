package com.incture.SmartHealthManagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.SmartHealthManagement.Entities.Prescription;
import com.incture.SmartHealthManagement.Services.PrescriptionService;

@RestController
@RequestMapping("/api/v1/Prescription")
public class PrescriptionController 
{
	@Autowired
	PrescriptionService prescriptionService;
	
	public ResponseEntity<String> addPrescription(@RequestBody Prescription prescription)
	{
		return prescriptionService.addPrescription(prescription);
	}
}
