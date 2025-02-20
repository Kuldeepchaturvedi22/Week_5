package com.handsonpracticeproblem.convertlistofjavaobjectsintojsonarray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Person> personList = new ArrayList<>();
            personList.add(new Person("Alice", 30));
            personList.add(new Person("Bob", 25));

            // Create an ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            // Convert the list of Person objects into a JSON array
            ArrayNode jsonArray = objectMapper.valueToTree(personList);

            // Print the JSON array
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonArray));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}