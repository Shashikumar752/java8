package com.shashi.lambda.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;
import com.shashi.lambda.beans.Student;

public class StudentDB {
    private StudentDB() {}

    public static final List<String> activitiesList = Arrays.asList("Cycling", "Rowing", "Reading",
            "Writing", "cooking", "Playing", "Teaching", "Learning");

    public static final List<String> gender = Arrays.asList("Male", "Female");

    public static List<Student> addStudent() {
        List<Student> sList = createStudents();
        Student s1 = new Student("Shashi", 4, 32, 2, "Male", activitiesList.subList(2, 4));
        Student s2 = new Student("Shashi", 4, 36, 2, "Female", activitiesList.subList(2, 5));
        sList.add(s2);
        sList.add(s1);
        return sList;
    }

    public static List<Student> createStudents() {

        Random random = new Random();
        List<String> activities = new ArrayList<>();
        IntConsumer c1 = (i) -> {
            activities.add(RandomStringUtils.randomAlphabetic(5));
        };
        IntStream.rangeClosed(0, 2).forEach(c1);

        List<Student> studentList = new ArrayList<>();
        IntConsumer c2 = (i) -> studentList.add(new Student(RandomStringUtils.randomAlphabetic(5),
                random.nextInt(3), random.nextInt(50) * 4, random.nextDouble() % 5,
                gender.get(random.nextInt(1)),
                activitiesList.subList(random.nextInt(2), random.nextInt(5) + 2)));
        // IntStream.rangeClosed(0, 1).forEach(c2.andThen(c3));

        IntConsumer c3 = (i) -> studentList.add(new Student(RandomStringUtils.randomAlphabetic(5),
                random.nextInt(3), random.nextInt(50) * 4, random.nextDouble() % 5,
                gender.get(random.nextInt(1)), activities));

        // IntStream.rangeClosed(0, 1).parallel().forEach(c3);
        IntStream.rangeClosed(0, 1).forEach(c2.andThen(c3));
        return studentList;
    }

}
