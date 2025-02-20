package com.practiceproblem.convertjavaobjectintojson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        try {
            // Create an instance of ObjectMapper for JSON processing
            ObjectMapper objectMapper = new ObjectMapper();

            // Create a Car object with specified attributes
            Car car = new Car("Tata nano", "green", 200000.0, "city car", "TATA");

            // Convert the Car object to a JSON string
            String jsonString = objectMapper.writeValueAsString(car);

            // Print the JSON string
            System.out.println(jsonString);
        } catch (Exception e) {
            // Print the exception message if an error occurs
            e.getMessage();
        }
    }
}