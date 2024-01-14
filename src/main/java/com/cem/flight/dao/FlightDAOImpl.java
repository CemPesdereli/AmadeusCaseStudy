package com.cem.flight.dao;

import com.cem.flight.entity.Airport;
import com.cem.flight.entity.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void save(Flight theFlight) {
        entityManager.merge(theFlight);
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
    public List<Flight> searchFlights(String departureCity, String destinationCity, String departureDate, String returnDate) {
        StringBuilder queryString = new StringBuilder("SELECT f FROM Flight f WHERE ");

        // Add conditions based on the parameters provided
        queryString.append("f.departureAirport.city = :departureCity ");
        queryString.append("AND f.arrivalAirport.city = :destinationCity ");

        Timestamp startOfDayDep = null;
        Timestamp endOfDayDep = null;

        Timestamp startOfDayRet = null;
        Timestamp endOfDayRet = null;



        if (departureDate != null && !departureDate.isEmpty()) {
            // Convert String to Timestamp
            startOfDayDep = convertStringToTimestamp(departureDate);
            endOfDayDep = new Timestamp(startOfDayDep.getTime() + 24 * 60 * 60 * 1000); // Add one day

            queryString.append("AND f.departureDateTime >= :startOfDayDep ");
            queryString.append("AND f.departureDateTime < :endOfDayDep ");
        }

        if (returnDate != null && !returnDate.isEmpty()) {
            // Convert String to Timestamp
            startOfDayRet = convertStringToTimestamp(returnDate);
            endOfDayRet = new Timestamp(startOfDayRet.getTime() + 24 * 60 * 60 * 1000); // Add one day

            queryString.append("AND f.returnDateTime >= :startOfDayRet ");
            queryString.append("AND f.returnDateTime < :endOfDayRet ");
        }

        // Create a query
        TypedQuery<Flight> query = entityManager.createQuery(queryString.toString(), Flight.class);

        // Set parameters
        query.setParameter("departureCity", departureCity);
        query.setParameter("destinationCity", destinationCity);

        if (startOfDayDep != null && endOfDayDep != null) {
            query.setParameter("startOfDayDep", startOfDayDep);
            query.setParameter("endOfDayDep", endOfDayDep);
        }
        if (startOfDayRet != null && endOfDayRet != null) {
            query.setParameter("startOfDayRet", startOfDayRet);
            query.setParameter("endOfDayRet", endOfDayRet);
        }

        // Execute the query and return the result
        return query.getResultList();
    }
    private Timestamp convertStringToTimestamp(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(dateString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString, e);
        }
    }
}
