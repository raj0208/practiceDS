package com.raj.practice.Misc;

import com.sun.javadoc.ProgramElementDoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static class Product {
        String name;
        double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return this.name;
        }

        public double getPrice() {
            return this.price;
        }
    }

    private static List<Product> getProducts() {
        return List.of(
                new Product("Apple Products", 100),
                new Product("Google", 200),
                new Product("Microsoft", 50)
        );
    }

    public static void main(String[] args) {
        // Operations to stream
        getProducts().stream()
                    // Intermediate operations (methods which returns Stream)
                    .filter(product -> product.getName().equals("Apple"))
                    .map(Product::getName)
                    // Terminal operations (methods which returns data)
                    .forEach(System.out::println);
    }

    private static void getStream() {
        List<Product> product = getProducts();

        Stream<Product> stream1 = product.stream();

        String[] array = new String[] {"A", "B", "C"};
        Stream<String> stream2 = Arrays.stream(array);

        //factory methods
        Stream<String> stream3 = Stream.of("one", "two");
        Stream<String> stream4 = Stream.ofNullable("four");

        Stream<?> stream5 = Stream.empty();

        // start is nclusive, end is exclusive
        IntStream dice = new Random().ints(1,7);

//        try(BufferedReader in = new BufferedReader(new FileReader("text.txt", StandardCharsets.UTF_8))) {
//            in.lines().forEach(System.out::println);
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void filteringTransforming() {
        List<Product> products = getProducts();

        products.stream()
                .filter(p -> p.getPrice() == 100)
                .forEach(System.out::println);

        // map is one to one transforming
        products.stream()
                .map(p -> String.format("The price of %s is $ %.2f", p.getName(), p.getPrice()))
                .forEach(System.out::println);

        // flatmap is one to many transforming
        Pattern spaces = Pattern.compile("\\s+");
        products.stream()
                .flatMap(p -> spaces.splitAsStream(p.getName()))
                .forEach(System.out::println);


        Stream<Stream<String>> stream = products.stream()
                .map(p -> spaces.splitAsStream(p.getName()));

        Stream<String> flatMapStream = products.stream()
                .flatMap(p -> spaces.splitAsStream(p.getName()));
    }

    private static void searchInStream() {
        List<Product> products = getProducts();

        Optional<Product> opt  = products.stream()
            .filter(p -> p.getName().equals("Google"))
                // ternimal operation
            // .findFirst();
            .findAny();
        opt.ifPresent(System.out::println);

        boolean flag = products.stream()
                // any product
                // .anyMatch(p -> p.getName().equals("Google"));
                // all product
                //.allMatch(p -> p.getPrice() < 0);
                .noneMatch(p-> p.getPrice() > 100);
        System.out.println("Does the list contain at least one product" + flag);

        // find a particular element
        // filter().findFirst() or findAny()

        // Check if elements exists
        //anyMatch(), allMatch() or noneMatch()

        // Short-circuiting
        // findFirst(), findAny(), anyMatch()

        // Not short circuiting
        // allMatch() and noneMatch()
    }

    private static void reducingandcollecting() {
        List<Product> products = getProducts();

        List<String> names = products.stream()
                .filter(p -> p.getPrice() >= 100)
                .map(Product::getName)
                .collect(Collectors.toList());
        System.out.println(names);

        String s = products.stream()
                .map(Product::getName)
                .distinct()
                .map(String::toUpperCase)
                .collect(Collectors.joining("; "));
        System.out.println(s);
    }
}
