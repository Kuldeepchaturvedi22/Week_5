package com.practiceproblem.createjsonobjectforstudent;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        // Create a new JSONObject instance
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        jsonObject.put("name", "John");
        jsonObject.put("age", 21);
        jsonObject.put("subject", new String[]{"physics", "maths", "chemistry"});

        // Print the JSON object
        System.out.println(jsonObject);
    }
}