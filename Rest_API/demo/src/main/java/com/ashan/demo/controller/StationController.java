package com.ashan.demo.controller;

import com.ashan.demo.common.StationViewDTO;
import com.ashan.demo.model.Station;
import com.ashan.demo.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StationController {

    private final StationService service;

    public StationController(StationService service) {
        this.service = service;
    }

    // Get all stations
    @Operation(summary = "Get all stations with pagination, filtration and sorting option")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - Incorrect parameters"),
            @ApiResponse(responseCode = "500", description = "Something went wrong in the serverside, please check " +
                    "parameters and try again")
    })
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
    @Operation(summary = "Get station by station ID and station statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - Incorrect parameters"),
            @ApiResponse(responseCode = "500", description = "Something went wrong in the serverside, please check " +
                    "parameters and try again")
    })
    @GetMapping("/station/{stationId}")
    ResponseEntity<StationViewDTO> getStationById(@PathVariable("stationId") String stationId) throws Exception {
        StationViewDTO stationViewDTO = service.getStationById(stationId);
        addLinks(stationViewDTO.getStation());
        return new ResponseEntity<>(stationViewDTO, HttpStatus.OK);
    }

    // Add new station
    @Operation(summary = "Add new station")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved"),
            @ApiResponse(responseCode = "404", description = "Not found - Incorrect parameters"),
            @ApiResponse(responseCode = "500", description = "Something went wrong in the serverside, please check " +
                    "parameters and try again")
    })
    @PostMapping("/station")
    ResponseEntity<Station> save(@RequestBody Station station) throws Exception {
        Station newStation = service.save(station);
        addLinks(newStation);
        return new ResponseEntity<>(station, HttpStatus.OK);
    }

    // Update existing station
    @Operation(summary = "Update station data, without ID and station ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not found - Incorrect parameters"),
            @ApiResponse(responseCode = "500", description = "Something went wrong in the serverside, please check " +
                    "parameters and try again")
    })
    @PutMapping("/station/{stationId}")
    ResponseEntity<Station> update(@PathVariable("stationId") String stationId,
                   @RequestBody Station station) throws Exception {
        Station newStation = service.update(stationId, station);
        addLinks(newStation);
        return new ResponseEntity<>(station, HttpStatus.OK);
    }

    //Delete station
    @Operation(summary = "Remove station")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - Incorrect parameters"),
            @ApiResponse(responseCode = "400", description = "Incorrect station ID - Check station ID again"),
            @ApiResponse(responseCode = "500", description = "Something went wrong in the serverside, please check " +
                    "parameters and try again")
    })
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
