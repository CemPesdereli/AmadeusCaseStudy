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


        Airport theAirport = airportDAO.findById(theId);
        if(theAirport==null){
            throw new RuntimeException("Airport id not found - "+theId);
        }
        return theAirport;
    }

    @Override
    @Transactional
    public void save(Airport theAirport) {
        airportDAO.save(theAirport);

    }

    @Override
    @Transactional
    public void deleteById(int theId) {

        Airport tempAirport =  airportDAO.findById(theId);
        if(tempAirport==null){
            throw new RuntimeException("Airport id not found - "+theId);
        }
        airportDAO.deleteById(theId);
    }
}
