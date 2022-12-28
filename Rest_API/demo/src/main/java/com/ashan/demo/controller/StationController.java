package com.ashan.demo.controller;

import com.ashan.demo.common.StationViewDTO;
import com.ashan.demo.model.Station;
import com.ashan.demo.service.StationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StationController {

    private final StationService service;

    public StationController(StationService service) {
        this.service = service;
    }

    // Get all stations
    @GetMapping("/stations")
    ResponseEntity<Page<Station>> all(@RequestHeader(defaultValue = "") String name,
                            @RequestHeader(defaultValue = "nimi") String sort,
                            @RequestHeader(defaultValue = "ASC") String direction,
                            @RequestHeader int page,
                            @RequestHeader int size
    ) throws Exception {

        Pageable pageable = PageRequest.of(page, size, getSortDirection(sort, direction));

        Page<Station> stations = service.all(name, pageable);

        for (Station station: stations.getContent()) {
            addLinks(station);
        }

        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

    // Get station data by Station ID
    @GetMapping("/station/{stationId}")
    ResponseEntity<StationViewDTO> getStationById(@PathVariable("stationId") String stationId) throws Exception {
        StationViewDTO stationViewDTO = service.getStationById(stationId);
        addLinks(stationViewDTO.getStation());
        return new ResponseEntity<>(stationViewDTO, HttpStatus.OK);
    }

    // Add new station
    @PostMapping("/station")
    ResponseEntity<Station> save(@RequestBody Station station) throws Exception {
        Station newStation = service.save(station);
        addLinks(newStation);
        return new ResponseEntity<>(station, HttpStatus.OK);
    }

    // Update existing station
    @PutMapping("/station/{stationId}")
    ResponseEntity<Station> update(@PathVariable("stationId") String stationId,
                   @RequestBody Station station) throws Exception {
        Station newStation = service.update(stationId, station);
        addLinks(newStation);
        return new ResponseEntity<>(station, HttpStatus.OK);
    }

    //Delete station
    @DeleteMapping("/station/{stationId}")
    Object delete(@PathVariable("stationId") String stationId) {
        if (service.delete(stationId)) {
            return new ResponseEntity<>("Successfully deleted", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Cannot find station - " + stationId, HttpStatus.BAD_REQUEST);
    }

    // Return the Sort object according to user parameters
    private Sort getSortDirection(String sort, String direction) {
        Sort data = Sort.by(sort).ascending();

        if (direction.equals("DESC")) {
            data = Sort.by(sort).descending();
        }
        return data;
    }

    // Add links to the station object
    private Station addLinks(Station station) throws Exception {
        Link self = linkTo(methodOn(StationController.class)
                .getStationById(station.getStationId())).withSelfRel();
        station.add(self);

        Link update = linkTo(methodOn(StationController.class)
                .update(station.getStationId(), station)).withRel("update");
        station.add(update);

        Link delete = linkTo(methodOn(StationController.class)
                .delete(station.getStationId())).withRel("delete");
        station.add(delete);
        return station;
    }
}
