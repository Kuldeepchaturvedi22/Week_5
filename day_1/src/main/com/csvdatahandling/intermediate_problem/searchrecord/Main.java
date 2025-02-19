package com.csvdatahandling.intermediate_problem.searchrecord;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee name: ");
        String employeeName = scanner.nextLine();
        boolean check = false;

        // Name of the CSV file to be read
        String fileName = "employee.csv";

        // Try-with-resources to ensure FileReader and CSVReader are closed properly
        try(CSVReader csvReader = new CSVReader(new FileReader(fileName))){
            String[] nextLine;

            // Read each line from the CSV file until the end
            while((nextLine = csvReader.readNext()) != null){
                // Check if the employee name matches the input
                if(nextLine[1].equals(employeeName)) {
                    // Print the department and salary of the employee
                    System.out.println("Department -> " + nextLine[3] + "| Salary -> " + nextLine[4]);
                    check = true;
                }
            }
            // If the employee does not exist, print a message
            if(!check){
                System.out.println("Employee does not exist!");
            }
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}