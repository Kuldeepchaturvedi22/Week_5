package com.csvdatahandling.basic_problem.readcsvfile;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        // Name of the CSV file to be read
        String fileName = "studentDetails.csv";

        // Try-with-resources to ensure FileReader and CSVReader are closed properly
        try(CSVReader csvReader = new CSVReader(new FileReader(fileName))){
            String[] nextLine;

            // Read each line from the CSV file until the end
            while((nextLine = csvReader.readNext()) != null){
                // Print the contents of each line
                System.out.println("Id " + nextLine[0] + "| Name " + nextLine[1] + "| Age " + nextLine[2] + "| Marks " + nextLine[3]);
            }
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}