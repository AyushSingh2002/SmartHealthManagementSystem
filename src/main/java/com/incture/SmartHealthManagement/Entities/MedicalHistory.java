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
@Table(name = "MedicalHistory")
public class MedicalHistory 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@Column
	private String description;
	
	@Column
	private Date recordDate;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public MedicalHistory(Long id, Patient patient, String description, Date recordDate) {
		super();
		this.id = id;
		this.patient = patient;
		this.description = description;
		this.recordDate = recordDate;
	}

	public MedicalHistory() {
		super();
	}

	@Override
	public String toString() {
		return "MedicalHistory [id=" + id + ", patient=" + patient + ", description=" + description + ", recordDate="
				+ recordDate + "]";
	}
	
}
