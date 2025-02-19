package com.csvdatahandling.advanced_problem.readlargeCSVfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "largeFile.csv";
        int chunkSize = 100;
        int recordCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineCount = 0;

            while ((line = br.readLine()) != null) {
                // Process the line (e.g., parse CSV fields)
                lineCount++;
                recordCount++;

                // If chunk size is reached, display the count and reset the line count
                if (lineCount == chunkSize) {
                    System.out.println("Processed " + recordCount + " records so far.");
                    lineCount = 0;
                }
            }

            // Display the final count if there are remaining lines
            if (lineCount > 0) {
                System.out.println("Processed " + recordCount + " records in total.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
