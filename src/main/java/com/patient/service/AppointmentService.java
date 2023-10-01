package com.patient.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.patient.entity.Appointment;
import com.patient.entity.Doctor;
import com.patient.exception.WeeklySlotNotFoundException;
import com.patient.repository.AppointmentRepository;

@Service
public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	public Appointment createAppointment(Appointment appointment) {

		if (appointment.getDateTime().getDayOfWeek() == DayOfWeek.SUNDAY) {
			throw new IllegalArgumentException("Appointments on Sundays are not allowed.");
		}
		return appointmentRepository.save(appointment);
	}

	public Appointment getAppointmentById(Long id) {
		return appointmentRepository.findById(id)
				.orElseThrow(() -> new WeeklySlotNotFoundException("Appointment not found"));
	}

	public void deleteAppointment(Long id) {

		Appointment existingAppointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new WeeklySlotNotFoundException("Appointment not found"));
		appointmentRepository.delete(existingAppointment);
	}

	public List<Appointment> getAppointmentsForDoctor(Doctor doctor) {

		return appointmentRepository.findByDoctor(doctor);
	}

}
