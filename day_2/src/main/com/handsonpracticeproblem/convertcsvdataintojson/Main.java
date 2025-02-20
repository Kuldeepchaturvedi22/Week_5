package com.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Read CSV file
            BufferedReader reader = new BufferedReader(new FileReader("data.csv"));

            // Read header line
            String headerLine = reader.readLine();
            String[] headers = headerLine.split(",");

            // Read data lines
            String line;
            List<String[]> dataLines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                dataLines.add(line.split(","));
            }

            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode jsonArray = objectMapper.createArrayNode();

            // Convert each CSV line to JSON object
            for (String[] data : dataLines) {
                ObjectNode jsonObject = objectMapper.createObjectNode();
                for (int i = 0; i < headers.length; i++) {
                    jsonObject.put(headers[i].trim(), data[i].trim());
                }
                jsonArray.add(jsonObject);
            }

            // Write JSON to file with pretty printing
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("output.json"), jsonArray);

            // Print JSON
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(jsonArray));

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}