package com.cem.flight.dao;

import com.cem.flight.entity.Airport;
import com.cem.flight.entity.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightDAOImpl implements FlightDAO{

    private EntityManager entityManager;

    @Autowired
    public FlightDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }


    @Override
    public List<Flight> findAll() {
        TypedQuery<Flight> theQuery = entityManager.createQuery("from Flight ", Flight.class);
        return theQuery.getResultList();
    }

    @Override
    public Flight findById(int theId) {
        return entityManager.find(Flight.class, theId);
    }

    @Override
    public void save(Airport theAirport) {
        entityManager.merge(theAirport);
    }

    @Override
    public void deleteById(int theId) {
        Flight flightToDelete = entityManager.find(Flight.class, theId);
        if (flightToDelete != null) {
            Airport departureAirport = flightToDelete.getDepartureAirport();
            Airport arrivalAirport = flightToDelete.getArrivalAirport();

            if (departureAirport != null) {
                departureAirport.getDepartures().remove(flightToDelete);
            }

            if (arrivalAirport != null) {
                arrivalAirport.getArrivals().remove(flightToDelete);
            }

            entityManager.remove(flightToDelete);
        }
    }
}
