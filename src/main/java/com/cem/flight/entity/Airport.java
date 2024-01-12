package com.cem.flight.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="city")
    private String city;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "departureAirport",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> departures;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "arrivalAirport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> arrivals;


    public Airport(){

    }

    public Airport(String city) {
        this.city = city;
        //departures=new ArrayList<>();
        //arrivals=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Flight> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Flight> departures) {
        this.departures = departures;
    }

    public List<Flight> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Flight> arrivals) {
        this.arrivals = arrivals;
    }
    public void addDeparture(Flight tempFlight) {
        if (departures == null) {
            departures = new ArrayList<>();
        }
        departures.add(tempFlight);
        tempFlight.setDepartureAirport(this);
    }
    public void addArrival(Flight tempFlight) {
        if (arrivals == null) {
            arrivals = new ArrayList<>();
        }
        arrivals.add(tempFlight);
        tempFlight.setArrivalAirport(this);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}

