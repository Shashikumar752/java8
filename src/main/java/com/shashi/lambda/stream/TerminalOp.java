package com.shashi.lambda.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.shashi.lambda.beans.Student;
import com.shashi.lambda.dao.StudentDB;

public class TerminalOp {

    public static void main(String[] args) {

        List<Student> sList = StudentDB.addStudent();
        System.out.println(sList.toString());
        Map<String, List<String>> map = sList.stream().collect(
                Collectors.toMap(s -> s.getName(), s -> s.getActivities(), (l, k) -> l = k));
        System.out.println(map);
    }
}
