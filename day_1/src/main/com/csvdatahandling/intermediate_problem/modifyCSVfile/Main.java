package com.csvdatahandling.intermediate_problem.modifyCSVfile;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        // Name of the CSV file to be read
        String fileName = "employee.csv";
        // Name of the CSV file to be written with updated data
        String updatedFile = "updatedEmployee.csv";

        // Try-with-resources to ensure FileReader, CSVReader, FileWriter, and CSVWriter are closed properly
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName));
             CSVWriter csvWriter = new CSVWriter(new FileWriter(updatedFile))) {
            String[] nextLine;

            // Read each line from the CSV file until the end
            while ((nextLine = csvReader.readNext()) != null) {
                // Check if the department is "IT"
                if (nextLine[3].equals("IT")) {
                    // Parse the salary and increase it by 10%
                    String[] emp1 = nextLine;
                    int salary = Integer.parseInt(emp1[4]);
                    double percent = salary * 10 / 100;
                    salary += percent;
                    emp1[4] = Integer.toString(salary);
                    // Write the updated employee details to the new CSV file
                    csvWriter.writeNext(emp1);
                } else {
                    // Write the original employee details to the new CSV file
                    csvWriter.writeNext(nextLine);
                }
            }
            // Print success message
            System.out.println("File updated successfully!");
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}