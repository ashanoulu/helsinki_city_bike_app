package com.ashan.demo.service.impl;

import com.ashan.demo.model.Journey;
import com.ashan.demo.repository.JourneyRepository;
import com.ashan.demo.service.JourneyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneyServiceImpl implements JourneyService {
    private final JourneyRepository journeyRepository;

    public JourneyServiceImpl(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }


    @Override
    public Page<Journey> all(String departureStation, String returnStation, Pageable pageable) {
        return journeyRepository.findAllByDepartureStationNameContainsAndAndReturnStationNameContaining(
                departureStation, returnStation, pageable
        );
    }

    @Override
    public int countByDepartureStationId(String stationId) {
        return journeyRepository.countByDepartureStationId(stationId);
    }

    @Override
    public int countByReturnStationId(String stationId) {
        return journeyRepository.countByReturnStationId(stationId);
    }

    @Override
    public Object averageJourneysByDepartureStationId(String stationId) {
        return journeyRepository.averageJourneysByDepartureStationId(stationId);
    }

    @Override
    public Object averageJourneysByReturnStationId(String stationId) {
        return journeyRepository.averageJourneysByReturnStationId(stationId);
    }

//    @Override
//    public List<Object> getTopDepartureStations(String stationId, int limit) {
//        return journeyRepository.getTopDepartureStations(stationId, limit);
//    }

//    @Override
//    public List<Object> getTopReturnStations(String stationId, int limit) {
//        return journeyRepository.getTopReturnStations(stationId, limit);
//    }
}
