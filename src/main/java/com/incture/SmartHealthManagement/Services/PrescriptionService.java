package com.incture.SmartHealthManagement.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.incture.SmartHealthManagement.Dao.PrescriptionDao;
import com.incture.SmartHealthManagement.Entities.Prescription;

@Service
public class PrescriptionService 
{
	@Autowired
	PrescriptionDao prescriptionDao;
	
	public ResponseEntity<String> addPrescription(Prescription prescription)
	{
		prescriptionDao.save(prescription);
		return new ResponseEntity<String>("Prescription added to database!", HttpStatus.OK);
	}
	public ResponseEntity<Prescription> getPrescription(Long id)
	{
		Optional<Prescription> optional = prescriptionDao.findById(id);
		if(optional.isPresent())
		{
			Prescription prescription = optional.get();
			return new ResponseEntity<Prescription>(prescription, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<List<Prescription>> getAllPrescriptions()
	{
		List<Prescription> prescriptions = prescriptionDao.findAll();
		return new ResponseEntity<List<Prescription>>(prescriptions, HttpStatus.OK);
	}
	public ResponseEntity<String> updatePrescription(Long id, Prescription updatedPrescription)
	{
		Optional<Prescription> optional = prescriptionDao.findById(id);
		if(optional.isPresent())
		{
			Prescription prescription = optional.get();
			prescription.setPatient(updatedPrescription.getPatient());
			prescription.setDoctor(updatedPrescription.getDoctor());
			prescription.setIssueDate(updatedPrescription.getIssueDate());
			prescription.setMedicationDetails(updatedPrescription.getMedicationDetails());
			prescription.setDosageInstruction(updatedPrescription.getDosageInstruction());
			prescriptionDao.save(prescription);
			return new ResponseEntity<String>("Report updated successfully!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Report not found!", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> deletePrescription(Long id)
	{
		Optional<Prescription> optional = prescriptionDao.findById(id);
		if(optional.isPresent())
		{
			prescriptionDao.deleteById(id);
			return new ResponseEntity<String>("Prescription deleted successfully!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Prescription not found!", HttpStatus.NOT_FOUND);
		}
	}
}
