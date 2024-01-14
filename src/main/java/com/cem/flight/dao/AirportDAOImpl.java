package com.cem.flight.dao;

import com.cem.flight.entity.Airport;
import com.cem.flight.entity.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AirportDAOImpl implements AirportDAO {


    private EntityManager entityManager;

    @Autowired
    public AirportDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }


    @Override
    public List<Airport> findAll() {
        TypedQuery<Airport> theQuery = entityManager.createQuery("from Airport ", Airport.class);
        return theQuery.getResultList();
    }

    @Override
    public Airport findById(int theId) {
        return entityManager.find(Airport.class, theId);
    }

    @Override
    public void save(Airport theAirport) {
        entityManager.merge(theAirport);

    }

    @Override
    public void deleteById(int theId) {

        Airport airportToDelete = entityManager.find(Airport.class, theId);
        if (airportToDelete != null) {
            // Remove flights associated with the airport
            List<Flight> departureFlights = new ArrayList<>(airportToDelete.getDepartures());
            List<Flight> arrivalFlights = new ArrayList<>(airportToDelete.getArrivals());


            for (Flight departureFlight : departureFlights) {
                departureFlight.setDepartureAirport(null);

                Airport departureAirport = departureFlight.getDepartureAirport();
                Airport arrivalAirport = departureFlight.getArrivalAirport();
                if (departureAirport != null) {
                    departureAirport.getDepartures().remove(departureFlight);
                }

                if (arrivalAirport != null) {
                    arrivalAirport.getArrivals().remove(departureFlight);
                }
                entityManager.remove(departureFlight);
            }

            for (Flight arrivalFlight : arrivalFlights) {
                arrivalFlight.setArrivalAirport(null);
                Airport departureAirport = arrivalFlight.getDepartureAirport();
                Airport arrivalAirport = arrivalFlight.getArrivalAirport();
                if (departureAirport != null) {
                    departureAirport.getDepartures().remove(arrivalFlight);
                }

                if (arrivalAirport != null) {
                    arrivalAirport.getArrivals().remove(arrivalFlight);
                }


                entityManager.remove(arrivalFlight);
            }

            entityManager.remove(airportToDelete);
            entityManager.flush();
        }


    }
}
