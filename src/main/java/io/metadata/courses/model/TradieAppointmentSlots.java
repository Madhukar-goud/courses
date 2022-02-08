package io.metadata.courses.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "tradie_appointment_slots")
public class TradieAppointmentSlots {
    @ApiModelProperty(required = false, hidden = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long tradieAppointmentSlotsId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @JsonBackReference
    @ManyToOne
    private Tradie tradie;

    @ApiModelProperty(required = false, hidden = true)
    @ManyToOne
    private Customer customer;
    private AppointmentStatus status;

    public AppointmentStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Tradie getTradie() {
        return tradie;
    }

    public void setTradie(Tradie tradie) {
        this.tradie = tradie;
    }

    public Long getTradieAppointmentSlotsId() {
        return tradieAppointmentSlotsId;
    }

    public void setTradieAppointmentSlotsId(Long tradieAppointmentSlotsId) {
        this.tradieAppointmentSlotsId = tradieAppointmentSlotsId;
    }
}
