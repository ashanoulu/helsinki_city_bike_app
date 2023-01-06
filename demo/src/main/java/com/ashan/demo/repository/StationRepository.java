package com.ashan.demo.repository;

import com.ashan.demo.model.Station;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Page<Station> findAllByNimiContainsOrNamnContainsOrNameContains(String nimi, String namn, String name, Pageable pageable);
    Station findByStationId(String stationId);
    Station findTopByOrderByIdDesc();
}
