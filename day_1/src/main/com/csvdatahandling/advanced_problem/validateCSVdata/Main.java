package com.csvdatahandling.advanced_problem.validateCSVdata;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        // Name of the CSV file to be read
        String fileName = "people.csv";

        // Regular expression for validating email addresses
        String emailRegex = "[a-zA-Z0-9-_.]+@[a-zA-Z0-9]+.?[a-zA-Z]+?$";

        // Regular expression for validating phone numbers (10 digits)
        String numberRegex = "\\d{10}";

        // Compile the regular expressions into patterns
        Pattern emailPattern = Pattern.compile(emailRegex);
        Pattern numberPattern = Pattern.compile(numberRegex);

        // Try-with-resources to ensure FileReader and CSVReader are closed properly
        try(CSVReader csvReader = new CSVReader(new FileReader(fileName))){
            String[] readLine;

            // Read each line from the CSV file until the end
            while((readLine = csvReader.readNext()) != null){
                // Match the email and phone number against the patterns
                Matcher emailMatcher = emailPattern.matcher(readLine[1]);
                Matcher numberMatcher = numberPattern.matcher(readLine[2]);

                // If either the email or phone number is invalid, print an error message
                if((!emailMatcher.matches()) || (!numberMatcher.matches())){
                    System.out.println("Error in details: " + "| Name -> " + readLine[0] + "| Email -> " + readLine[1] + "| Number -> " + readLine[2]);
                }
            }
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}