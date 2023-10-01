package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.entity.Doctor;
import com.patient.repository.DoctorRepository;
import com.patient.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private DoctorRepository doctorRepository;

	@GetMapping("/all")
	public List<Doctor> getAllDoctors() {
		return doctorService.getAllDoctors();
	}

	@GetMapping("/{id}")
	public Doctor getDoctorById(@PathVariable Long id) {

		return doctorService.getDoctorById(id);
	}

	@PostMapping("/create")
	public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
		try {
			Doctor createdDoctor = doctorService.createDoctor(doctor);
			return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{doctorId}")
	public ResponseEntity<Object> updateDoctor(@PathVariable Long doctorId, @RequestBody Doctor updatedDoctor) {
	
		if (doctorRepository.existsById(doctorId)) {
			updatedDoctor.setId(doctorId); // Ensure the ID is set
			Doctor updated = doctorRepository.save(updatedDoctor);
			return ResponseEntity.ok(updated);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
		}
	}

	@DeleteMapping("/{doctorId}")
	public ResponseEntity<Void> deleteDoctor(@PathVariable Long doctorId) {
		
		if (doctorRepository.existsById(doctorId)) {
			doctorRepository.deleteById(doctorId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
