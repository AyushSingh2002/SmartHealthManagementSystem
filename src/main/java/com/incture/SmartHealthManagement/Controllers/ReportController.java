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

import com.incture.SmartHealthManagement.Entities.Report;
import com.incture.SmartHealthManagement.Services.ReportService;

@RestController
@RequestMapping("/api/v1/Report")
public class ReportController 
{
	@Autowired
	ReportService reportService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
	
	@PostMapping("/add-report")
	public ResponseEntity<String> addReport(@RequestBody Report report, @RequestParam Long doctorId, @RequestParam Long patientId)
	{
		LOGGER.info("add-report endpoint used.");
		return reportService.addReport(report, doctorId, patientId);
	}
	
	@GetMapping("/get-all-reports")
	public ResponseEntity<List<Report>> getAllReports()
	{
		LOGGER.info("get-all-reports endpoint used.");
		return reportService.getAllReports();
	}
	
	@GetMapping("/get-report/{id}")
	public ResponseEntity<Report> getReport(@PathVariable Long id)
	{
		LOGGER.info("get-report endpoint used.");
		return reportService.getReport(id);
	}
	
	@PutMapping("/update-report/{id}")
	public ResponseEntity<String> updateReport(@PathVariable Long id, @RequestBody Report report)
	{
		LOGGER.info("update-report endpoint used.");
		return reportService.updateReport(id, report);
	}
	
	@DeleteMapping("/delete-report/{id}")
	public ResponseEntity<String> deleteReport(@PathVariable Long id)
	{
		LOGGER.info("delete-report endpoint used.");
		return reportService.deleteReport(id);
	}
}
