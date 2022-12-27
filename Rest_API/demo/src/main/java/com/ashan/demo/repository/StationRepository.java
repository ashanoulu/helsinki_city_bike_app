package com.ashan.demo.repository;

import com.ashan.demo.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Page<Station> findAllByNimiContainsOrNamnContainsOrNameContains(String nimi, String namn, String name, Pageable pageable);
    Station findByStationId(String stationId);
    Station findTopByOrderByIdDesc();
}
