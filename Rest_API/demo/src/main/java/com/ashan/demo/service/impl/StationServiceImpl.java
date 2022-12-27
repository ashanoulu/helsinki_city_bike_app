package com.ashan.demo.service.impl;

import com.ashan.demo.model.Station;
import com.ashan.demo.repository.StationRepository;
import com.ashan.demo.service.StationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Page<Station> all(String name, Pageable pageable) {
        return stationRepository.findAllByNimiContainsOrNamnContainsOrNameContains(name, name, name, pageable);
    }

    @Override
    public Station getStationById(String stationId) {
        return stationRepository.findByStationId(stationId);
    }

    @Override
    public Station save(Station station) {
        Station lastData = stationRepository.findTopByOrderByIdDesc();
        station.setStationId(String.valueOf(lastData.getId() + 1) + "0");
        return stationRepository.save(station);
    }
}
