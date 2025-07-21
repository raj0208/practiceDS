package com.raj.practice.Misc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    private static List<Product> getProducts() {
        return List.of(
                new Product("Apple Products", BigDecimal.valueOf(100)),
                new Product("Google", BigDecimal.valueOf(200)),
                new Product("Microsoft", BigDecimal.valueOf(50)),
                new Product("Alphabet", BigDecimal.valueOf(50)),
                new Product("X", BigDecimal.valueOf(200))
        );
    }

    private static List<MovieMetaData> getMovieMetaData() {
        return List.of(
                new MovieMetaData(1, "AB", "cast"),
                new MovieMetaData(1, "MD", "director"),
                new MovieMetaData(2, "DK", "cast"),
                new MovieMetaData(2, "RK", "cast"),
                new MovieMetaData(2, "SG", "director11")
        );
    }

    public static void main(String[] args) {
        // Operations to stream
//        getProducts().stream()
//                    // Intermediate operations (methods which returns Stream)
//                    .filter(product -> product.getName().equals("Apple"))
//                    .map(Product::getName)
//                    // Terminal operations (methods which returns data)
//                    .forEach(System.out::println);

//        generatingBuildingStream();
//        reducingStream();
//        reduceNcollect();
//        groupingBy();
        groupByMovie();
    }

    private static void getStream() {
        List<Product> product = getProducts();

        Stream<Product> stream1 = product.stream();

        String[] array = new String[]{"A", "B", "C"};
        Stream<String> stream2 = Arrays.stream(array);

        //factory methods
        Stream<String> stream3 = Stream.of("one", "two");
        Stream<String> stream4 = Stream.ofNullable("four");

        Stream<?> stream5 = Stream.empty();

        // start is nclusive, end is exclusive
        IntStream dice = new Random().ints(1, 7);

//        try(BufferedReader in = new BufferedReader(new FileReader("text.txt", StandardCharsets.UTF_8))) {
//            in.lines().forEach(System.out::println);
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void filteringTransforming() {
        List<Product> products = getProducts();

        products.stream()
                .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(100)) == 0)
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

        Optional<Product> opt = products.stream()
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
                .noneMatch(p -> p.getPrice().intValue() > 100);
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
                .filter(p -> p.getPrice().intValue() >= 100)
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

    private static void generatingBuildingStream() {
        Stream<UUID> uuids = Stream.generate(UUID::randomUUID);
        uuids.limit(10).forEach(System.out::println);

        Stream<BigInteger> powersOfTwo = Stream.iterate(BigInteger.ONE, n -> n.multiply(BigInteger.TWO));
        powersOfTwo.limit(10).forEach(System.out::println);

        Stream.iterate('A', l -> l <= 'Z', l -> (char) (l + 1)).forEach(System.out::print);

        Stream.Builder<String> builder = Stream.builder();
        builder.add("one");
        builder.add("two");
        builder.build().forEach(System.out::println);
    }

    private static void reducingStream() {
        List<Product> products = getProducts();

        Optional<BigDecimal> optionalDouble = products.stream()
                .map(Product::getPrice)
                //.reduce((result, element) -> result.add(element));
                .reduce(BigDecimal::add);
        optionalDouble.ifPresentOrElse(
                total -> System.out.println("Total value is " + total),
                () -> System.out.println("There is no products"));

        BigDecimal total = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total is " + total);

        BigDecimal total2 = products.stream()
                .reduce(BigDecimal.ZERO, (reduce, product) -> reduce.add(product.getPrice()), BigDecimal::add);
        System.out.println("Total is " + total2);
    }

    private static void reduceNcollect() {
        List<Product> products = getProducts();

        // reduce - immutable collection
        ArrayList<String> reduce = products.stream()
                .reduce(
                        new ArrayList<String>(),
                        (list, product) -> {
                            ArrayList<String> newList = new ArrayList<>(list);
                            newList.add(product.getName());
                            return newList;
                        },
                        (list1, list2) -> {
                            ArrayList<String> newList = new ArrayList<>(list1);
                            newList.addAll(list2);
                            return newList;
                        }
                );
        reduce.forEach(System.out::println);

        // collect - mutable collection
        List<String> collect = products.stream().collect(
                ArrayList::new,
                (list, product) -> list.add(product.getName()),
                ArrayList::addAll
        );
        collect.forEach(System.out::println);

        List<String> mapCollect = products.stream().map(Product::getName).collect(Collectors.toList());
        mapCollect.forEach(System.out::println);
    }

    private static void groupByMovie() {
        List<MovieMetaData> movieMetaData = getMovieMetaData();

        Map<Integer, List<MovieMetaData>> movieMetaDataById = movieMetaData.stream()
                .collect(Collectors.groupingBy(MovieMetaData::getId));
        String director = movieMetaDataById.get(2).stream()
                .filter(x -> x.role.equals("director")).map(MovieMetaData::getName).collect(Collectors.joining(","));
        String cast = movieMetaDataById.get(2).stream()
                .filter(x -> x.role.equals("cast")).map(MovieMetaData::getName).map(String::toUpperCase)
                .sorted((s1, s2) -> s2.compareTo(s1))
                .collect(Collectors.joining(","));

        Map<Integer, List<String>> movieById = movieMetaData.stream()
                .collect(Collectors.groupingBy(MovieMetaData::getId, Collectors.mapping(MovieMetaData::getName, Collectors.toList())));

    }

    private static void groupingBy() {
        List<Product> products = getProducts();

        Map<BigDecimal, List<Product>> productsByName = products.stream()
                .collect(Collectors.groupingBy(Product::getPrice));

        Map<BigDecimal, List<String>> productNamesByPrice = products.stream()
                .collect(Collectors.groupingBy(Product::getPrice, Collectors.mapping(Product::getName, Collectors.toList())));

//        Map<String, BigDecimal> totalPerName = products.stream()
//                .collect(Collectors.groupingBy(Product::getName),
//                        Collectors.mapping(Product::getPrice, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

    }

    private static void partitioningStream() {
        List<Product> products = getProducts();

        BigDecimal price = new BigDecimal("100");
        Map<Boolean, List<Product>> partitionedProducts = products.stream()
                .collect(Collectors.partitioningBy(product -> product.getPrice().compareTo(price) < 0));

        System.out.println("Cheap products");
        partitionedProducts.get(true).forEach(System.out::println);

        System.out.println("Expensive products");
        partitionedProducts.get(false).forEach(System.out::println);
    }

    private static void parallelStream() {
        List<Product> products = getProducts();

        List<String> names = products.parallelStream()  // .stream()
                .filter(p -> p.getPrice().intValue() > 100)
                .map(Product::getName)
                .collect(Collectors.toList());

        // DoubleStream
        // IntStream
        // LongStream
    }

    public static class Product {
        String name;
        BigDecimal price;

        public Product(String name, BigDecimal price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return this.name;
        }

        public BigDecimal getPrice() {
            return this.price;
        }
    }

    public static class MovieMetaData {
        private final int id;
        private final String name;
        private final String role;

        public MovieMetaData(int id, String name, String role) {
            this.id = id;
            this.name = name;
            this.role = role;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }
    }
}
