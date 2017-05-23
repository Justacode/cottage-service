package ru.kpfu.itis.mustafin.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
public class Cottage {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer cottageNumber;

    @Column
    private Integer bedsNumber;

    @Column
    private String parking;

    @Column
    private String childZone;

    @Column
    private String gazebo;

    @Column
    private String comeWithAnimals;

    @Column
    private Integer dailyRent;

    public Cottage() {
    }

    public Cottage(int cottageNumber, int bedsNumber, String parking, String childZone,
                   String gazebo, String comeWithAnimals, int dailyRent) {
        this.cottageNumber = cottageNumber;
        this.bedsNumber = bedsNumber;
        this.parking = parking;
        this.childZone = childZone;
        this.gazebo = gazebo;
        this.comeWithAnimals = comeWithAnimals;
        this.dailyRent = dailyRent;
    }

    public Long getId() {
        return id;
    }

    public Integer getCottageNumber() {
        return cottageNumber;
    }

    public Integer getBedsNumber() {
        return bedsNumber;
    }

    public String getParking() {
        return parking;
    }

    public String getChildZone() {
        return childZone;
    }

    public String getGazebo() {
        return gazebo;
    }

    public String getComeWithAnimals() {
        return comeWithAnimals;
    }

    public Integer getDailyRent() {
        return dailyRent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBedsNumber(Integer bedsNumber) {
        this.bedsNumber = bedsNumber;
    }

    public void setChildZone(String childZone) {
        this.childZone = childZone;
    }

    public void setComeWithAnimals(String comeWithAnimals) {
        this.comeWithAnimals = comeWithAnimals;
    }

    public void setCottageNumber(int cottageNumber) {
        this.cottageNumber = cottageNumber;
    }

    public void setDailyRent(Integer dailyRent) {
        this.dailyRent = dailyRent;
    }

    public void setGazebo(String gazebo) {
        this.gazebo = gazebo;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }
}

