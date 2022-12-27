package com.ashan.demo.controller;

import com.ashan.demo.model.Station;
import com.ashan.demo.service.StationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class StationController {

    private final StationService service;

    public StationController(StationService service) {
        this.service = service;
    }

    @GetMapping("/stations")
    Page<Station> all(@RequestHeader(defaultValue = "") String name,
                      @RequestHeader(defaultValue = "nimi") String sort,
                      @RequestHeader(defaultValue = "ASC") String direction,
                      @RequestHeader int page,
                      @RequestHeader int size
    ) throws Exception {

        Pageable pageable = PageRequest.of(page, size, getSortDirection(sort, direction));
        return service.all(name, pageable);
    }

    @GetMapping("/station/{stationId}")
    Station getStationById(@PathVariable("stationId") String stationId) throws Exception {
        return service.getStationById(stationId);
    }

    @PostMapping("/station")
    Station save(@RequestBody Station station) {
        return service.save(station);
    }

    private Sort getSortDirection(String sort, String direction) {
        Sort data = Sort.by(sort).ascending();

        if (direction.equals("DESC")) {
            data = Sort.by(sort).descending();
        }
        return data;
    }
}
