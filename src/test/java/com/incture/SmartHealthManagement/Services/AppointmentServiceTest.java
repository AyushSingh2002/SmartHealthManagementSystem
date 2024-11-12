package com.incture.SmartHealthManagement.Services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.incture.SmartHealthManagement.Controllers.AppointmentController;
import com.incture.SmartHealthManagement.Entities.Appointment;

class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAppointment() {
        Appointment appointment = new Appointment();
        Long doctorId = 1L;
        Long patientId = 2L;
        
        when(appointmentService.addAppointment(any(Appointment.class), eq(doctorId), eq(patientId)))
                .thenReturn(new ResponseEntity<>("Appointment added successfully", HttpStatus.OK));

        ResponseEntity<String> response = appointmentController.addAppointment(appointment, doctorId, patientId);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Appointment added successfully", response.getBody());
    }

    @Test
    void testGetAppointment() {
        Long appointmentId = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);

        when(appointmentService.getAppointment(appointmentId)).thenReturn(new ResponseEntity<>(appointment, HttpStatus.OK));

        ResponseEntity<Appointment> response = appointmentController.getAppointment(appointmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(appointment, response.getBody());
    }

    @Test
    void testGetAllAppointments() {
        List<Appointment> appointments = Arrays.asList(new Appointment(), new Appointment());

        when(appointmentService.getAllAppointments()).thenReturn(new ResponseEntity<>(appointments, HttpStatus.OK));

        ResponseEntity<List<Appointment>> response = appointmentController.getAllAppointments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(appointments, response.getBody());
    }

    @Test
    void testUpdateAppointment() {
        Long appointmentId = 1L;
        Appointment updatedAppointment = new Appointment();

        when(appointmentService.updateAppointment(appointmentId, updatedAppointment))
                .thenReturn(new ResponseEntity<>("Appointment updated successfully", HttpStatus.OK));

        ResponseEntity<String> response = appointmentController.updateAppointment(updatedAppointment, appointmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Appointment updated successfully", response.getBody());
    }

    @Test
    void testDeleteAppointment() {
        Long appointmentId = 1L;

        when(appointmentService.deleteAppointment(appointmentId))
                .thenReturn(new ResponseEntity<>("Appointment deleted successfully", HttpStatus.OK));

        ResponseEntity<String> response = appointmentController.deleteAppointment(appointmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Appointment deleted successfully", response.getBody());
    }
}
