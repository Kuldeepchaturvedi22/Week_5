package com.handsonpracticeproblem.filterjsondata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file and convert to list of Person objects
            List<Person> personList = objectMapper.readValue(new File("data.json"), new TypeReference<List<Person>>() {});

            // Filter records where age > 25
            List<Person> filteredList = personList.stream()
                    .filter(person -> person.getAge() > 25)
                    .collect(Collectors.toList());

            // Print the filtered list
            filteredList.forEach(person -> System.out.println(person.getName() + ": " + person.getAge()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}