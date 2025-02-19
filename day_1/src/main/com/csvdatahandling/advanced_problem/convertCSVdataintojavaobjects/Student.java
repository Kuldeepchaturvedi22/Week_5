package com.csvdatahandling.advanced_problem.convertCSVdataintojavaobjects;

public class Student {
    // Private fields to store student details
    private int id;
    private String name;
    private int age;
    private double marks;

    // Constructor to initialize the student object
    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    // Getter method for id
    public int getId() {
        return id;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for age
    public int getAge() {
        return age;
    }

    // Getter method for marks
    public double getMarks() {
        return marks;
    }
}