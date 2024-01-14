package com.cem.flight.entity;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name="airport")

public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="city")
    @Schema(example = "Istanbul")
    private String city;


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "departureAirport",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Flight> departures;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "arrivalAirport", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Flight> arrivals;


    public Airport(){

    }

    public Airport(String city) {
        this.city = city;

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



    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}

