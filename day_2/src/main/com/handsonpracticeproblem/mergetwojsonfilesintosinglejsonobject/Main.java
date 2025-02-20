package com.handsonpracticeproblem.mergetwojsonfilesintosinglejsonobject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the first JSON file
            JsonNode jsonNode1 = objectMapper.readTree(new File("file1.json"));

            // Read the second JSON file
            JsonNode jsonNode2 = objectMapper.readTree(new File("file2.json"));

            // Create a new ObjectNode for merging
            ObjectNode mergedJson = objectMapper.createObjectNode();

            // Add all fields from first JSON
            mergedJson.setAll((ObjectNode) jsonNode1);

            // Add all fields from second JSON
            mergedJson.setAll((ObjectNode) jsonNode2);

            // Print the merged JSON with pretty printing
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(mergedJson);
            System.out.println(prettyJson);

            // Optionally save to a new file
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("merged.json"), mergedJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}