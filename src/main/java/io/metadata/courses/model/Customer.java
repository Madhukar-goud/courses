package io.metadata.courses.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "customer")
public class Customer {

    @ApiModelProperty(required = false, hidden = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String phoneNum;

    @ApiModelProperty(required = false, hidden = true)
    @OneToMany(mappedBy = "customer")
    List<TradieAppointmentSlots> tradieAppointmentSlotsList = new ArrayList<>();

    public Long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<TradieAppointmentSlots> getTradieAppointmentSlotsList() {
        return tradieAppointmentSlotsList;
    }

    public void addTradieAppointmentSlotsList(TradieAppointmentSlots tradieAppointmentSlots) {
        this.tradieAppointmentSlotsList.add(tradieAppointmentSlots);
    }
}
