package com.patient.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patient.entity.Appointment;
import com.patient.entity.Doctor;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByDoctorAndDateTimeBetween(Doctor doctor, LocalDateTime startDateTime,
			LocalDateTime endDateTime);

	List<Appointment> findByDoctor(Doctor doctor);
}
