package com.cem.flight.service;

import com.cem.flight.entity.Flight;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class MockThirdPartyApi  {


    public String getData() throws Exception {
        // Path to your JSON file
        String jsonFilePath = "src/main/resources/mockdata.json";

        // Read JSON file and parse
        try {
            JsonNode jsonNode = readJsonFile(jsonFilePath);


            return jsonNode.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failed";
    }

    private JsonNode readJsonFile(String jsonFilePath) throws IOException {
        // Create ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON file and parse
        File file = new File(jsonFilePath);
        return objectMapper.readTree(file);
    }
}