package com.incture.SmartHealthManagement.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.incture.SmartHealthManagement.Dao.DoctorDao;
import com.incture.SmartHealthManagement.Dao.UserDao;
import com.incture.SmartHealthManagement.Entities.Doctor;
import com.incture.SmartHealthManagement.Entities.User;

@Service
public class DoctorService 
{
	@Autowired
	DoctorDao doctorDao;
	
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<String> addDoctor(Doctor doctor, Long userId)
	{
		Optional<User> optional = userDao.findById(userId);
		if(optional.isPresent())
		{
			doctor.setUser(optional.get());
			doctorDao.save(doctor);
			return new ResponseEntity<String>("Doctor profile created!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("User not found!", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Doctor> getDoctor(Long id)
	{
		Optional<Doctor> optionalDoctor = doctorDao.findById(id);
		if(optionalDoctor.isPresent())
		{
			Doctor doctor = optionalDoctor.get();
			return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<Doctor>> getAllDoctors()
	{
		List<Doctor> doctors = doctorDao.findAll();
		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
	}
	
	public ResponseEntity<String> updateDoctor(Long id, Doctor updatedDoctor)
	{
		Optional<Doctor> optionalDoctor = doctorDao.findById(id);
		if(optionalDoctor.isPresent())
		{
			Doctor doctor = optionalDoctor.get();
			doctor.setFirstName(updatedDoctor.getFirstName());
			doctor.setLastName(updatedDoctor.getLastName());
			doctor.setSpeciality(updatedDoctor.getSpeciality());
			doctor.setContactNumber(updatedDoctor.getContactNumber());
			doctor.setUser(updatedDoctor.getUser());
			doctor.setAppointments(updatedDoctor.getAppointments());
			doctorDao.save(doctor);
			return new ResponseEntity<String>("Doctor profile updated successfully!", HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Doctor profile not found!", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> deleteDoctor(Long id)
	{
		Optional<Doctor> optionalDoctor = doctorDao.findById(id);
		if(optionalDoctor.isPresent())
		{
			doctorDao.deleteById(id);
			return new ResponseEntity<String>("Doctor profile deleted successfully!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Doctor profile not found!", HttpStatus.NOT_FOUND);
		}
	}
}
