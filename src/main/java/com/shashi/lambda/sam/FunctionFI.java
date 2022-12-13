package com.shashi.lambda.sam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
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

    public static BiFunction<List<Student>, Predicate<Student>, List<Student>> bifFilter =
            (sList, pS) -> {
                List<Student> sListCopy = new ArrayList<Student>(sList);
                sList.forEach((s) -> {
                    if (!(pS.test(s))) {
                        sListCopy.remove(s);
                    }
                });
                return sListCopy;
            };
    // Implemetation is same as fFilter as input and output is of same type
    public static UnaryOperator<List<Student>> uoF = (sList) -> {
        List<Student> sListCopy = new ArrayList<Student>(sList);
        sList.forEach((s) -> {
            if (!(PredicateFI.sP.or(PredicateFI.sPr).test(s))) {
                sListCopy.remove(s);
            }
        });
        return sListCopy;
    };

    // Implemetation is same as fFilter as input and output is of same type
    public static BinaryOperator<List<Student>> boF = (sList, asList) -> {
        // List<Student> sListCopy = new ArrayList<Student>(sList);
        sList.addAll(asList);

        return sList;
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

        System.out.println("after applying unaryoperator funtion, result should be same as above");
        System.out.println(uoF.andThen(f).apply(sTList));

        System.out.println("Bifunction implementation result");
        System.out.println(bifFilter.andThen(f).apply(sTList, PredicateFI.sP));

        List<Student> sList1 = SupplierFI.supp.get();
        System.out.println("sList1: " + sList1.toString());
        List<Student> sList2 = SupplierFI.supp1.get();
        System.out.println("sList2: " + sList2.toString());
        System.out.println("Binary Operator implementation result");
        System.out.println(boF.apply(sList1, sList2));


        Arrays.stream("BANANA".split(""))
                .collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + " = " + v));
    }

}
