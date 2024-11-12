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

import com.incture.SmartHealthManagement.Entities.Appointment;
import com.incture.SmartHealthManagement.Services.AppointmentService;

@RestController
@RequestMapping("/api/v1/Appointment")
public class AppointmentController 
{
	@Autowired
	private AppointmentService appointmentService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
	
	@PostMapping("/add-appointment")
	public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment, @RequestParam Long doctorId, @RequestParam Long patientId)
	{
		LOGGER.info("add-appointment endpoint used.");
		return appointmentService.addAppointment(appointment, doctorId, patientId);
	}
	
	@GetMapping("/get-appointment/{id}")
	public ResponseEntity<Appointment> getAppointment(@PathVariable Long id)
	{
		LOGGER.info("get-appointment/{id} endpoint used.");
		return appointmentService.getAppointment(id);
	}
	
	@GetMapping("/get-all-appointments")
	public ResponseEntity<List<Appointment>> getAllAppointments()
	{
		LOGGER.info("get-all-appointments endpoint used.");
		return appointmentService.getAllAppointments();
	}
	
	@PutMapping("/update-appointment/{id}")
	public ResponseEntity<String> updateAppointment(@RequestBody Appointment appointment, @PathVariable Long id)
	{
		LOGGER.info("update-appointment/{id} endpoint used.");
		return appointmentService.updateAppointment(id, appointment);
	}
	
	@DeleteMapping("/delete-appointment/{id}")
	public ResponseEntity<String> deleteAppointment(@PathVariable Long id)
	{
		LOGGER.info("delete-appointment/{id} endpoint used.");
		return appointmentService.deleteAppointment(id);
	}
}
