package com.ashan.demo.service;

import com.ashan.demo.model.Journey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JourneyService {
    Page<Journey> all(String departureStation, String returnStation, Pageable pageable);
    int countByDepartureStationId(String stationId);
    int countByReturnStationId(String stationId);
    Object averageJourneysByDepartureStationId(String stationId);
    Object averageJourneysByReturnStationId(String stationId);
//    List<Object> getTopDepartureStations(String stationId,  int limit);
//    List<Object> getTopReturnStations(String stationId, int limit);
}
