
package com.cancer.backend.service;

import com.cancer.backend.dto.AppointmentRequest;
import com.cancer.backend.entity.Appointment;
import com.cancer.backend.repository.AppointmentRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository
    ) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment createAppointment(
            AppointmentRequest request
    ) {

        boolean alreadyBooked =
                appointmentRepository
                        .existsByDoctorAndAppointmentDate(
                                request.getDoctor(),
                                request.getAppointmentDate()
                        );

        if (alreadyBooked) {
            throw new RuntimeException(
                    "This doctor already has an appointment on this date"
            );
        }

        Appointment appointment = new Appointment();

        appointment.setFullName(request.getFullName());

        // Database requires patient_name
        appointment.setPatientName(request.getFullName());

        appointment.setEmail(request.getEmail());
        appointment.setPhone(request.getPhone());
        appointment.setAge(request.getAge());
        appointment.setGender(request.getGender());
        appointment.setCancerType(request.getCancerType());

        appointment.setAppointmentDate(
                request.getAppointmentDate()
        );

        appointment.setAppointmentTime(
                request.getAppointmentTime()
        );

        appointment.setDoctor(request.getDoctor());
        appointment.setMessage(request.getMessage());

        appointment.setStatus("PENDING");

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByEmail(
            String email
    ) {

        return appointmentRepository.findByEmail(email);
    }

    public List<Appointment> getAppointmentsByDate(
            LocalDate date
    ) {

        return appointmentRepository.findByAppointmentDate(date);
    }
}
