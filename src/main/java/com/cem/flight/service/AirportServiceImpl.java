package com.cem.flight.service;

import com.cem.flight.dao.AirportDAO;
import com.cem.flight.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService{

    private AirportDAO airportDAO;

    @Autowired
    public AirportServiceImpl(AirportDAO theAirportDAO) {
        this.airportDAO=theAirportDAO;
    }


    @Override
    public List<Airport> findAll() {
        return airportDAO.findAll();
    }

    @Override
    public Airport findById(int theId) {
        return airportDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Airport theAirport) {
        airportDAO.save(theAirport);

    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        airportDAO.deleteById(theId);
    }
}
