package com.csvdatahandling.basic_problem.writecsvfile;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        // Name of the CSV file to be written
        String fileName = "studentDetailsWriter.csv";

        // Try-with-resources to ensure FileWriter and CSVWriter are closed properly
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName))){
            // Header for the CSV file
            String[] header = {"ID", "Name", "Department", "Salary"};
            // Data rows for the CSV file
            String[] emp1 = {"104", "Alice Williams", "Finance", "62000"};
            String[] emp2 = {"105", "Bob Johnson", "Sales", "58000"};

            // Write header and data rows to the CSV file
            csvWriter.writeNext(header);
            csvWriter.writeNext(emp1);
            csvWriter.writeNext(emp2);

            // Print success message
            System.out.println("CSV file written successfully using OpenCSV!");
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}