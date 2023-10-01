package com.patient.repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patient.entity.Doctor;
import com.patient.entity.DoctorWeeklySlot;

@Repository
public interface DoctorWeeklySlotRepository extends JpaRepository<DoctorWeeklySlot, Long> {

	List<DoctorWeeklySlot> findByDoctorAndDayOfWeek(Doctor doctor, DayOfWeek dayOfWeek);

	List<DoctorWeeklySlot> findByDoctor(Doctor doctor);
}
