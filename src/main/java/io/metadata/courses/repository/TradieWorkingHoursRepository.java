package io.metadata.courses.repository;

import io.metadata.courses.model.Tradie;
import io.metadata.courses.model.TradieWorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface TradieWorkingHoursRepository extends JpaRepository<TradieWorkingHours, Long> {

    List<TradieWorkingHours> findByTradieAndWorkingDay(Tradie tradie, DayOfWeek dayOfWeek);
}
