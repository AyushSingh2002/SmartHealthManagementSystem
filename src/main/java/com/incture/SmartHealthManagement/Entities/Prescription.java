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
public class Prescription 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@Column
	private Date issueDate;
	
	@Column
	private String medicationDetails;
	
	@Column
	private String dosageInstruction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getMedicationDetails() {
		return medicationDetails;
	}

	public void setMedicationDetails(String medicationDetails) {
		this.medicationDetails = medicationDetails;
	}

	public String getDosageInstruction() {
		return dosageInstruction;
	}

	public void setDosageInstruction(String dosageInstruction) {
		this.dosageInstruction = dosageInstruction;
	}

	public Prescription(Long id, Patient patient, Doctor doctor, Date issueDate, String medicationDetails,
			String dosageInstruction) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.issueDate = issueDate;
		this.medicationDetails = medicationDetails;
		this.dosageInstruction = dosageInstruction;
	}

	public Prescription() {
		super();
	}

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", issueDate=" + issueDate
				+ ", medicationDetails=" + medicationDetails + ", dosageInstruction=" + dosageInstruction + "]";
	}

	
	
}
