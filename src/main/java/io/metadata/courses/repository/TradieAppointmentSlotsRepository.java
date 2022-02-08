package io.metadata.courses.repository;

import io.metadata.courses.model.Tradie;
import io.metadata.courses.model.TradieAppointmentSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TradieAppointmentSlotsRepository extends JpaRepository<TradieAppointmentSlots, Long> {
    TradieAppointmentSlots findTradieAppointmentSlotsByTradieAndStartTime(Tradie tradie, LocalDateTime startTime);
}
