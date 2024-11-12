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

import com.incture.SmartHealthManagement.Dao.DoctorDao;
import com.incture.SmartHealthManagement.Dao.PatientDao;
import com.incture.SmartHealthManagement.Dao.ReportDao;
import com.incture.SmartHealthManagement.Entities.Doctor;
import com.incture.SmartHealthManagement.Entities.Patient;
import com.incture.SmartHealthManagement.Entities.Report;

class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private ReportDao reportDao;

    @Mock
    private DoctorDao doctorDao;

    @Mock
    private PatientDao patientDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddReport_DoctorAndPatientExist() {
        Long doctorId = 1L;
        Long patientId = 2L;
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        Report report = new Report();

        when(doctorDao.findById(doctorId)).thenReturn(Optional.of(doctor));
        when(patientDao.findById(patientId)).thenReturn(Optional.of(patient));
        when(reportDao.save(report)).thenReturn(report);

        ResponseEntity<String> response = reportService.addReport(report, doctorId, patientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Report added to database!", response.getBody());
        verify(reportDao, times(1)).save(report);
    }

    @Test
    void testAddReport_DoctorOrPatientNotFound() {
        Long doctorId = 1L;
        Long patientId = 2L;
        Report report = new Report();

        when(doctorDao.findById(doctorId)).thenReturn(Optional.empty());
        when(patientDao.findById(patientId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = reportService.addReport(report, doctorId, patientId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patient or Doctor not found!", response.getBody());
        verify(reportDao, never()).save(report);
    }

    @Test
    void testGetReport_ReportExists() {
        Long reportId = 1L;
        Report report = new Report();
        report.setId(reportId);

        when(reportDao.findById(reportId)).thenReturn(Optional.of(report));

        ResponseEntity<Report> response = reportService.getReport(reportId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(report, response.getBody());
    }

    @Test
    void testGetReport_ReportNotFound() {
        Long reportId = 1L;

        when(reportDao.findById(reportId)).thenReturn(Optional.empty());

        ResponseEntity<Report> response = reportService.getReport(reportId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllReports() {
        List<Report> reports = Arrays.asList(new Report(), new Report());

        when(reportDao.findAll()).thenReturn(reports);

        ResponseEntity<List<Report>> response = reportService.getAllReports();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reports, response.getBody());
    }

    @Test
    void testUpdateReport_ReportExists() {
        Long reportId = 1L;
        Report existingReport = new Report();
        Report updatedReport = new Report();
        updatedReport.setReportDetails("Updated details");

        when(reportDao.findById(reportId)).thenReturn(Optional.of(existingReport));
        when(reportDao.save(any(Report.class))).thenReturn(existingReport);

        ResponseEntity<String> response = reportService.updateReport(reportId, updatedReport);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Report updated successfully!", response.getBody());
        verify(reportDao, times(1)).save(existingReport);
    }

    @Test
    void testUpdateReport_ReportNotFound() {
        Long reportId = 1L;
        Report updatedReport = new Report();

        when(reportDao.findById(reportId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = reportService.updateReport(reportId, updatedReport);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Report not found!", response.getBody());
        verify(reportDao, never()).save(any(Report.class));
    }

    @Test
    void testDeleteReport_ReportExists() {
        Long reportId = 1L;
        Report report = new Report();

        when(reportDao.findById(reportId)).thenReturn(Optional.of(report));

        ResponseEntity<String> response = reportService.deleteReport(reportId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Report deleted successfully!", response.getBody());
        verify(reportDao, times(1)).deleteById(reportId);
    }

    @Test
    void testDeleteReport_ReportNotFound() {
        Long reportId = 1L;

        when(reportDao.findById(reportId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = reportService.deleteReport(reportId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Report not found!", response.getBody());
        verify(reportDao, never()).deleteById(anyLong());
    }
}
