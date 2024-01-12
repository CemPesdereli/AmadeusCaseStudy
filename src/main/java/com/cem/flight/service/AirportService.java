package com.cem.flight.service;


import com.cem.flight.entity.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> findAll();

    Airport findById(int theId);

    void save(Airport theAirport);

    void deleteById(int theId);

}
