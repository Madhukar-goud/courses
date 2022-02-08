package io.metadata.courses.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "tradie_working_hours")
public class TradieWorkingHours {

    @ApiModelProperty(required = false, hidden = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long tradieWorkingHoursId;

    @JsonBackReference
    @ManyToOne
    private Tradie tradie;
    private DayOfWeek workingDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer durationInMinutes;
    private Integer gapTimeInMinutes;

    public Long getTradieWorkingHoursId() {
        return tradieWorkingHoursId;
    }

    public void setTradieWorkingHoursId(Long tradieWorkingHoursId) {
        this.tradieWorkingHoursId = tradieWorkingHoursId;
    }

    public Tradie getTradie() {
        return tradie;
    }

    public void setTradie(Tradie tradie) {
        this.tradie = tradie;
    }

    public DayOfWeek getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(DayOfWeek workingDay) {
        this.workingDay = workingDay;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Integer getGapTimeInMinutes() {
        return gapTimeInMinutes;
    }

    public void setGapTimeInMinutes(Integer gapTimeInMinutes) {
        this.gapTimeInMinutes = gapTimeInMinutes;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
