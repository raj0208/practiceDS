package com.raj.practice.Misc;



import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsRnd {
    public static void main(String[] args) {
        int[] arr = {1,3,2,4,3,5,6,2,1,0};
        IntStream.of(arr)
                .max()
                .ifPresent(System.out :: println);

        IntSummaryStatistics statistics = IntStream.of(arr).summaryStatistics();
        System.out.println("statistics = " + statistics);

        // distinct numbers
        IntStream.of(arr)
                .distinct()
                .sorted()
                .limit(2)
                .forEach(System.out::println);

        getEmployees().stream()
            .sorted(Comparator.comparing(Employee::getName).reversed())
            .filter(employee -> employee.isActive)
            .limit(2)
            .map(Employee::getName)
            .collect(Collectors.toList())
            .forEach(System.out::println);

        System.out.println(getEmployees().stream()
                .sorted(Comparator.comparing(Employee::getName).reversed())
                .map(Employee::getName)
                .collect(Collectors.joining(",")));

//        Map<String, Long> empByDept = getEmployees().stream().filter(e -> e.isActive).collect(Collectors.groupingBy(e -> e.dept, Collectors.counting()));
//        for(String key : empByDept.keySet()) {
//            System.out.println(key + ":" + empByDept.get(key));
//        }


    }

    public static class Employee {
        private String name;
        private int dept;
        private boolean isActive;

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", dept=" + dept +
                    ", isActive=" + isActive +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDept() {
            return dept;
        }

        public void setDept(int dept) {
            this.dept = dept;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public Employee(String name, int dept, boolean isActive) {
            this.name = name;
            this.dept = dept;
            this.isActive = isActive;
        }

        public List<Employee> getEmployees() {
            List<Employee> employees = new ArrayList<>();
            return employees;
        }
    }

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("AAA", 101, true));
        employees.add(new Employee("ZZZ", 101, true));
        employees.add(new Employee("TTT", 102, true));
        employees.add(new Employee("DDD", 103, false));

        return employees;
    }
}
