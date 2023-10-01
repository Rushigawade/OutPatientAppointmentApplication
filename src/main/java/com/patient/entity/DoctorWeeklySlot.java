package com.patient.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "doctor_weekly_slots")
public class DoctorWeeklySlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;
	private int maxPatients;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	public DoctorWeeklySlot(Long id, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, int maxPatients,
			Doctor doctor) {
		super();
		this.id = id;
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
		this.maxPatients = maxPatients;
		this.doctor = doctor;
	}

	public DoctorWeeklySlot() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public int getMaxPatients() {
		return maxPatients;
	}

	public void setMaxPatients(int maxPatients) {
		this.maxPatients = maxPatients;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "DoctorWeeklySlot [id=" + id + ", dayOfWeek=" + dayOfWeek + ", startTime=" + startTime + ", endTime="
				+ endTime + ", maxPatients=" + maxPatients + ", doctor=" + doctor + "]";
	}

}
