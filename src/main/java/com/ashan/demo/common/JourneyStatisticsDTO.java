package com.ashan.demo.common;

public class JourneyStatisticsDTO {
    private String stationId;
    private String stationName;
    private String total;

    public JourneyStatisticsDTO(){}

    public JourneyStatisticsDTO(String stationId, String stationName, String total) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.total = total;
    }

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
