package com.csvdatahandling.advanced_problem.mergetwoCSVfiles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        // Names of the CSV files to be read
        String fileName1 = "student1.csv";
        String fileName2 = "student2.csv";
        // Name of the CSV file to be created
        String mergeFile = "mergeStudent.csv";

        // Try-with-resources to ensure FileReader, CSVReader, FileWriter, and CSVWriter are closed properly
        try (CSVReader csvReader1 = new CSVReader(new FileReader(fileName1));
             CSVReader csvReader2 = new CSVReader(new FileReader(fileName2));
             CSVWriter csvWriter = new CSVWriter(new FileWriter(mergeFile))) {

            String[] line1;
            String[] line2;
            String[] mergeLine = new String[5];

            // Read each line from both CSV files and merge them based on the first column
            while (((line1 = csvReader1.readNext()) != null) && ((line2 = csvReader2.readNext()) != null)) {
                if (line1[0].equals(line2[0])) {
                    mergeLine[0] = line1[0];
                    mergeLine[1] = line1[1];
                    mergeLine[2] = line1[2];
                    mergeLine[3] = line2[1];
                    mergeLine[4] = line2[2];
                    csvWriter.writeNext(mergeLine);
                }
            }
            System.out.println("File merged successfully!");
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}