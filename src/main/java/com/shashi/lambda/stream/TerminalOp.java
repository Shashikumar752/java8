package com.shashi.lambda.stream;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
                System.out.println("avg age: "
                                + sList.stream().map(s -> s.getAge()).peek(System.out::println)
                                                .reduce(0, (s1, s2) -> (s1 + s2)) / sList.size());

                Optional<Student> s = sList.stream()
                                .reduce((s1, s2) -> s1.getAge() > s2.getAge() ? s1 : s2);
                System.out.println("oldest student: " + (s.isPresent() ? s.get() : null));

                System.out.println(sList.stream().map(Student::getName)
                                .filter(student -> !student.endsWith("Shashi"))
                                .collect(Collectors.joining("-")));

                Map<String, List<Student>> mapGroup =
                                sList.stream().collect(Collectors.groupingBy(Student::getGender));
                System.out.println(mapGroup);

                Map<String, Set<String>> mapGroup1 = sList.stream().collect(Collectors.groupingBy(
                                Student::getGender,
                                Collectors.mapping(Student::getName, Collectors.toSet())));
                System.out.println(mapGroup1);
                System.out.println(sList.stream().collect(Collectors.groupingBy(Student::getName,
                                Collectors.mapping(Student::getName, Collectors.counting()))));
                System.out.println(sList.stream().collect(
                                Collectors.groupingBy(Student::getName, Collectors.counting())));

                Stream.of("This is Test".replace(" ", "").split(""))
                                .collect(Collectors.groupingBy(Function.identity(),
                                                Collectors.counting()))
                                .forEach((k, v) -> System.out.println(k + " " + v));
        }
}

