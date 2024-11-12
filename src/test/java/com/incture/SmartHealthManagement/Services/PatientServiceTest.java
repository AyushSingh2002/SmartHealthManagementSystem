package com.incture.SmartHealthManagement.Services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.incture.SmartHealthManagement.Dao.PatientDao;
import com.incture.SmartHealthManagement.Dao.UserDao;
import com.incture.SmartHealthManagement.Entities.Patient;
import com.incture.SmartHealthManagement.Entities.User;

class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientDao patientDao;

    @Mock
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPatient_UserExists() {
        Long userId = 1L;
        Patient patient = new Patient();
        User user = new User();
        user.setId(userId);

        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        when(patientDao.save(patient)).thenReturn(patient);

        ResponseEntity<String> response = patientService.addPatient(patient, userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patient profile created!", response.getBody());
        verify(patientDao, times(1)).save(patient);
    }

    @Test
    void testAddPatient_UserNotFound() {
        Long userId = 1L;
        Patient patient = new Patient();

        when(userDao.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = patientService.addPatient(patient, userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found!", response.getBody());
        verify(patientDao, never()).save(patient);
    }

    @Test
    void testGetPatient_PatientExists() {
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);

        when(patientDao.findById(patientId)).thenReturn(Optional.of(patient));

        ResponseEntity<Patient> response = patientService.getPatient(patientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void testGetPatient_PatientNotFound() {
        Long patientId = 1L;

        when(patientDao.findById(patientId)).thenReturn(Optional.empty());

        ResponseEntity<Patient> response = patientService.getPatient(patientId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllPatients() {
        List<Patient> patients = Arrays.asList(new Patient(), new Patient());

        when(patientDao.findAll()).thenReturn(patients);

        ResponseEntity<List<Patient>> response = patientService.getAllPatients();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patients, response.getBody());
    }

    @Test
    void testUpdatePatient_PatientExists() {
        Long patientId = 1L;
        Patient existingPatient = new Patient();
        Patient updatedPatient = new Patient();
        updatedPatient.setFirstName("Updated First Name");

        when(patientDao.findById(patientId)).thenReturn(Optional.of(existingPatient));
        when(patientDao.save(any(Patient.class))).thenReturn(existingPatient);

        ResponseEntity<String> response = patientService.updatePatient(patientId, updatedPatient);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patient profile updated successfully!", response.getBody());
        verify(patientDao, times(1)).save(existingPatient);
    }

    @Test
    void testUpdatePatient_PatientNotFound() {
        Long patientId = 1L;
        Patient updatedPatient = new Patient();

        when(patientDao.findById(patientId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = patientService.updatePatient(patientId, updatedPatient);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patient profile not found!", response.getBody());
        verify(patientDao, never()).save(any(Patient.class));
    }

    @Test
    void testDeletePatient_PatientExists() {
        Long patientId = 1L;
        Patient patient = new Patient();

        when(patientDao.findById(patientId)).thenReturn(Optional.of(patient));

        ResponseEntity<String> response = patientService.deletePatient(patientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patient profile removed successfully!", response.getBody());
        verify(patientDao, times(1)).deleteById(patientId);
    }

    @Test
    void testDeletePatient_PatientNotFound() {
        Long patientId = 1L;

        when(patientDao.findById(patientId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = patientService.deletePatient(patientId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patient profile not found!", response.getBody());
        verify(patientDao, never()).deleteById(anyLong());
    }
}
