package com.ashan.demo.service;

import com.ashan.demo.common.StationViewDTO;
import com.ashan.demo.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StationService {
    Page<Station> all(String name, Pageable pageable);
    StationViewDTO getStationById(String stationId);
    Station save(Station station);
    Station update(String stationId, Station station);
    boolean delete(String stationId);
}
