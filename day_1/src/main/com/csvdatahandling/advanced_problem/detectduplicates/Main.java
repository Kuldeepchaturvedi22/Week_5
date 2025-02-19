package com.csvdatahandling.advanced_problem.detectduplicates;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Name of the CSV file to be read
        String fileName = "studentDetails.csv";
        // HashSet to store seen IDs
        HashSet<String> seenIds = new HashSet<>();
        // List to store duplicate records
        List<String> duplicateRecords = new ArrayList<>();

        // Try-with-resources to ensure BufferedReader is closed properly
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                // Split the line into details
                String[] details = line.split(",");
                // Get the ID from the details
                String id = details[0];

                // Check if the ID has been seen before
                if (!seenIds.add(id)) {
                    // If the ID is a duplicate, add the line to duplicate records
                    duplicateRecords.add(line);
                }
            }

            // Check if there are any duplicate records
            if (duplicateRecords.isEmpty()) {
                System.out.println("No duplicate records found.");
            } else {
                System.out.println("Duplicate records:");
                // Print each duplicate record
                for (String record : duplicateRecords) {
                    System.out.println(record);
                }
            }
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}