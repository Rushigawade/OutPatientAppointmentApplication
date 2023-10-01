package com.patient.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String PatientName;

	private LocalDateTime dateTime;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	public Appointment(Long id, String patientName, LocalDateTime dateTime, Doctor doctor) {
		super();
		this.id = id;
		PatientName = patientName;
		this.dateTime = dateTime;
		this.doctor = doctor;
	}

	public Appointment() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getPatientName() {
		return PatientName;
	}

	public void setPatientName(String patientName) {
		PatientName = patientName;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", PatientName=" + PatientName + ", dateTime=" + dateTime + ", doctor="
				+ doctor + "]";
	}

}
