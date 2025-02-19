package com.csvdatahandling.advanced_problem.generateCSVreport;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";
        String csvFile = "employeeReport.csv";

        String query = "SELECT employee_id, name, department, salary FROM employees";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile))) {

            // Write CSV header
            String[] header = {"Employee ID", "Name", "Department", "Salary"};
            csvWriter.writeNext(header);

            // Write data rows
            while (resultSet.next()) {
                String[] data = {
                        resultSet.getString("employee_id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("salary")
                };
                csvWriter.writeNext(data);
            }

            System.out.println("CSV report generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}