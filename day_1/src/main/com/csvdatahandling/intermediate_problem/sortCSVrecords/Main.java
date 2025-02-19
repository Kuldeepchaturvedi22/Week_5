package com.csvdatahandling.intermediate_problem.sortCSVrecords;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // Name of the CSV file to be read
        String fileName = "employee.csv";
        List<String[]> records = new ArrayList<>();

        // Try-with-resources to ensure FileReader and CSVReader are closed properly
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;

            // Read each line from the CSV file and add to the list
            while ((nextLine = csvReader.readNext()) != null) {
                records.add(nextLine);
            }
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }

        // Sort the records by Salary in descending order
        records.sort((a, b) -> Integer.compare(Integer.parseInt(b[4]), Integer.parseInt(a[4])));

        // Print the top 5 highest-paid employees
        System.out.println("Top 5 highest-paid employees:");
        for (int i = 0; i < Math.min(5, records.size()); i++) {
            String[] record = records.get(i);
            System.out.println("ID: " + record[0] + ", Name: " + record[1] + ", Age: " + record[2] + ", Department: " + record[3]+", Salary: "+record[4]);
        }
    }
}