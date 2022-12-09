package com.shashi.lambda.beans;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
public @Data class Student {
    private String name;
    private int grade;
    private int age;
    private double gpa;
    private String gender;
    private List<String> activities;
}
