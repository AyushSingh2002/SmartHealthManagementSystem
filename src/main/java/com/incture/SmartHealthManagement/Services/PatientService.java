package com.incture.SmartHealthManagement.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.incture.SmartHealthManagement.Dao.PatientDao;
import com.incture.SmartHealthManagement.Dao.UserDao;
import com.incture.SmartHealthManagement.Entities.Patient;
import com.incture.SmartHealthManagement.Entities.User;

@Service
public class PatientService 
{
	@Autowired
	private PatientDao patientDao;
	
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<String> addPatient(Patient patient, Long userId)
	{
		Optional<User> optional = userDao.findById(userId);
		if(optional.isPresent())
		{
			patient.setUser(optional.get());
			patientDao.save(patient);
			return new ResponseEntity<String>("Patient profile created!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("User not found!", HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Patient> getPatient(Long id)
	{
		Optional<Patient> optionalPatient = patientDao.findById(id);
		if(optionalPatient.isPresent())
		{
			Patient patient = optionalPatient.get();
			return new ResponseEntity<Patient>(patient, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<Patient>> getAllPatients()
	{
		List<Patient> patients = patientDao.findAll();
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}
	
	public ResponseEntity<String> updatePatient(Long id, Patient updatedPatient)
	{
		Optional<Patient> optionalPatient = patientDao.findById(id);
		if(optionalPatient.isPresent())
		{
			Patient patient = optionalPatient.get();
			patient.setFirstName(updatedPatient.getFirstName());
			patient.setLastName(updatedPatient.getLastName());
			patient.setUserName(updatedPatient.getUserName());
			patient.setEmail(updatedPatient.getEmail());
			patient.setDateOfBirth(updatedPatient.getDateOfBirth());
			patient.setUser(updatedPatient.getUser());
			patient.setMedicalHistories(updatedPatient.getMedicalHistories());
			patient.setAppointments(updatedPatient.getAppointments());
			patientDao.save(patient);
			return new ResponseEntity<String>("Patient profile updated successfully!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Patient profile not found!", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> deletePatient(Long id)
	{
		Optional<Patient> optionalPatient = patientDao.findById(id);
		if(optionalPatient.isPresent())
		{
			patientDao.deleteById(id);
			return new ResponseEntity<String>("Patient profile removed successfully!", HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Patient profile not found!", HttpStatus.NOT_FOUND);
		}
	}
}
