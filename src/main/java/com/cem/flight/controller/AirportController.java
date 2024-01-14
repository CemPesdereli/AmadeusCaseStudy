package com.cem.flight.controller;

import com.cem.flight.entity.Airport;
import com.cem.flight.service.AirportService;
import io.swagger.v3.oas.annotations.Hidden;
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
    public List<Airport> findAllAirport(){
        return airportService.findAll();
    }
    @GetMapping("/airports/{airportId}")
    public Airport getAirport(@PathVariable int airportId){

        Airport theAirport = airportService.findById(airportId);
        if(theAirport==null){
            throw new RuntimeException("Airport id not found - "+airportId);
        }
        return theAirport;
    }


    @PostMapping("/airports")
    public void addAirport(@RequestBody Airport theAirport){

        theAirport.setId(0);
        airportService.save(theAirport);

    }

    @PutMapping("/airports")
    public void updateAirport(@RequestBody Airport theAirport){
        airportService.save(theAirport);
    }

    @DeleteMapping("/airports/{airportId}")
    public String deleteAirport(@PathVariable int airportId){
        Airport tempAirport =  airportService.findById(airportId);
        if(tempAirport==null){
            throw new RuntimeException("Airport id not found - "+airportId);
        }
        airportService.deleteById(airportId);
        return "Deleted is is: "+airportId;
    }








}
