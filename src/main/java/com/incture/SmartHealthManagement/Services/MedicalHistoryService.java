package com.incture.SmartHealthManagement.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.incture.SmartHealthManagement.Dao.MedicalHistoryDao;
import com.incture.SmartHealthManagement.Dao.PatientDao;
import com.incture.SmartHealthManagement.Entities.MedicalHistory;
import com.incture.SmartHealthManagement.Entities.Patient;

@Service
public class MedicalHistoryService 
{
	@Autowired
	MedicalHistoryDao medicalHistoryDao;
	
	@Autowired
	PatientDao patientDao;
	
	public ResponseEntity<String> addMedicalHistory(MedicalHistory medicalHistory, Long patientId)
	{
		Optional<Patient> optional = patientDao.findById(patientId);
		if(optional.isPresent())
		{
			medicalHistory.setPatient(optional.get());
			medicalHistoryDao.save(medicalHistory);
			return new ResponseEntity<String>("Medical History added to database!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Patient not found!", HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<MedicalHistory> getMedicalHistory(Long id)
	{
		Optional<MedicalHistory> optional = medicalHistoryDao.findById(id);
		if(optional.isPresent())
		{
			MedicalHistory medicalHistory = optional.get();
			return new ResponseEntity<MedicalHistory>(medicalHistory, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<MedicalHistory>> getAllMedicalHistory()
	{
		List<MedicalHistory> medicalHistories = medicalHistoryDao.findAll();
		return new ResponseEntity<List<MedicalHistory>>(medicalHistories, HttpStatus.OK);
	}
	
	public ResponseEntity<String> updateMedicalHistory(Long id, MedicalHistory updatedMedicalHistory)
	{
		Optional<MedicalHistory> optional = medicalHistoryDao.findById(id);
		if(optional.isPresent())
		{
			MedicalHistory medicalHistory = optional.get();
			medicalHistory.setPatient(updatedMedicalHistory.getPatient());
			medicalHistory.setRecordDate(updatedMedicalHistory.getRecordDate());
			medicalHistory.setDescription(updatedMedicalHistory.getDescription());
			medicalHistoryDao.save(medicalHistory);
			return new ResponseEntity<String>("Medical History updated!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Medical History not found!", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> deleteMedicalHistory(Long id)
	{
		Optional<MedicalHistory> optional = medicalHistoryDao.findById(id);
		if(optional.isPresent())
		{
			medicalHistoryDao.deleteById(id);
			return new ResponseEntity<String>("Medical History deleted!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Medical History not found!", HttpStatus.NOT_FOUND);
		}
	}
}
