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
    public Page<Journey> all(String departureStation, String returnStation, int coveredDestination, int duration,
                             Pageable pageable) {
        return journeyRepository.findAllByDepartureStationNameContainsAndAndReturnStationNameContainingAndCoveredDestinationLessThanEqualAndDurationLessThanEqual(
                departureStation, returnStation, coveredDestination, duration, pageable
        );
    }
}
