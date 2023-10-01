package com.patient.service;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.entity.Doctor;
import com.patient.entity.DoctorWeeklySlot;
import com.patient.exception.WeeklySlotNotFoundException;
import com.patient.repository.DoctorWeeklySlotRepository;

@Service
public class DoctorWeeklySlotService {
	@Autowired
	private DoctorWeeklySlotRepository slotRepository;

	public List<DoctorWeeklySlot> getWeeklySlotsForDoctor(Doctor doctor) {
		return slotRepository.findByDoctorAndDayOfWeek(doctor, DayOfWeek.SUNDAY);
	}

	public DoctorWeeklySlot createWeeklySlot(DoctorWeeklySlot slot) {
	
		return slotRepository.save(slot);
	}

	public DoctorWeeklySlot updateWeeklySlot(Long slotId, DoctorWeeklySlot updatedSlot) {

		DoctorWeeklySlot existingSlot = slotRepository.findById(slotId)
				.orElseThrow(() -> new WeeklySlotNotFoundException("Weekly slot not found"));

		existingSlot.setDayOfWeek(updatedSlot.getDayOfWeek());
		existingSlot.setStartTime(updatedSlot.getStartTime());
		existingSlot.setEndTime(updatedSlot.getEndTime());
		existingSlot.setMaxPatients(updatedSlot.getMaxPatients());

		return slotRepository.save(existingSlot);
	}

	public void deleteWeeklySlot(Long slotId) {
		DoctorWeeklySlot existingSlot = slotRepository.findById(slotId)
				.orElseThrow(() -> new WeeklySlotNotFoundException("Weekly slot not found"));

		slotRepository.delete(existingSlot);
	}
}
