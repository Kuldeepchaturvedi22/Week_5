package com.handsonpracticeproblem.validateemailfieldusingjsonschema;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Define the JSON Schema for email validation
            String schemaStr = """
                {
                  "$schema": "http://json-schema.org/draft-07/schema#",
                  "type": "object",
                  "properties": {
                    "email": {
                      "type": "string",
                      "format": "email"
                    }
                  },
                  "required": ["email"]
                }
                """;

            // Create JSON to validate
            String jsonStr = """
                {
                  "email": "test@example.com"
                }
                """;

            // Create JsonSchema instance
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            JsonSchema schema = factory.getSchema(schemaStr);

            // Parse JSON
            JsonNode jsonNode = objectMapper.readTree(jsonStr);

            // Validate the JSON against the schema
            Set<ValidationMessage> validationResult = schema.validate(jsonNode);

            // Print validation results
            if (validationResult.isEmpty()) {
                System.out.println("Email is valid!");
            } else {
                System.out.println("Email validation errors:");
                validationResult.forEach(vm -> System.out.println(vm.getMessage()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}