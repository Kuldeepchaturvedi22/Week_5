package com.practiceproblem.validatejsonstructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.SchemaVersion;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGenerator;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Load JSON Schema
            JsonNode schemaNode = objectMapper.readTree(new File("schema.json"));
            // Load JSON Data
            JsonNode jsonData = objectMapper.readTree(new File("user.json"));

            // Create schema generator
            SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(objectMapper, SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON);
            SchemaGeneratorConfig config = configBuilder.build();
            SchemaGenerator generator = new SchemaGenerator(config);

            // Validate JSON
            JsonNode generatedSchema = generator.generateSchema(jsonData.getClass());
            if (generatedSchema.equals(schemaNode)) {
                System.out.println("JSON is valid!");
            } else {
                System.out.println("Invalid JSON!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}