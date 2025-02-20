package com.practiceproblem.readjsonfileandextract;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            // Read the JSON file as a string
            String content = new String(Files.readAllBytes(Paths.get("data.json")));

            // Create a JSONObject from the string
            JSONObject jsonObject = new JSONObject(content);

            // Extract specific fields
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");

            // Print the extracted fields
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}