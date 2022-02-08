package io.metadata.courses.service;


import io.metadata.courses.model.*;
import io.metadata.courses.repository.*;
import io.metadata.courses.utils.LogExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AppointmentService {

    @Autowired
    TradieRepository tradieRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TradieWorkingHoursRepository tradieWorkingHoursRepository;

    @Autowired
    TradieAppointmentSlotsRepository tradieAppointmentSlotsRepository;

    @LogExecutionTime
    public List<TradieAppointmentSlots> getAllTradieAvailableAppointments(Long tradieId, LocalDate date) {
        Optional<Tradie> tradieOptional = tradieRepository.findById(tradieId);
        if (tradieOptional.isPresent()) {
            int dayOfWeek = date.getDayOfWeek().getValue();
            log.info("Day of Week ==> " + dayOfWeek);
            List<TradieWorkingHours> tradieWorkingHoursList = tradieWorkingHoursRepository.findByTradieAndWorkingDay(tradieOptional.get(), date.getDayOfWeek());
            List<TradieAppointmentSlots> tradieAppointmentSlotsList = new ArrayList<>();
            if (tradieWorkingHoursList == null || tradieWorkingHoursList.size() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No working hours data for Tradie");
            }
            //split into appointments
            TradieWorkingHours tradieWorkingHours = tradieWorkingHoursList.get(0);
            LocalTime compareTime = tradieWorkingHours.getStartTime();
            while (compareTime.isBefore(tradieWorkingHours.getEndTime())) {
                TradieAppointmentSlots tradieAppointmentSlots = tradieAppointmentSlotsRepository.findTradieAppointmentSlotsByTradieAndStartTime(tradieOptional.get(), LocalDateTime.of(date, compareTime));
                if (tradieAppointmentSlots != null && !tradieAppointmentSlots.getStatus().equals(AppointmentStatus.BOOKED)) {
                    tradieAppointmentSlotsList.add(tradieAppointmentSlots);
                } else {
                    tradieAppointmentSlots = new TradieAppointmentSlots();
                    tradieAppointmentSlots.setTradie(tradieWorkingHours.getTradie());
                    tradieAppointmentSlots.setStartTime(LocalDateTime.of(date, compareTime));
                    tradieAppointmentSlots.setEndTime(LocalDateTime.of(date, compareTime.plusMinutes(tradieWorkingHours.getDurationInMinutes())));
                    compareTime = compareTime.plusMinutes(tradieWorkingHours.getGapTimeInMinutes() + tradieWorkingHours.getDurationInMinutes());
                    tradieAppointmentSlots.setStatus(AppointmentStatus.AVAILABLE);
                    tradieAppointmentSlotsList.add(tradieAppointmentSlots);
                }
            }
            return tradieAppointmentSlotsList;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tradie ID not found");
    }
}
