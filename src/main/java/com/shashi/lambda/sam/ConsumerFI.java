package com.shashi.lambda.sam;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import com.shashi.lambda.beans.Student;
import com.shashi.lambda.dao.StudentDB;

public class ConsumerFI {

    public static Consumer<Student> c =
            c -> System.out.println("name: " + c.getName() + " activities: " + c.getActivities());
    public static Consumer<Student> cNameAndGpA =
            c -> System.out.println("name: " + c.getName() + " gpa: " + c.getGpa());
    public static Consumer<List<Student>> cForEach = (Slist) -> {
        Slist.forEach(cNameAndGpA);
    };
    public static BiConsumer<String, List<String>> biC1 =
            (i1, i2) -> System.out.println(i1 + ": " + i2.toString());

    public static Consumer<Student> c1 = (student) -> {
        biC1.accept(student.getName(), student.getActivities());
    };

    public static void main(String[] args) {
        List<Student> studentList = StudentDB.createStudents();
        studentList.forEach(c1);
    }

}
