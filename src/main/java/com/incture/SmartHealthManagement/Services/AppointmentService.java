package com.incture.SmartHealthManagement.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.incture.SmartHealthManagement.Dao.AppointmentDao;
import com.incture.SmartHealthManagement.Dao.DoctorDao;
import com.incture.SmartHealthManagement.Dao.PatientDao;
import com.incture.SmartHealthManagement.Entities.Appointment;
import com.incture.SmartHealthManagement.Entities.Doctor;
import com.incture.SmartHealthManagement.Entities.Patient;

@Service
public class AppointmentService 
{
	@Autowired
	AppointmentDao appointmentDao;
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private PatientDao patientDao;
	
	public ResponseEntity<String> addAppointment(Appointment appointment, Long doctorId, Long patientId)
	{
		Optional<Doctor> optionalDoctor = doctorDao.findById(doctorId);
		Optional<Patient> optionalPatient = patientDao.findById(patientId);
		if(optionalDoctor.isPresent() && optionalPatient.isPresent())
		{
			appointment.setDoctor(optionalDoctor.get());
			appointment.setPatient(optionalPatient.get());
			appointmentDao.save(appointment);
			return new ResponseEntity<String>("Appointment scheduled!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Either Doctor or Patient is not found!", HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Appointment> getAppointment(Long id)
	{
		Optional<Appointment> optionalAppointment = appointmentDao.findById(id);
		if(optionalAppointment.isPresent())
		{
			Appointment appointment = optionalAppointment.get();
			return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<Appointment>> getAllAppointments()
	{
		List<Appointment> appointments = appointmentDao.findAll();
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}
	
	public ResponseEntity<String> updateAppointment(Long id, Appointment updatedAppointment)
	{
		Optional<Appointment> optionalAppointment = appointmentDao.findById(id);
		if(optionalAppointment.isPresent())
		{
			Appointment appointment = optionalAppointment.get();
			appointment.setPatient(updatedAppointment.getPatient());
			appointment.setDoctor(updatedAppointment.getDoctor());
			appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
			appointment.setAppointmentStatus(updatedAppointment.getAppointmentStatus());
			appointmentDao.save(appointment);
			return new ResponseEntity<String>("Appointment details updated!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Appointment not scheduled!", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> deleteAppointment(Long id)
	{
		Optional<Appointment> optionalAppointment = appointmentDao.findById(id);
		if(optionalAppointment.isPresent())
		{
			appointmentDao.deleteById(id);
			return new ResponseEntity<String>("Appointment deleted successfully!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Appointment not scheduled!", HttpStatus.NOT_FOUND);
		}
	}
}
