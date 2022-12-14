package com.shashi.lambda.sam;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import com.shashi.lambda.beans.Student;
import com.shashi.lambda.dao.StudentDB;

public class PredicateFI {

    public static final Predicate<Student> sP = s -> s.getActivities().contains("cooking");
    public static final Predicate<Student> sPr = s -> s.getActivities().contains("Reading");
    public static final Consumer<Student> c1 = s1 -> {
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
        sList.forEach(c1);
    }

}
