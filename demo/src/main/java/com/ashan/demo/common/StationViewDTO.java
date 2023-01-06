package com.ashan.demo.common;

import com.ashan.demo.model.Station;

public class StationViewDTO {
    private String stationId;
    private Station station;
    private StationStatisticsDTO stationStatistics;

    public StationViewDTO(){}

    public StationViewDTO(String stationId, Station station, StationStatisticsDTO stationStatistics) {
        this.stationId = stationId;
        this.station = station;
        this.stationStatistics = stationStatistics;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public StationStatisticsDTO getStationStatistics() {
        return stationStatistics;
    }

    public void setStationStatistics(StationStatisticsDTO stationStatistics) {
        this.stationStatistics = stationStatistics;
    }
}
