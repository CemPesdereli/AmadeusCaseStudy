package com.cem.flight.controller;

import com.cem.flight.entity.Airport;
import com.cem.flight.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirportController {

    private AirportService airportService;

    @Autowired
    public AirportController(AirportService theAirportService){
        this.airportService=theAirportService;
    }

    @GetMapping("/airports")
    @Operation(summary = "This returns a list of all airports", description = "No need parameters")
    public List<Airport> findAllAirport(){
        return airportService.findAll();
    }

    @GetMapping("/airports/{airportId}")
    @Operation(summary = "This returns a single airport based on id",description = "You need to provide id.")
    public Airport getAirport(@PathVariable int airportId){

        return airportService.findById(airportId);
    }


    @PostMapping("/airports")
    @Operation(summary = "This creates a new airport",description = "You do not need to provide id. City name must be unique.")
    public void addAirport(@RequestBody Airport theAirport){

        theAirport.setId(0);
        airportService.save(theAirport);

    }


    @PutMapping("/airports")
    @Operation(summary = "This updates an existing airport",description = "You need to provide id and your desired city name.")
    public void updateAirport(@RequestBody Airport theAirport){
        airportService.save(theAirport);
    }


    @DeleteMapping("/airports/{airportId}")
    @Operation(summary = "This deletes an existing airport based on id",description = "You need to provide id. This also deletes all flights that are associated with this airport.")
    public void deleteAirport(@PathVariable int airportId){

        airportService.deleteById(airportId);

    }








}
