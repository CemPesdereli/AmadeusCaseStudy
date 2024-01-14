package com.cem.flight.dao;


import com.cem.flight.entity.Flight;

import java.util.List;

public interface FlightDAO {

    List<Flight> findAll();

    Flight findById(int theId);

    void save(Flight theFlight);

    void deleteById(int theId);

    List<Flight> searchFlights(String departureCity, String destinationCity, String departureDate, String returnDate);
}
