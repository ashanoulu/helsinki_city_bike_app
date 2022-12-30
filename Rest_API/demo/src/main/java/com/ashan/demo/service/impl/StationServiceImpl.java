package com.ashan.demo.service.impl;

import com.ashan.demo.common.JourneyStatisticsDTO;
import com.ashan.demo.common.StationStatisticsDTO;
import com.ashan.demo.common.StationViewDTO;
import com.ashan.demo.model.Journey;
import com.ashan.demo.model.Station;
import com.ashan.demo.repository.JourneyRepository;
import com.ashan.demo.repository.StationRepository;
import com.ashan.demo.service.StationService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StationServiceImpl implements StationService {

    Logger logger = LoggerFactory.getLogger(StationServiceImpl.class);

    private final StationRepository stationRepository;
    private final JourneyRepository journeyRepository;

    public StationServiceImpl(StationRepository stationRepository, JourneyRepository journeyRepository) {
        this.stationRepository = stationRepository;
        this.journeyRepository = journeyRepository;
    }

    @Override
    public Page<Station> all(String name, Pageable pageable) {
        return stationRepository.findAllByNimiContainsOrNamnContainsOrNameContains(name, name, name, pageable);
    }

    @Override
    public StationViewDTO getStationById(String stationId) {
        try {
            long departureCount = journeyRepository.countByDepartureStationId(stationId);
            long returnCount = journeyRepository.countByReturnStationId(stationId);
            List<List<String>> averageJourneysByDeparture = journeyRepository.averageJourneysByDepartureStationId(stationId);
            List<List<String>> averageJourneysByReturn = journeyRepository.averageJourneysByReturnStationId(stationId);
            List<List<String>> topDepartureStations = journeyRepository.getTopDepartureStations(stationId, 5);
            List<List<String>> topReturnStations = journeyRepository.getTopReturnStations(stationId, 5);

            StationStatisticsDTO stationStat = new StationStatisticsDTO();
            stationStat.setStationId(stationId);
            stationStat.setDepartureCount(departureCount);
            stationStat.setReturnCount(returnCount);
            stationStat.setAverageDepartureDistance(Double.valueOf(averageJourneysByDeparture.get(0).get(0)));
            stationStat.setAverageReturnDistance(Double.valueOf(averageJourneysByReturn.get(0).get(0)));
            stationStat.setAverageDepartureDuration(Double.valueOf(averageJourneysByDeparture.get(0).get(1)));
            stationStat.setAverageReturnDuration(Double.valueOf(averageJourneysByReturn.get(0).get(1)));

            List<JourneyStatisticsDTO> journeyStatDepartList = new ArrayList<>();
            List<JourneyStatisticsDTO> journeyStatReturnList = new ArrayList<>();

            for (List list : topDepartureStations) {
                JourneyStatisticsDTO journeyStatDTO = new JourneyStatisticsDTO();
                journeyStatDTO.setStationId((String) list.get(3));
                journeyStatDTO.setStationName((String) list.get(4));
                journeyStatDTO.setTotal((String) list.get(9));
                journeyStatDepartList.add(journeyStatDTO);
            }

            for (List list : topReturnStations) {
                JourneyStatisticsDTO journeyStatDTO = new JourneyStatisticsDTO();
                journeyStatDTO.setStationId((String) list.get(5));
                journeyStatDTO.setStationName((String) list.get(6));
                journeyStatDTO.setTotal((String) list.get(9));
                journeyStatReturnList.add(journeyStatDTO);
            }

            stationStat.setDepartStationStat(journeyStatDepartList);
            stationStat.setReturnStationStat(journeyStatReturnList);

            Station station = stationRepository.findByStationId(stationId);

            return new StationViewDTO(stationId, station, stationStat);
        } catch (Exception e) {
            logger.error("Error occurred in query -", e);
            return null;
        }
    }

    @Override
    @Transactional
    public Station save(Station station) {
        try {
            Station lastData = stationRepository.findTopByOrderByIdDesc();
            station.setStationId(String.valueOf(lastData.getId() + 1) + "0");
            return stationRepository.save(station);
        } catch (Exception e) {
            logger.error("Error occurred in query -", e);
            return null;
        }
    }

    @Override
    @Transactional
    public Station update(String stationId, Station station) {
        try {
            Station existingStation = stationRepository.findByStationId(stationId);
            if (existingStation != null){
                Station clonedStation = existingStation;
                existingStation = station;
                existingStation.setId(clonedStation.getId());
                existingStation.setStationId(clonedStation.getStationId());
                return stationRepository.save(existingStation);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("Error occurred in query -", e);
            return null;
        }
    }

    @Override
    public boolean delete(String stationId) {
        try {
            Station station = stationRepository.findByStationId(stationId);
            if (station != null) {
                stationRepository.delete(station);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Error occurred in query -", e);
            return false;
        }
    }
}
