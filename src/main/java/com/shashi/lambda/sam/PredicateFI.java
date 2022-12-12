package com.shashi.lambda.sam;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import com.shashi.lambda.beans.Student;
import com.shashi.lambda.dao.StudentDB;

public class PredicateFI {

    public static Predicate<Student> sP = (s) -> s.getActivities().contains("cooking");
    public static Predicate<Student> sPr = (s) -> s.getActivities().contains("Reading");
    public static Consumer<Student> c1 = (s1) -> {
        if (sP.test(s1)) {
            ConsumerFI.c1.accept(s1);
        }
    };

    public static void main(String[] args) {
        List<Student> sList = StudentDB.createStudents();
        findCook(sList);
    }

    private static void findCook(List<Student> sList) {
        sList.forEach(ConsumerFI.c);
        // sList.stream().filter(sP).collect(Collectors.toList()).forEach(ConsumerFI.c1);
        sList.forEach(c1);
    }

}
