package com.practiceproblem.mergetwojsonobjects;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "Ram");
        jsonObject1.put("age", 28);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("address", "Bhopal");

        // Merge jsonObject2 into jsonObject1
        for (String key : jsonObject2.keySet()) {
            jsonObject1.put(key, jsonObject2.get(key));
        }

        // Print the merged JSON object
        System.out.println(jsonObject1);
    }
}