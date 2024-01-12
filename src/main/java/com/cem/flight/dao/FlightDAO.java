package com.cem.flight.dao;

import com.cem.flight.entity.Airport;
import com.cem.flight.entity.Flight;

import java.util.List;

public interface FlightDAO {

    List<Flight> findAll();

    Flight findById(int theId);

    void save(Airport theAirport);

    void deleteById(int theId);
}
