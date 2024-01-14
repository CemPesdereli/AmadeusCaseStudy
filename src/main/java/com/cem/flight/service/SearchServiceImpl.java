package com.cem.flight.service;

import com.cem.flight.dao.FlightDAO;
import com.cem.flight.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{

    private FlightDAO flightDAO;

    @Autowired
    public SearchServiceImpl(FlightDAO theFlightDAO){
        this.flightDAO=theFlightDAO;
    }


    @Override
    public List<Flight> searchFlights(String departureCity, String destinationCity, String departureDate, String returnDate) {
        return flightDAO.searchFlights(departureCity,destinationCity,departureDate,returnDate);
    }

    @Override
    public List<Flight> splitFlights(List<Flight> flights) {
        List<Flight> populatedFlights = new ArrayList<>();

        for(Flight theFlight: flights){
            populatedFlights.add(theFlight);
            Flight tempFlight = new Flight(theFlight.getArrivalAirport(),theFlight.getDepartureAirport(),theFlight.getReturnDateTime(),theFlight.getPrice());
            populatedFlights.add(tempFlight);

        }
        return populatedFlights;
    }
}
