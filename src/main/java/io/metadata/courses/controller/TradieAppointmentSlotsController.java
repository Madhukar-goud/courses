package io.metadata.courses.controller;

import io.metadata.courses.model.AppointmentStatus;
import io.metadata.courses.model.Tradie;
import io.metadata.courses.model.TradieAppointmentSlots;
import io.metadata.courses.model.TradieWorkingHours;
import io.metadata.courses.repository.TradieAppointmentSlotsRepository;
import io.metadata.courses.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class TradieAppointmentSlotsController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/get-all-tradie-available-appointments")
    public List<TradieAppointmentSlots> getAllTradieAvailableAppointments(@RequestParam Long tradieId, @RequestParam String localDateStr) {
        LocalDate localDate = LocalDate.of(Integer.parseInt(localDateStr.split("-")[0]),Integer.parseInt(localDateStr.split("-")[1]),  Integer.parseInt(localDateStr.split("-")[2]));
        return appointmentService.getAllTradieAvailableAppointments(tradieId, localDate);
    }
}
