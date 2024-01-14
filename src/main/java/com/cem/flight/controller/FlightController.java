package com.cem.flight.controller;

import com.cem.flight.entity.Flight;
import com.cem.flight.service.FlightService;
import com.cem.flight.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "This returns a list of all flights", description = "No need parameters")
    public List<Flight> findAllFlight(){
        return flightService.findAll();
    }

    @GetMapping("/flights/{flightId}")
    @Operation(summary = "This returns a single flight based on id",description = "You need to provide id.")
    public Flight getFlight(@PathVariable int flightId){

        return flightService.findById(flightId);
    }

    @PostMapping("/flights")
    @Operation(summary = "This creates a new flight",description = "You need to provide ids of existing airports, city names are not important. departureDateTime can not be null. You can provide returnDateTime and price.")
    public void addFlight(@RequestBody Flight theFlight){

        theFlight.setId(0);
        flightService.save(theFlight);

    }

    @PutMapping("/flights")
    @Operation(summary = "This updates an existing flight",description = "You need to provide id of " +
            "flight and you can change departure and arrival airports by providing their ids, city name is not important. You can also update dates and price.")
    public void updateFlight(@RequestBody Flight theFlight){

        flightService.save(theFlight);

    }

    @DeleteMapping("/flights/{flightId}")
    @Operation(summary = "This deletes an existing flight based on id",description = "You need to provide id.")
    public void deleteFlight(@PathVariable int flightId){
        Flight tempFlight =  flightService.findById(flightId);
        flightService.deleteById(flightId);

    }

    @GetMapping("/flights/search")
    @Operation(summary = "This lists flights based on your parameters",description = "You need to provide departureCity and destinationCity. Date format is yyyy-MM-dd Dates can be null. If you specify return date list will be split first element" +
            "will be normal flight and the next one will be for the return one with reversed city names and departure date as return date. There will be no return date.")
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
