package com.cem.flight.service;

import com.cem.flight.dao.FlightDAO;
import com.cem.flight.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightDAO flightDAO;

    @Autowired
    public FlightServiceImpl(FlightDAO theFlightDAO){
        this.flightDAO=theFlightDAO;
    }


    @Override
    public List<Flight> findAll() {
        return flightDAO.findAll();
    }

    @Override
    public Flight findById(int theId) {

        Flight theFlight = flightDAO.findById(theId);
        if(theFlight==null){
            throw new RuntimeException("Flight id not found - "+theId);
        }
        return theFlight;
    }

    @Override
    @Transactional
    public void save(Flight theFlight) {
        flightDAO.save(theFlight);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {

        Flight tempFlight =  flightDAO.findById(theId);
        if(tempFlight==null){
            throw new RuntimeException("Flight id not found - "+theId);
        }
        flightDAO.deleteById(theId);

    }
}
