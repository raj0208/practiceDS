package com.raj.practice.Misc;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Reactive Java by JavaBrains
 * https://www.youtube.com/watch?v=-uQcnnY1ofY&list=PLqq-6Pq4lTTYPR2oH7kgElMYZhJd4vOGI&index=8
 */
public class ReactiveJava {
    static Supplier<Stream<Integer>> getIntStream = () -> Stream.of(1,2,3,4,5);
    static Supplier<Stream<StreamsRnd.Employee>> getEmployeeStream = () ->
        Stream.of(
                new StreamsRnd.Employee("AAA", 1, true ),
                new StreamsRnd.Employee("BBB", 2, true ),
                new StreamsRnd.Employee("CCC", 3, false ),
                new StreamsRnd.Employee("DDD", 4, false ),
                new StreamsRnd.Employee("EEE", 5, true )
        );

    public static void main(String[] args) {
        getIntStream.get()
                .flatMap(x -> getEmployeeStream.get().filter(emp -> emp.getDept() == x))
                .map(x -> x.getName())
                .forEach(System.out::println);
    }
}


