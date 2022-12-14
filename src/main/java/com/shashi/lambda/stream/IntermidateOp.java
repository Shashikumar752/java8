package com.shashi.lambda.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.shashi.lambda.beans.Student;
import com.shashi.lambda.dao.StudentDB;
import com.shashi.lambda.sam.ComparatorIvsD;
import com.shashi.lambda.sam.PredicateFI;

public class IntermidateOp {

        public static void main(String[] args) {
                List<Student> sList = StudentDB.addStudent();
                // use of flat map
                Set<String> stringList = sList.stream().map(Student::getActivities)
                                .flatMap(s -> s.stream().map(String::toUpperCase))
                                .collect(Collectors.toSet());
                System.out.println("stringList: " + stringList);

                List<String> stringList1 = sList.stream()
                                .flatMap(s -> s.getActivities().stream().map(String::toUpperCase))
                                .distinct().sorted().collect(Collectors.toList());
                System.out.println("stringList1: " + stringList1);

                System.out.println(sList.stream().filter(PredicateFI.sP).sorted(ComparatorIvsD.cD1)
                                .collect(Collectors.toList()));

                Student s1 = new Student("James", 4, 17, 2, "AMLE",
                                Arrays.asList("Playing", "Teaching"));
                Student s2 = s1;
                sList.add(s1);
                sList.add(s2);
                System.out.println("list of student after adding 2 copies of an object \n\n");
                System.out.println(sList);
                System.out.println();
                System.out.println();
                System.out.println(sList.stream().distinct().collect(Collectors.toList()));
                System.out.println();
                System.out.println();
                System.out.println(sList.stream().collect(Collectors.toSet()));

        }
}
