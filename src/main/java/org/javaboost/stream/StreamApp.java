package org.javaboost.stream;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

//Get all employees with salary more than 1000&sort them by both rating and salary;
public class StreamApp {

    public static void main(String[] args) {
        List<Employee> employees;
        Random random = new Random();
        Stream<Employee> employeeStream = Stream.generate(() ->
                new Employee("Name" + random.nextInt(),
                        random.nextInt(100, 10_000),
                        random.nextInt(20, 35)));

        employees = employeeStream.limit(15_500_000).toList();
        System.out.println(employees.size());
        System.out.println("--------------");

        LocalDateTime start = LocalDateTime.now();
        ArrayList<Employee> sequentialStream = employees.stream()
                .filter(employee -> employee.getSalary() > 1000)
                .sorted(Comparator.comparingInt(Employee::getRating).reversed()
                        .thenComparingInt(Employee::getSalary))
                .peek(employee -> employee.setSalary(employee.getSalary() * 2))
                .collect(new SortedCollector());
        System.out.println(sequentialStream.size());
        LocalDateTime end = LocalDateTime.now();
        System.out.println("Stream execution time = " + Duration.between(start, end).toMillis());

        start = LocalDateTime.now();
        ArrayList<Employee> parallelStream = employees.parallelStream()
                .filter(employee -> employee.getSalary() > 1000)
                .sorted(Comparator.comparingInt(Employee::getRating).reversed()
                        .thenComparingInt(Employee::getSalary))
                .peek(employee -> employee.setSalary(employee.getSalary() * 2))
                .collect(new SortedCollector());
        System.out.println(parallelStream.size());
        end = LocalDateTime.now();
        System.out.println("Parallel stream execution time = " + Duration.between(start, end).toMillis());

        System.out.println("Is ordered: " + isSorted(parallelStream));
    }

    private static boolean isSorted(ArrayList<Employee> parallelStream) {
        boolean isSorted = true;
        for (int i = 0; i < parallelStream.size() - 1; i++) {
            Employee current = parallelStream.get(i);
            Employee next = parallelStream.get(i + 1);
            if (current.getRating() < next.getRating()) {
                isSorted = false;
                break;
            } else if (current.getRating() == next.getRating()
                    && current.getSalary() > next.getSalary()) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }
}
