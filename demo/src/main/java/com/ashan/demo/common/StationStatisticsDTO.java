package com.ashan.demo.common;

import java.util.List;

public class StationStatisticsDTO {
    String stationId;
    String stationName;
    Long departureCount;
    Long returnCount;
    double averageDepartureDistance;
    double averageReturnDistance;
    double averageDepartureDuration;
    double averageReturnDuration;
    List<JourneyStatisticsDTO> departStationStat;
    List<JourneyStatisticsDTO> returnStationStat;

    public StationStatisticsDTO() {}

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Long getDepartureCount() {
        return departureCount;
    }

    public void setDepartureCount(Long departureCount) {
        this.departureCount = departureCount;
    }

    public Long getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(Long returnCount) {
        this.returnCount = returnCount;
    }

    public double getAverageDepartureDistance() {
        return averageDepartureDistance;
    }

    public void setAverageDepartureDistance(double averageDepartureDistance) {
        this.averageDepartureDistance = averageDepartureDistance;
    }

    public double getAverageReturnDistance() {
        return averageReturnDistance;
    }

    public void setAverageReturnDistance(double averageReturnDistance) {
        this.averageReturnDistance = averageReturnDistance;
    }

    public double getAverageDepartureDuration() {
        return averageDepartureDuration;
    }

    public void setAverageDepartureDuration(double averageDepartureDuration) {
        this.averageDepartureDuration = averageDepartureDuration;
    }

    public double getAverageReturnDuration() {
        return averageReturnDuration;
    }

    public void setAverageReturnDuration(double averageReturnDuration) {
        this.averageReturnDuration = averageReturnDuration;
    }

    public List<JourneyStatisticsDTO> getDepartStationStat() {
        return departStationStat;
    }

    public void setDepartStationStat(List<JourneyStatisticsDTO> departStationStat) {
        this.departStationStat = departStationStat;
    }

    public List<JourneyStatisticsDTO> getReturnStationStat() {
        return returnStationStat;
    }

    public void setReturnStationStat(List<JourneyStatisticsDTO> returnStationStat) {
        this.returnStationStat = returnStationStat;
    }
}
