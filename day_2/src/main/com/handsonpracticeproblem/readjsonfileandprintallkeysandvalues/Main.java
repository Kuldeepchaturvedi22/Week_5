package com.handsonpracticeproblem.readjsonfileandprintallkeysandvalues;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Create an ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file and convert to JsonNode
            JsonNode rootNode = objectMapper.readTree(new File("data.json"));

            // Print all keys and values
            printJsonNode(rootNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printJsonNode(JsonNode node) {
        if (node.isObject()) {
            // Iterate over the fields of the JSON object
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                // Print the key and value
                System.out.println(field.getKey() + ": " + field.getValue());
                // Recursively print nested objects or arrays
                printJsonNode(field.getValue());
            }
        } else if (node.isArray()) {
            // Iterate over the elements of the JSON array
            for (JsonNode arrayItem : node) {
                // Recursively print each element
                printJsonNode(arrayItem);
            }
        }
    }
}