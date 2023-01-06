package com.ashan.demo.controller;


import com.ashan.demo.model.Journey;
import com.ashan.demo.service.JourneyService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@OpenAPIDefinition(info=@Info(title="Helsinki Bike App"))
public class JourneyController {

    Logger logger = LoggerFactory.getLogger(JourneyController.class);

    private final JourneyService service;

    JourneyController(JourneyService service) {
        this.service = service;
    }

    /* Get all journeys with pagination */
    @Operation(summary = "Get all journeys with pagination, filtration and sorting option")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - Incorrect parameters"),
            @ApiResponse(responseCode = "500", description = "Something went wrong in the serverside, please check " +
                    "parameters and try again")
    })
    @GetMapping("/journeys")
    ResponseEntity<Page<Journey>> all(@RequestHeader String departureStation,
                      @RequestHeader String returnStation,
                      @RequestHeader(defaultValue = "0") int page,
                      @RequestHeader(defaultValue = "10") int size,
                      @RequestHeader(defaultValue = "departureStationName") String sort,
                      @RequestHeader(defaultValue = "ASC") String direction
                      ) throws Exception {

        logger.info("Search params - " + departureStation + "," + returnStation);
        logger.info("Begin station all method");

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
