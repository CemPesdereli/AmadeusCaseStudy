package com.cem.flight.dao;

import com.cem.flight.entity.Airport;

import java.util.List;

public interface AirportDAO {

    List<Airport> findAll();

    Airport findById(int theId);

    void save(Airport theAirport);

    void deleteById(int theId);




}
