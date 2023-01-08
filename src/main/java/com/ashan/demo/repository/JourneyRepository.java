package com.ashan.demo.repository;


import com.ashan.demo.model.Journey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {
    Page<Journey> findAllByDepartureStationNameContainsAndAndReturnStationNameContaining(
            String departureStation, String returnStation, Pageable pageable);

    int countByDepartureStationId(String stationId);

    int countByReturnStationId(String stationId);

    @Query(value = "SELECT AVG(jny.covered_destination) avg_distance, AVG(jny.duration) avg_duration FROM `journey` jny " +
            "WHERE jny.departure_station_id = :stationId ;", nativeQuery = true)
    List<List<String>> averageJourneysByDepartureStationId(@Param("stationId") String stationId);

    @Query(value = "SELECT AVG(jny.covered_destination) avg_distance, AVG(jny.duration) avg_duration FROM `journey` jny " +
            "WHERE jny.return_station_id = :stationId ;", nativeQuery = true)
    List<List<String>> averageJourneysByReturnStationId(String stationId);

    @Query(value = "SELECT jny.*, COUNT(*) total FROM `journey` jny where " +
            "jny.return_station_id = :stationId GROUP by jny.departure_station_id ORDER by COUNT(*) DESC limit :limit ;",
            nativeQuery = true)
    List<List<String>> getTopDepartureStations(@Param("stationId") String stationId, @Param("limit") int limit);

    @Query(value = "SELECT jny.*, COUNT(*) total FROM `journey` jny where " +
            "jny.departure_station_id = :stationId GROUP by jny.return_station_id ORDER by COUNT(*) DESC limit :limit ;",
            nativeQuery = true)
    List<List<String>> getTopReturnStations(@Param("stationId") String stationId, @Param("limit") int limit);
}
