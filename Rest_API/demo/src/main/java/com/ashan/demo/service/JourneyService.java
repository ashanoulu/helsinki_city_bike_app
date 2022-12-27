package com.ashan.demo.service;

import com.ashan.demo.model.Journey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//@Service
public interface JourneyService {
    Page<Journey> all(String departureStation, String returnStation, int coveredDestination, int duration,
                      Pageable pageable);
}
