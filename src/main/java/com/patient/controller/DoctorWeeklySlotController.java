package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
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

import com.patient.entity.Doctor;
import com.patient.entity.DoctorWeeklySlot;
import com.patient.exception.WeeklySlotNotFoundException;
import com.patient.repository.DoctorRepository;
import com.patient.repository.DoctorWeeklySlotRepository;
import com.patient.service.DoctorWeeklySlotService;

@RestController
@RequestMapping("/slots")
public class DoctorWeeklySlotController {
	@Autowired
	private DoctorWeeklySlotService slotService;

	@Autowired
	private DoctorWeeklySlotRepository slotRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@GetMapping("/sundays")
	public List<DoctorWeeklySlot> getSundaySlotsForDoctor(@RequestParam Long doctorId) {

		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new WeeklySlotNotFoundException("Doctor not found"));
		return slotService.getWeeklySlotsForDoctor(doctor);
	}

	@GetMapping("/all")
	public List<DoctorWeeklySlot> getAllSlotsForDoctor() {

		return slotRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Object> createWeeklySlot(@RequestBody DoctorWeeklySlot slot) {

		DoctorWeeklySlot createdSlot = slotService.createWeeklySlot(slot);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdSlot);
	}

	@PutMapping("/{slotId}")
	public ResponseEntity<Object> updateWeeklySlot(@PathVariable Long slotId, @RequestBody DoctorWeeklySlot slot) {

		try {
			DoctorWeeklySlot updatedSlot = slotService.updateWeeklySlot(slotId, slot);
			return ResponseEntity.ok(updatedSlot);
		} catch (WeeklySlotNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{slotId}")
	public ResponseEntity<Void> deleteWeeklySlot(@PathVariable Long slotId) {

		slotService.deleteWeeklySlot(slotId);
		return ResponseEntity.noContent().build();
	}
}
