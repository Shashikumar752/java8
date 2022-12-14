package com.shashi.lambda.stream;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.shashi.lambda.beans.Student;
import com.shashi.lambda.dao.StudentDB;

public class TerminalOp {

        public static void main(String[] args) {

                List<Student> sList = StudentDB.addStudent();

                Map<String, List<String>> map = sList.stream().peek(System.out::println)
                                .collect(Collectors.toMap(s -> s.getName(), s -> s.getActivities(),
                                                (l, k) -> l = k));
                System.out.println(map);

                List<String> names = sList.stream().map(Student::getName).map(String::toUpperCase)
                                .collect(Collectors.toList());
                System.out.println("names: " + names);

                Set<String> namesSet = sList.stream().map(Student::getName).map(String::toUpperCase)
                                .collect(Collectors.toSet());
                System.out.println("names: " + namesSet);

                List<String> namesSet11 = sList.stream().map(Student::getName)
                                .map(String::toUpperCase).distinct().collect(Collectors.toList());
                System.out.println("distinct names with list: " + namesSet11);

                long count = sList.stream().map(Student::getName).map(String::toUpperCase)
                                .distinct().count();
                System.out.println("distinct names count: " + count);

                System.out.println("distinct student count: " + sList.stream().count());

        }
}

