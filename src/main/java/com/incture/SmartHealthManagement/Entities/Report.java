package com.incture.SmartHealthManagement.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Report 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@Column
	private Date reportDate;
	
	@Column
	private String reportDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportDetails() {
		return reportDetails;
	}

	public void setReportDetails(String reportDetails) {
		this.reportDetails = reportDetails;
	}

	public Report(Long id, Doctor doctor, Patient patient, Date reportDate, String reportDetails) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.patient = patient;
		this.reportDate = reportDate;
		this.reportDetails = reportDetails;
	}

	public Report() {
		super();
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", doctor=" + doctor + ", patient=" + patient + ", reportDate=" + reportDate
				+ ", reportDetails=" + reportDetails + "]";
	}
	
}
