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

import com.incture.SmartHealthManagement.Dao.MedicalHistoryDao;
import com.incture.SmartHealthManagement.Dao.PatientDao;
import com.incture.SmartHealthManagement.Entities.MedicalHistory;
import com.incture.SmartHealthManagement.Entities.Patient;

class MedicalHistoryServiceTest {

    @InjectMocks
    private MedicalHistoryService medicalHistoryService;

    @Mock
    private MedicalHistoryDao medicalHistoryDao;

    @Mock
    private PatientDao patientDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMedicalHistory_PatientExists() {
        Long patientId = 1L;
        MedicalHistory medicalHistory = new MedicalHistory();
        Patient patient = new Patient();
        patient.setId(patientId);

        when(patientDao.findById(patientId)).thenReturn(Optional.of(patient));
        when(medicalHistoryDao.save(medicalHistory)).thenReturn(medicalHistory);

        ResponseEntity<String> response = medicalHistoryService.addMedicalHistory(medicalHistory, patientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Medical History added to database!", response.getBody());
        verify(medicalHistoryDao, times(1)).save(medicalHistory);
    }

    @Test
    void testAddMedicalHistory_PatientNotFound() {
        Long patientId = 1L;
        MedicalHistory medicalHistory = new MedicalHistory();

        when(patientDao.findById(patientId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = medicalHistoryService.addMedicalHistory(medicalHistory, patientId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patient not found!", response.getBody());
        verify(medicalHistoryDao, never()).save(medicalHistory);
    }

    @Test
    void testGetMedicalHistory_MedicalHistoryExists() {
        Long medicalHistoryId = 1L;
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setId(medicalHistoryId);

        when(medicalHistoryDao.findById(medicalHistoryId)).thenReturn(Optional.of(medicalHistory));

        ResponseEntity<MedicalHistory> response = medicalHistoryService.getMedicalHistory(medicalHistoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicalHistory, response.getBody());
    }

    @Test
    void testGetMedicalHistory_MedicalHistoryNotFound() {
        Long medicalHistoryId = 1L;

        when(medicalHistoryDao.findById(medicalHistoryId)).thenReturn(Optional.empty());

        ResponseEntity<MedicalHistory> response = medicalHistoryService.getMedicalHistory(medicalHistoryId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllMedicalHistory() {
        List<MedicalHistory> medicalHistories = Arrays.asList(new MedicalHistory(), new MedicalHistory());

        when(medicalHistoryDao.findAll()).thenReturn(medicalHistories);

        ResponseEntity<List<MedicalHistory>> response = medicalHistoryService.getAllMedicalHistory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicalHistories, response.getBody());
    }

    @Test
    void testUpdateMedicalHistory_MedicalHistoryExists() {
        Long medicalHistoryId = 1L;
        MedicalHistory existingMedicalHistory = new MedicalHistory();
        MedicalHistory updatedMedicalHistory = new MedicalHistory();
        updatedMedicalHistory.setDescription("Updated Description");

        when(medicalHistoryDao.findById(medicalHistoryId)).thenReturn(Optional.of(existingMedicalHistory));
        when(medicalHistoryDao.save(any(MedicalHistory.class))).thenReturn(updatedMedicalHistory);

        ResponseEntity<String> response = medicalHistoryService.updateMedicalHistory(medicalHistoryId, updatedMedicalHistory);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Medical History updated!", response.getBody());
        verify(medicalHistoryDao, times(1)).save(existingMedicalHistory);
    }

    @Test
    void testUpdateMedicalHistory_MedicalHistoryNotFound() {
        Long medicalHistoryId = 1L;
        MedicalHistory updatedMedicalHistory = new MedicalHistory();

        when(medicalHistoryDao.findById(medicalHistoryId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = medicalHistoryService.updateMedicalHistory(medicalHistoryId, updatedMedicalHistory);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Medical History not found!", response.getBody());
        verify(medicalHistoryDao, never()).save(any(MedicalHistory.class));
    }

    @Test
    void testDeleteMedicalHistory_MedicalHistoryExists() {
        Long medicalHistoryId = 1L;
        MedicalHistory medicalHistory = new MedicalHistory();
        
        when(medicalHistoryDao.findById(medicalHistoryId)).thenReturn(Optional.of(medicalHistory));

        ResponseEntity<String> response = medicalHistoryService.deleteMedicalHistory(medicalHistoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Medical History deleted!", response.getBody());
        verify(medicalHistoryDao, times(1)).deleteById(medicalHistoryId);
    }

    @Test
    void testDeleteMedicalHistory_MedicalHistoryNotFound() {
        Long medicalHistoryId = 1L;

        when(medicalHistoryDao.findById(medicalHistoryId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = medicalHistoryService.deleteMedicalHistory(medicalHistoryId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Medical History not found!", response.getBody());
        verify(medicalHistoryDao, never()).deleteById(anyLong());
    }
}
