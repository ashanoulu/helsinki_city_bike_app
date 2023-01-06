package com.ashan.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
//@Table(name = "journey")
public class Journey {
    private @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    @NotNull
    private Timestamp departureTime;
    @NotNull
    private Timestamp returnTime;
    @NotNull
    private String departureStationId;
    @NotNull
    private String departureStationName;
    @NotNull
    private String returnStationId;
    @NotNull
    private String returnStationName;
    @NotNull
    private int coveredDestination;
    @NotNull
    private int duration;

    public Journey() {}

    public Journey(Long id, Timestamp departureTime, Timestamp returnTime, String departureStationId,
                   String departureStationName, String returnStationId, String returnStationName,
                   int coveredDestination, int duration) {
        this.id = id;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.departureStationId = departureStationId;
        this.departureStationName = departureStationName;
        this.returnStationId = returnStationId;
        this.returnStationName = returnStationName;
        this.coveredDestination = coveredDestination;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }

    public String getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(String departureStationId) {
        this.departureStationId = departureStationId;
    }

    public String getDepartureStationName() {
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    public String getReturnStationId() {
        return returnStationId;
    }

    public void setReturnStationId(String returnStationId) {
        this.returnStationId = returnStationId;
    }

    public String getReturnStationName() {
        return returnStationName;
    }

    public void setReturnStationName(String returnStationName) {
        this.returnStationName = returnStationName;
    }

    public int getCoveredDestination() {
        return coveredDestination;
    }

    public void setCoveredDestination(int coveredDestination) {
        this.coveredDestination = coveredDestination;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journey)) return false;
        Journey journey = (Journey) o;
        return coveredDestination == journey.coveredDestination && duration == journey.duration && Objects.equals(id, journey.id) && Objects.equals(departureTime, journey.departureTime) && Objects.equals(returnTime, journey.returnTime) && Objects.equals(departureStationId, journey.departureStationId) && Objects.equals(departureStationName, journey.departureStationName) && Objects.equals(returnStationId, journey.returnStationId) && Objects.equals(returnStationName, journey.returnStationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureTime, returnTime, departureStationId, departureStationName, returnStationId, returnStationName, coveredDestination, duration);
    }
}
