package com.incture.SmartHealthManagement.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.incture.SmartHealthManagement.Dao.DoctorDao;
import com.incture.SmartHealthManagement.Dao.PatientDao;
import com.incture.SmartHealthManagement.Dao.ReportDao;
import com.incture.SmartHealthManagement.Entities.Doctor;
import com.incture.SmartHealthManagement.Entities.Patient;
import com.incture.SmartHealthManagement.Entities.Report;

@Service
public class ReportService 
{
	@Autowired
	ReportDao reportDao;
	
	@Autowired
	DoctorDao doctorDao;
	
	@Autowired
	PatientDao patientDao;
	
	
	public ResponseEntity<String> addReport(Report report, Long doctorId, Long patientId)
	{
		Optional<Doctor> doctorOptional = doctorDao.findById(doctorId);
		Optional<Patient> patientOptional = patientDao.findById(patientId);
		if(doctorOptional.isPresent() && patientOptional.isPresent())
		{
			report.setDoctor(doctorOptional.get());
			report.setPatient(patientOptional.get());
			reportDao.save(report);
			return new ResponseEntity<String>("Report added to database!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Patient or Doctor not found!", HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<Report> getReport(Long id)
	{
		Optional<Report> optional = reportDao.findById(id);
		if(optional.isPresent())
		{
			Report report = optional.get();
			return new ResponseEntity<Report>(report, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<List<Report>> getAllReports()
	{
		List<Report> reports = reportDao.findAll();
		return new ResponseEntity<List<Report>>(reports, HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<String> updateReport(Long id, Report updatedReport)
	{
		Optional<Report> optional = reportDao.findById(id);
		if(optional.isPresent())
		{
			Report report = optional.get();
			report.setDoctor(updatedReport.getDoctor());
			report.setPatient(updatedReport.getPatient());
			report.setReportDate(updatedReport.getReportDate());
			report.setReportDetails(updatedReport.getReportDetails());
			reportDao.save(report);
			return new ResponseEntity<String>("Report updated successfully!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Report not found!", HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<String> deleteReport(Long id)
	{
		Optional<Report> optional = reportDao.findById(id);
		if(optional.isPresent())
		{
			reportDao.deleteById(id);
			return new ResponseEntity<String>("Report deleted successfully!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Report not found!", HttpStatus.NOT_FOUND);
		}
	}
}
