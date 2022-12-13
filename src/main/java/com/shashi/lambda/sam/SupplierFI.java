package com.shashi.lambda.sam;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import com.shashi.lambda.beans.Student;
import com.shashi.lambda.dao.StudentDB;

public class SupplierFI {

    // use supplier to generate student

    public static Supplier<List<Student>> supp = () -> {
        List<Student> sList = new ArrayList<>();
        Student s1 =
                new Student("Shashi", 4, 33, 2, "AMLE", StudentDB.activitiesList.subList(2, 4));
        Student s2 =
                new Student("Shashi", 4, 33, 2, "AMLE", StudentDB.activitiesList.subList(2, 5));
        sList.add(s2);
        sList.add(s1);
        return sList;
    };

    public static void main(String[] args) {
        System.out.println(supp.get());
    }
}
