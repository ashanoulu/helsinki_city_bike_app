package com.ashan.demo.controller;


import com.ashan.demo.model.Journey;
import com.ashan.demo.service.JourneyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class JourneyController {

    private final JourneyService service;

    JourneyController(JourneyService service) {
        this.service = service;
    }

    //Get all journeys with pagination
    @GetMapping("/journeys")
    ResponseEntity<Page<Journey>> all(@RequestHeader String departureStation,
                      @RequestHeader String returnStation,
                      @RequestHeader(defaultValue = "0") int page,
                      @RequestHeader(defaultValue = "10") int size,
                      @RequestHeader(defaultValue = "departureStationName") String sort,
                      @RequestHeader(defaultValue = "ASC") String direction
                      ) throws Exception {

        Pageable pageable = PageRequest.of(page, size, getSortDirection(sort, direction));
        return new ResponseEntity<>(service.all(departureStation, returnStation, pageable), HttpStatus.OK);
    }

    // Return the Sort object according to user parameters
    private Sort getSortDirection(String sort, String direction) {
        Sort data = Sort.by(sort).ascending();

        if (direction.equals("DESC")) {
            data = Sort.by(sort).descending();
        }
        return data;
    }
}
