package com.cem.flight.jobs;

import com.cem.flight.entity.Flight;
import com.cem.flight.service.FlightService;
import com.cem.flight.service.MockThirdPartyApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DailyFlightInfo {

    private MockThirdPartyApi mockThirdPartyApi;
    private FlightService flightService;

    public DailyFlightInfo(MockThirdPartyApi theMockThirdPartyApi, FlightService theFlightService){
        this.mockThirdPartyApi=theMockThirdPartyApi;
        this.flightService=theFlightService;
    }


    @Scheduled(cron = "0 0 * * * *") // Cron expression for running every minute
    public void execute() throws Exception {
        //System.out.println(mockThirdPartyApi.getData());
        String jsonString= mockThirdPartyApi.getData();
        ObjectMapper objectMapper = new ObjectMapper();

        // Replace jsonString with your actual JSON string

        // Read JSON string and convert to list of Flight objects
        List<Flight> flights = objectMapper.readValue(jsonString, new TypeReference<List<Flight>>() {});

        for(Flight tempFlight:flights){
            flightService.save(tempFlight);
        }
    }


}
