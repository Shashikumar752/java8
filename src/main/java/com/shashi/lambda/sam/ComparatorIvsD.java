package com.shashi.lambda.sam;

import java.util.Comparator;
import java.util.List;
import com.shashi.lambda.beans.Student;
import com.shashi.lambda.dao.StudentDB;

public class ComparatorIvsD {
    public static void main(String[] args) {

        Comparator<Student> cI = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return (s1.getAge() > s2.getAge()) ? 1 : ((s1.getAge() == s2.getAge()) ? 0 : -1);
            }
        };

        List<Student> sList = StudentDB.createStudents();
        sList.sort(cI);
        System.out.println("imperative way of sorting\n" + sList);

        Comparator<Student> cD1 = (s5, s6) -> (s5.getAge() > s6.getAge()) ? 1
                : ((s5.getAge() == s6.getAge()) ? 0 : -1);

        sList = StudentDB.createStudents();
        sList.sort(cD1);
        System.out.println("Declarative way 1 of sorting\n" + sList);
        Comparator<Student> cD2 = (s5, s6) -> {
            return ((Integer) s5.getAge()).compareTo(s6.getAge());
        };

        sList = StudentDB.createStudents();
        sList.sort(cD2);
        System.out.println("Declarative way 2 of sorting\n" + sList);

    }

}
