package com.cem.flight.controller;

import com.cem.flight.entity.Flight;
import com.cem.flight.service.FlightService;
import com.cem.flight.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    private FlightService flightService;
    private SearchService searchService;

    @Autowired
    public FlightController(FlightService theFlightService,SearchService theSearchService){
        this.flightService=theFlightService;
        this.searchService=theSearchService;
    }


    @GetMapping("/flights")
    public List<Flight> findAllFlight(){
        return flightService.findAll();
    }

    @GetMapping("/flights/{flightId}")
    public Flight getFlight(@PathVariable int flightId){

        Flight theFlight = flightService.findById(flightId);
        if(theFlight==null){
            throw new RuntimeException("Flight id not found - "+flightId);
        }
        return theFlight;
    }

    @PostMapping("/flights")
    public void addFlight(@RequestBody Flight theFlight){

        theFlight.setId(0);
        flightService.save(theFlight);

    }

    @PutMapping("/flights")
    public void updateFlight(@RequestBody Flight theFlight){

        flightService.save(theFlight);

    }

    @DeleteMapping("/flights/{flightId}")
    public String deleteFlight(@PathVariable int flightId){
        Flight tempFlight =  flightService.findById(flightId);
        if(tempFlight==null){
            throw new RuntimeException("Flight id not found - "+flightId);
        }
        flightService.deleteById(flightId);
        return "Deleted is is: "+flightId;
    }

    @GetMapping("/flights/search")
    public List<Flight> searchFlights( @RequestParam String departureCity,
                                       @RequestParam String destinationCity,
                                       @RequestParam(required = false) String departureDate,
                                       @RequestParam(required = false) String returnDate){

        List<Flight> flights = searchService.searchFlights(departureCity, destinationCity, departureDate, returnDate);
        if(returnDate!=null){
            return searchService.splitFlights(flights);
        }
        return flights;
    }



}
