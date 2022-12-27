package com.ashan.demo.service;

import com.ashan.demo.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StationService {
    Page<Station> all(String name, Pageable pageable);
    Station getStationById(String stationId);
    Station save(Station station);
}
