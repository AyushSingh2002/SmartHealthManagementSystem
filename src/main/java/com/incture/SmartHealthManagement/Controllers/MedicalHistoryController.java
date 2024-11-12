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

import com.incture.SmartHealthManagement.Entities.MedicalHistory;
import com.incture.SmartHealthManagement.Services.MedicalHistoryService;

@RestController
@RequestMapping("/api/v1/MedicalHistory")
public class MedicalHistoryController 
{
	@Autowired
	MedicalHistoryService medicalHistoryService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MedicalHistoryController.class);
	
	@PostMapping("/add-medicalHistory")
	public ResponseEntity<String> addMedicalHistory(@RequestBody MedicalHistory medicalHistory, @RequestParam Long patientId)
	{
		LOGGER.info("add-medicalHistory endpoint used.");
		return medicalHistoryService.addMedicalHistory(medicalHistory, patientId);
	}
	
	@GetMapping("/get-all-medicalHistories")
	public ResponseEntity<List<MedicalHistory>> getAllMedicalHistories()
	{
		LOGGER.info("get-all-medicalHistory endpoint used.");
		return medicalHistoryService.getAllMedicalHistory();
	}
	
	@GetMapping("/get-medicalHistory/{id}")
	public ResponseEntity<MedicalHistory> getMedicalHistory(@PathVariable Long id)
	{
		LOGGER.info("get-medicalHistory endpoint used.");
		return medicalHistoryService.getMedicalHistory(id);
	}
	
	@PutMapping("/update-medicalHistory/{id}")
	public ResponseEntity<String> updateMedicalHistory(@RequestBody MedicalHistory medicalHistory, @PathVariable Long id)
	{
		LOGGER.info("update-medicalHistory endpoint used.");
		return medicalHistoryService.updateMedicalHistory(id, medicalHistory);
	}
	
	@DeleteMapping("/delete-medicalHistory/{id}")
	public ResponseEntity<String> deleteMedicalHistory(@PathVariable Long id)
	{
		LOGGER.info("delete-medicalHistory endpoint used.");
		return medicalHistoryService.deleteMedicalHistory(id);
	}
}
