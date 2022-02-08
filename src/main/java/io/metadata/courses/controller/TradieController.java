package io.metadata.courses.controller;

import io.metadata.courses.model.AppointmentStatus;
import io.metadata.courses.model.Tradie;
import io.metadata.courses.model.TradieAppointmentSlots;
import io.metadata.courses.model.TradieWorkingHours;
import io.metadata.courses.repository.TradieAppointmentSlotsRepository;
import io.metadata.courses.repository.TradieRepository;
import io.metadata.courses.repository.TradieWorkingHoursRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class TradieController {

    @Autowired
    TradieRepository tradieRepository;

    @Autowired
    TradieWorkingHoursRepository tradieWorkingHoursRepository;


    @Autowired
    TradieAppointmentSlotsRepository tradieAppointmentSlotsRepository;

    @PostMapping("/add-tradie")
    public Tradie addTradie(@RequestBody Tradie tradie) {
        return tradieRepository.save(tradie);
    }

    @GetMapping("/get-all-tradies")
    public List<Tradie> getAllTradies() {
        return tradieRepository.findAll();
    }

    @GetMapping("/get-tradie-by-id")
    public Tradie getTradieById(@RequestParam Long tradieId) {

        Optional<Tradie> tradieOptional = tradieRepository.findById(tradieId);
        if (tradieOptional.isPresent()) {
            return tradieOptional.get();
        }
        return null;
    }

    @PostMapping("/add-tradie-working-hours")
    public TradieWorkingHours addTradieWorkingHours(@RequestParam Long tradieId, @RequestParam String startTime,
                                                    @RequestParam String endTime, @RequestParam Integer dayOfWeek,
                                                    @RequestParam Integer durationInMinutes, @RequestParam Integer gapTimeInMinutes ) {
        TradieWorkingHours tradieWorkingHours = new TradieWorkingHours();
        Optional<Tradie> tradieOptional = tradieRepository.findById(tradieId);
        if (tradieOptional.isPresent()) {
            tradieWorkingHours.setTradie(tradieOptional.get());
            tradieWorkingHours.setStartTime(LocalTime.of(Integer.parseInt(startTime.split("-")[0]), Integer.parseInt(startTime.split("-")[1])));
            tradieWorkingHours.setEndTime(LocalTime.of(Integer.parseInt(endTime.split("-")[0]), Integer.parseInt(endTime.split("-")[1])));
            tradieWorkingHours.setWorkingDay(DayOfWeek.of(dayOfWeek));
            tradieWorkingHours.setDurationInMinutes(durationInMinutes);
            tradieWorkingHours.setGapTimeInMinutes(gapTimeInMinutes);
            return tradieWorkingHoursRepository.save(tradieWorkingHours);
        }
        return null;
    }

    @PostMapping("/book-appointment")
    public TradieAppointmentSlots bookAppointmentSlot(@RequestParam Long tradieId, @RequestParam String startTime,
                                                      @RequestParam String endTime) {
        TradieAppointmentSlots tradieAppointmentSlots = new TradieAppointmentSlots();
        Optional<Tradie> tradieOptional = tradieRepository.findById(tradieId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (tradieOptional.isPresent()) {
            tradieAppointmentSlots.setTradie(tradieOptional.get());
            tradieAppointmentSlots.setStartTime(LocalDateTime.parse(startTime, formatter));
            tradieAppointmentSlots.setEndTime(LocalDateTime.parse(endTime, formatter));
            tradieAppointmentSlots.setStatus(AppointmentStatus.BOOKED);
        }
        return tradieAppointmentSlotsRepository.save(tradieAppointmentSlots);
    }

}
