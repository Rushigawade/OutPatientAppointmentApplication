package com.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.entity.Doctor;
import com.patient.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	public Doctor getDoctorById(Long id) {
		return doctorRepository.findById(id).orElse(null);
	}

	public Doctor createDoctor(Doctor doctor) {

		if (isDoctorNameUnique(doctor.getName())) {

			return doctorRepository.save(doctor);
		} else {

			throw new RuntimeException("Doctor name is not unique");
		}
	}

	private boolean isDoctorNameUnique(String name) {
		Doctor existingDoctor = doctorRepository.findByName(name);
		return existingDoctor == null;
	}

}
