package com.ashan.demo.repository;


import com.ashan.demo.model.Journey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {
    Page<Journey> findAllByDepartureStationNameContainsAndAndReturnStationNameContainingAndCoveredDestinationLessThanEqualAndDurationLessThanEqual(
            String departureStation, String returnStation, int coveredDestination, int duration, Pageable pageable);
}
