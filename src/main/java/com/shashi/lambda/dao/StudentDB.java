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

    public static List<Student> createStudents() {

        Random random = new Random();
        List<String> gender = Arrays.asList("Male", "Female");

        // imperative way of creating List of String
        List<String> activitiesList = Arrays.asList("Cycling", "Rowing", "Reading", "Writing",
                "cooking", "Playing", "Teaching", "Learning");

        // declarative way of generating the List<String>
        List<String> activities = new ArrayList<>();
        IntConsumer c1 = (i) -> {
            activities.add(RandomStringUtils.randomAlphabetic(5));
        };
        IntStream.rangeClosed(0, 2).forEach(c1);

        List<Student> studentList = new ArrayList<>();
        IntConsumer c2 = (i) -> studentList
                .add(new Student(RandomStringUtils.randomAlphabetic(5), random.nextInt(3),
                        random.nextInt(50), random.nextDouble() % 5, gender.get(random.nextInt(1)),
                        activitiesList.subList(random.nextInt(2), random.nextInt(5) + 2)));
        // IntStream.rangeClosed(0, 1).forEach(c2.andThen(c3));

        IntConsumer c3 = (i) -> studentList.add(new Student(RandomStringUtils.randomAlphabetic(5),
                random.nextInt(3), random.nextInt(50), random.nextDouble() % 5,
                gender.get(random.nextInt(1)), activities));

        // IntStream.rangeClosed(0, 1).parallel().forEach(c3);
        IntStream.rangeClosed(0, 1).forEach(c2.andThen(c3));
        return studentList;
    }

}
