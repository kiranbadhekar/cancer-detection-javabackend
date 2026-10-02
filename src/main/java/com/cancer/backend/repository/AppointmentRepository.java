package com.cancer.backend.repository;

import com.cancer.backend.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    List<Appointment> findByEmail(String email);

    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);

    boolean existsByDoctorAndAppointmentDate(
            String doctor,
            LocalDate appointmentDate
    );
}