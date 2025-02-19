package com.csvdatahandling.advanced_problem.convertCSVdataintojavaobjects;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Name of the CSV file to be read
        String fileName = "studentDetails.csv";
        // List to store Student objects
        ArrayList<Student> students = new ArrayList<>();

        // Try-with-resources to ensure FileReader and CSVReader are closed properly
        try(CSVReader csvReader = new CSVReader(new FileReader(fileName))){
            String[] details;

            // Read each line from the CSV file and create Student objects
            while((details = csvReader.readNext()) != null){
                Student student = new Student(Integer.parseInt(details[0]), details[1], Integer.parseInt(details[2]), Integer.parseInt(details[3]));
                students.add(student);
            }

            // Print the details of each student
            for(Student student : students){
                System.out.print(student.getId() + " ");
                System.out.print(student.getName() + " ");
                System.out.print(student.getAge() + " ");
                System.out.println(student.getMarks());
            }
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        } catch (CsvValidationException e) {
            // Handle CSV validation exception
            throw new RuntimeException(e);
        }
    }
}