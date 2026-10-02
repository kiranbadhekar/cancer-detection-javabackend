package com.cancer.backend.controller;

import com.cancer.backend.dto.AppointmentRequest;
import com.cancer.backend.entity.Appointment;
import com.cancer.backend.service.AppointmentService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(
            AppointmentService appointmentService
    ) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(
            @Valid @RequestBody AppointmentRequest request
    ) {

        Appointment appointment =
                appointmentService.createAppointment(request);

        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Appointment>> getByEmail(
            @PathVariable String email
    ) {

        return ResponseEntity.ok(
                appointmentService.getAppointmentsByEmail(email)
        );
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Appointment>> getByDate(
            @PathVariable LocalDate date
    ) {

        return ResponseEntity.ok(
                appointmentService.getAppointmentsByDate(date)
        );
    }
}