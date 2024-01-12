package com.cem.flight.service;

import com.cem.flight.entity.Airport;
import com.cem.flight.entity.Flight;

import java.util.List;

public interface FlightService {

    List<Flight> findAll();

    Flight findById(int theId);

    void save(Flight theFlight);

    void deleteById(int theId);


}
