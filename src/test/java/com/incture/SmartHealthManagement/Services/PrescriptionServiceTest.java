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

import com.incture.SmartHealthManagement.Dao.PrescriptionDao;
import com.incture.SmartHealthManagement.Entities.Prescription;

class PrescriptionServiceTest {

    @InjectMocks
    private PrescriptionService prescriptionService;

    @Mock
    private PrescriptionDao prescriptionDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPrescription() {
        Prescription prescription = new Prescription();

        when(prescriptionDao.save(prescription)).thenReturn(prescription);

        ResponseEntity<String> response = prescriptionService.addPrescription(prescription);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Prescription added to database!", response.getBody());
        verify(prescriptionDao, times(1)).save(prescription);
    }

    @Test
    void testGetPrescription_PrescriptionExists() {
        Long prescriptionId = 1L;
        Prescription prescription = new Prescription();
        prescription.setId(prescriptionId);

        when(prescriptionDao.findById(prescriptionId)).thenReturn(Optional.of(prescription));

        ResponseEntity<Prescription> response = prescriptionService.getPrescription(prescriptionId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(prescription, response.getBody());
    }

    @Test
    void testGetPrescription_PrescriptionNotFound() {
        Long prescriptionId = 1L;

        when(prescriptionDao.findById(prescriptionId)).thenReturn(Optional.empty());

        ResponseEntity<Prescription> response = prescriptionService.getPrescription(prescriptionId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllPrescriptions() {
        List<Prescription> prescriptions = Arrays.asList(new Prescription(), new Prescription());

        when(prescriptionDao.findAll()).thenReturn(prescriptions);

        ResponseEntity<List<Prescription>> response = prescriptionService.getAllPrescriptions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(prescriptions, response.getBody());
    }

    @Test
    void testUpdatePrescription_PrescriptionExists() {
        Long prescriptionId = 1L;
        Prescription existingPrescription = new Prescription();
        Prescription updatedPrescription = new Prescription();
        updatedPrescription.setDosageInstruction("Take twice daily");

        when(prescriptionDao.findById(prescriptionId)).thenReturn(Optional.of(existingPrescription));
        when(prescriptionDao.save(any(Prescription.class))).thenReturn(existingPrescription);

        ResponseEntity<String> response = prescriptionService.updatePrescription(prescriptionId, updatedPrescription);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Report updated successfully!", response.getBody());
        verify(prescriptionDao, times(1)).save(existingPrescription);
    }

    @Test
    void testUpdatePrescription_PrescriptionNotFound() {
        Long prescriptionId = 1L;
        Prescription updatedPrescription = new Prescription();

        when(prescriptionDao.findById(prescriptionId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = prescriptionService.updatePrescription(prescriptionId, updatedPrescription);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Report not found!", response.getBody());
        verify(prescriptionDao, never()).save(any(Prescription.class));
    }

    @Test
    void testDeletePrescription_PrescriptionExists() {
        Long prescriptionId = 1L;
        Prescription prescription = new Prescription();

        when(prescriptionDao.findById(prescriptionId)).thenReturn(Optional.of(prescription));

        ResponseEntity<String> response = prescriptionService.deletePrescription(prescriptionId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Prescription deleted successfully!", response.getBody());
        verify(prescriptionDao, times(1)).deleteById(prescriptionId);
    }

    @Test
    void testDeletePrescription_PrescriptionNotFound() {
        Long prescriptionId = 1L;

        when(prescriptionDao.findById(prescriptionId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = prescriptionService.deletePrescription(prescriptionId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Prescription not found!", response.getBody());
        verify(prescriptionDao, never()).deleteById(anyLong());
    }
}
