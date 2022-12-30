package com.ashan.demo.service.impl;

import com.ashan.demo.controller.JourneyController;
import com.ashan.demo.model.Journey;
import com.ashan.demo.repository.JourneyRepository;
import com.ashan.demo.service.JourneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class JourneyServiceImpl implements JourneyService {

    Logger logger = LoggerFactory.getLogger(JourneyServiceImpl.class);

    private final JourneyRepository journeyRepository;

    public JourneyServiceImpl(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }


    @Override
    public Page<Journey> all(String departureStation, String returnStation, Pageable pageable) {
        try {
            return journeyRepository.findAllByDepartureStationNameContainsAndAndReturnStationNameContaining(
                    departureStation, returnStation, pageable
            );
        } catch (Exception e) {
            logger.error("Error occurred in query -", e);
            return null;
        }
    }

    @Override
    public int countByDepartureStationId(String stationId) {
        try {
            return journeyRepository.countByDepartureStationId(stationId);
        } catch (Exception e) {
            logger.error("Error occurred in query -", e);
            return 0;
        }
    }

    @Override
    public int countByReturnStationId(String stationId) {
        try {
            return journeyRepository.countByReturnStationId(stationId);
        } catch (Exception e) {
            logger.error("Error occurred in query -", e);
            return 0;
        }
    }

    @Override
    public Object averageJourneysByDepartureStationId(String stationId) {
        try {
            return journeyRepository.averageJourneysByDepartureStationId(stationId);
        } catch (Exception e) {
            logger.error("Error occurred in query -", e);
            return 0;
        }
    }

    @Override
    public Object averageJourneysByReturnStationId(String stationId) {
        try {
            return journeyRepository.averageJourneysByReturnStationId(stationId);
        } catch (Exception e) {
            logger.error("Error occurred in query -", e);
            return 0;
        }

    }
}
