package com.shashi.lambda.sam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import com.shashi.lambda.beans.Student;
import com.shashi.lambda.dao.StudentDB;

public class FunctionFI {

    public static Function<List<Student>, Map<String, Double>> f = (sList) -> {
        Map<String, Double> mapOfNameAndGpa = new HashMap<>();
        sList.forEach((s) -> {
            if (mapOfNameAndGpa.get(s.getName()) == null) {
                mapOfNameAndGpa.put(s.getName(), s.getGpa());
            }
        });
        return mapOfNameAndGpa;
    };

    public static Function<List<Student>, List<Student>> fFilter = (sList) -> {
        List<Student> sListCopy = new ArrayList<Student>(sList);
        sList.forEach((s) -> {
            if (!(PredicateFI.sP.or(PredicateFI.sPr).test(s))) {
                sListCopy.remove(s);
            }
        });
        return sListCopy;
    };

    public static void main(String[] args) {
        List<Student> sTList = StudentDB.createStudents();
        System.out.println(sTList.toString());

        Map<String, Double> nameAndGpa = f.apply(sTList);
        System.out.println(nameAndGpa);

        System.out.println("after applying compose function");
        System.out.println(f.compose(fFilter).apply(sTList));

        System.out.println("after applying and then function");
        System.out.println(fFilter.andThen(f).apply(sTList));

    }

}
