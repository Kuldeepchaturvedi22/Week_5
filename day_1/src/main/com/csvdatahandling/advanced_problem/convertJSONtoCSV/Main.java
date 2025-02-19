package com.csvdatahandling.advanced_problem.convertJSONtoCSV;

import com.opencsv.CSVWriter;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String jsonFile = "students.json";
        String csvFile = "students.csv";
        String outputJsonFile = "students_output.json";

        // Convert JSON to CSV
        convertJsonToCsv(jsonFile, csvFile);

        // Convert CSV back to JSON
        convertCsvToJson(csvFile, outputJsonFile);
    }

    public static void convertJsonToCsv(String jsonFilePath, String csvFilePath) {
        try (InputStream is = new FileInputStream(jsonFilePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is));
             CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFilePath))) {

            JSONTokener tokener = new JSONTokener(br);
            JSONArray jsonArray = new JSONArray(tokener);

            // Convert JSON array to CSV format
            String csvData = CDL.toString(jsonArray);

            // Write CSV data to file
            csvWriter.writeAll((Iterable<String[]>) new BufferedReader(new StringReader(csvData)));
            System.out.println("JSON converted to CSV successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertCsvToJson(String csvFilePath, String jsonFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
             FileWriter fileWriter = new FileWriter(jsonFilePath)) {

            // Read CSV data
            String csvData = br.lines().reduce("", (acc, line) -> acc + line + "\n");

            // Convert CSV data to JSON array
            JSONArray jsonArray = CDL.toJSONArray(csvData);

            // Write JSON array to file
            fileWriter.write(jsonArray.toString(4));
            System.out.println("CSV converted to JSON successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}