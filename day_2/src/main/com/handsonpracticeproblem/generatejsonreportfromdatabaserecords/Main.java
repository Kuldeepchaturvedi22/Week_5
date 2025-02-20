package com.handsonpracticeproblem.generatejsonreportfromdatabaserecords;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Create statement
            Statement stmt = conn.createStatement();

            // Execute query
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();

            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode jsonArray = objectMapper.createArrayNode();

            // Convert each record to JSON
            while (rs.next()) {
                ObjectNode jsonObject = objectMapper.createObjectNode();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metadata.getColumnName(i);
                    String value = rs.getString(i);
                    jsonObject.put(columnName, value);
                }
                jsonArray.add(jsonObject);
            }

            // Write JSON report to file
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("report.json"), jsonArray);

            // Print the report
            System.out.println("Generated JSON Report:");
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(jsonArray));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}