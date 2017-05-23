package ru.kpfu.itis.mustafin.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Rent {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fullName;

    @Column
    private String phoneNumber;

    @Column
    private Integer cottageNumber;

    @Column
    private String arrival;

    @Column
    private String departure;

    public Rent() {

    }

    public Rent(String fullName, String phoneNumber, int cottageNumber, String arrival, String departure) {
        this.fullName = fullName;
        this.cottageNumber = cottageNumber;
        this.phoneNumber = phoneNumber;
        this.arrival = arrival;
        this.departure = departure;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCottageNumber(Integer cottageNumber) {
        this.cottageNumber = cottageNumber;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getCottageNumber() {
        return cottageNumber;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
