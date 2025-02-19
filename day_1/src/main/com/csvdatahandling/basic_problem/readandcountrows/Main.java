package com.csvdatahandling.basic_problem.readandcountrows;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        // Name of the CSV file to be read
        String fileName = "studentDetails.csv";
        // Initialize a counter to count the number of records
        int count = 0;

        // Try-with-resources to ensure FileReader and CSVReader are closed properly
        try(CSVReader csvReader = new CSVReader(new FileReader(fileName))){
            String[] nextLine;

            // Read each line from the CSV file until the end
            while((nextLine = csvReader.readNext()) != null){
                // Increment the counter for each line read
                count++;
            }
            // Print the total number of records in the CSV file
            System.out.println("Number of records in this csv file is : " + (count-1));
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}