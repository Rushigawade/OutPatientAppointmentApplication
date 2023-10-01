package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.entity.Appointment;
import com.patient.entity.Doctor;
import com.patient.exception.AppointmentNotFoundException;
import com.patient.exception.DocterNotFoundException;
import com.patient.repository.DoctorRepository;
import com.patient.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private DoctorRepository doctorRepository;

	@GetMapping
	public List<Appointment> getAllAppointments() {
		return appointmentService.getAllAppointments();
	}

	@PostMapping
	public ResponseEntity<Object> createAppointment(@RequestBody Appointment appointment) {
		try {
			Appointment createdAppointment = appointmentService.createAppointment(appointment);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getAppointmentById(@PathVariable Long id) {
		try {
			Appointment appointment = appointmentService.getAppointmentById(id);
			return ResponseEntity.ok(appointment);
		} catch (AppointmentNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
		try {
			appointmentService.deleteAppointment(id);
			return ResponseEntity.noContent().build();
		} catch (AppointmentNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/doctor/{doctorId}")
	public List<Appointment> getAppointmentsForDoctor(@PathVariable Long doctorId) {
		// Fetch and return all appointments for a specific doctor
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new DocterNotFoundException("Doctor not found"));
		return appointmentService.getAppointmentsForDoctor(doctor);
	}
	
	

}
