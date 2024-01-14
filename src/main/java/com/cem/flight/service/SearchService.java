package com.cem.flight.service;

import com.cem.flight.entity.Flight;

import java.util.List;

public interface SearchService {

    List<Flight> searchFlights(String departureCity, String destinationCity, String departureDate, String returnDate);

    List<Flight> splitFlights(List<Flight> flights);

}
