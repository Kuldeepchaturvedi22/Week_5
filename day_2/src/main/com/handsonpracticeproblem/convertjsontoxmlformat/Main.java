package com.handsonpracticeproblem.convertjsontoxmlformat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instances for JSON and XML
            ObjectMapper jsonMapper = new ObjectMapper();
            XmlMapper xmlMapper = new XmlMapper();

            // Read JSON from file
            JsonNode jsonNode = jsonMapper.readTree(new File("data.json"));

            // Convert JSON to XML string with pretty printing
            String xmlString = xmlMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(jsonNode);

            // Print the XML
            System.out.println(xmlString);

            // Optionally save to XML file
            xmlMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("output.xml"), jsonNode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}