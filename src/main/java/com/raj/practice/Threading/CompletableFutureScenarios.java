package com.raj.practice.Threading;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

// https://codefarm0.medium.com/scenario-based-java-multithreading-interview-questions-with-a-focus-on-completablefuture-e749bcbb7dbb
public class CompletableFutureScenarios {
    public static void main(String args[]) throws Exception {
        scenarios();
    }

    private static void functions() {
        Consumer<String> print = msg -> System.out.println("Consumer -> " + msg);
        Supplier<String> provider = () -> {
            return "Supplier -> Data provider";
        };
        Function<String, String> processor = data -> "Function -> " + data;

        print.accept("Hello World");
        System.out.println(provider.get());
        System.out.println(processor.apply("Data processor"));

    }

    private static void scenarios() {
        //System.out.println("====== supplyAsync() =======================");
        // Scenario 1: Stock Market Live Data Aggregation
        //stockPriceAggregation();
        // Scenario 2: E-Commerce Order Processing with Parallel Tasks
        parallelProcessing();
        parallelProcessingWithException();
        // Scenario 3: Real-time Fraud Detection in Payment Transactions
        //fraudDetection();
        // Scenario 4: Parallel Data Processing in a Big Data Pipeline
        //dataprocessing();
        // Scenario 5: Asynchronous Caching with Auto-Refresh
        //asyncCaching();
        // Scenario 6: Distributed Computing with Worker Nodes
        //distributedComputing();
    }

    private static void distributedComputing() {

        final ExecutorService workerPool = Executors.newFixedThreadPool(4);
        List<Integer> dataset = Arrays.asList(10, 20, 30, 40, 50, 60);

        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (Integer chunk : dataset) {
            futures.add(CompletableFuture.supplyAsync(() -> processChunk(chunk), workerPool));
        }
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        CompletableFuture<List<Integer>> aggregatedResults = allTasks.thenApply(v ->
                futures.stream().map(CompletableFuture::join).toList()
        );
        System.out.println("✅ Final Aggregated Result: " + aggregatedResults.join());
        workerPool.shutdown();
    }

    private static Integer processChunk(Integer chunk) {
        simulateDelay(1000);
        return chunk * 2;  // Example transformation
    }

    private static void asyncCaching() {
        scheduleAutoRefresh();
        getData("product_123").thenAccept(System.out::println);
        getData("product_123").thenAccept(System.out::println);  // Uses cache
    }


    private static final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static CompletableFuture<String> getData(String key) {
        return CompletableFuture.supplyAsync(() -> {
            return cache.computeIfAbsent(key, CompletableFutureScenarios::fetchFromDatabase);
        });
    }
    private static String fetchFromDatabase(String key) {
        simulateDelay(2000);
        return "Data for " + key + " (Fetched from DB)";
    }
    public static void scheduleAutoRefresh() {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("🔄 Auto-refreshing cache...");
            cache.replaceAll((k, v) -> fetchFromDatabase(k));
        }, 5000, 500, TimeUnit.MILLISECONDS);
    }

    private static void dataprocessing() {
        List<String> rawSensorData = Arrays.asList("Sensor1:45", "Sensor2:50", "Sensor1:45", "Sensor3:60", "InvalidData");
        CompletableFuture<List<String>> cleanedData = CompletableFuture.supplyAsync(() -> cleanseData(rawSensorData));
        CompletableFuture<List<String>> enrichedData = cleanedData.thenCompose(data -> CompletableFuture.supplyAsync(() -> enrichData(data)));
        CompletableFuture<Map<String, Double>> aggregatedData = enrichedData.thenCompose(data -> CompletableFuture.supplyAsync(() -> aggregateData(data)));
        System.out.println("🚀 Final Processed Data: " + aggregatedData.join());
    }

    private static List<String> cleanseData(List<String> raw) {
        System.out.println("🔍 Cleaning data...");
        return raw.stream().filter(d -> d.contains(":")).distinct().toList();
    }

    private static List<String> enrichData(List<String> data) {
        System.out.println("📌 Enriching data...");
        return data.stream().map(d -> d + " (Location: NY)").toList();
    }

    private static Map<String, Double> aggregateData(List<String> data) {
        System.out.println("📊 Aggregating data...");
        Map<String, List<Integer>> grouped = new HashMap<>();

        for (String record : data) {
            String[] parts = record.split(":");
            grouped.computeIfAbsent(parts[0], k -> new ArrayList<>()).add(Integer.parseInt(parts[1].split(" ")[0]));
        }

        Map<String, Double> averages = new HashMap<>();
        grouped.forEach((sensor, readings) -> {
            averages.put(sensor, readings.stream().mapToInt(i -> i).average().orElse(0));
        });

        return averages;
    }

    private static void fraudDetection() {
        CompletableFuture<Double> amountRisk = analyzeAmountRisk(5000);
        CompletableFuture<Double> locationRisk = analyzeLocationRisk("Nigeria1");
        CompletableFuture<Double> historyRisk = analyzeTransactionHistory("User1234");

        CompletableFuture<Double> fraudResult =amountRisk
                .thenCombine(locationRisk, Double::sum)
                .thenCombine(historyRisk, Double::sum);

        fraudResult.thenAccept(score -> {
            if (score > 0.0) {
                System.out.println("🚨 Fraud Detected! for " + score + " risks");
            } else {
                System.out.println("✅ Transaction Approved");
            }
        }).join();
    }

    private static CompletableFuture<Double> analyzeAmountRisk(double amount) {
        return CompletableFuture.supplyAsync(() -> amount > 5000 ? 1.0 : 0D );
    }

    private static CompletableFuture<Double> analyzeLocationRisk(String country) {
        return CompletableFuture.supplyAsync(() -> "Nigeria".equals(country) ? 1D : 0D );
    }

    private static CompletableFuture<Double> analyzeTransactionHistory(String user) {
        return CompletableFuture.supplyAsync(() -> "User123".equals(user) ? 1D : 0D );
    }

    private static void parallelProcessingWithException() {
        Consumer<Integer> simulateDelay = millis -> {
            try {
                Thread.sleep(millis);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture<Void> orderProcessing = CompletableFuture.runAsync(() -> {
                    simulateDelay(500);
                    //throw new RuntimeException("Order failed");
                    System.out.println("Order completed");
                }, executor)
                .thenRunAsync(() -> {
                    simulateDelay(1000);
                    //throw new RuntimeException("Inventory failed");
                    System.out.println("Inventory completed");
                }, executor)
                .thenRunAsync(() -> {
                    simulateDelay(1000);
                    //throw new RuntimeException("Payment failed");
                    System.out.println("Payment completed");
                }, executor);

        try {
            orderProcessing.join();
            System.out.println("🚀 Order successfully processed!");
        } catch (CompletionException e) {
            System.out.println("🛑 Order processing aborted due to error: " + e.getCause().getMessage());
        }

        executor.shutdown();
    }

    private static void parallelProcessing() {
        Consumer<Integer> simulateDelay = millis -> {
            try {
                Thread.sleep(millis);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        };

        long start = System.currentTimeMillis();

        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            simulateDelay(3000);
            System.out.println("Task 1 completed in " + (System.currentTimeMillis() - start));
        });

        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            simulateDelay(1500);
            System.out.println("Task 2 completed in " + (System.currentTimeMillis() - start));
        });

        CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
            simulateDelay(500);
            System.out.println("Task 3 completed in " + (System.currentTimeMillis() - start));
        });

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
        allTasks.join();

        System.out.println("All tasks completed in " + (System.currentTimeMillis() - start));
    }

    private static void stockPriceAggregation() {
        try {
            CompletableFuture<Double> future1 = fetchStockPrice("Api 1");
            CompletableFuture<Double> future2 = fetchStockPrice("Api 2");
            CompletableFuture<Double> future3 = fetchStockPrice("Api 3");

            CompletableFuture<Double> res = future1
                    .thenCombine(future2, (x, y) -> x + y)
                    .thenCombine(future3, (x, y) -> (x + y) / 3);

            System.out.println("Average price is $" + res.get());
        } catch (Exception ex) {
            // No-op
        }
    }

    // supplyAsync()
    private static CompletableFuture<Double> fetchStockPrice(String api) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(new Random().nextInt(2000) + 500);
            if (new Random().nextBoolean()) {  // Simulating failure
                throw new RuntimeException(api + " failed!");
            }
            double price = 100 + new Random().nextDouble() * 10;
            System.out.println(api + " returned $" + price);
            return price;
        }).exceptionally(ex -> {
            System.err.println(ex.getMessage());
            System.out.println(api + " returned default value $100.0");
            return 100.0;  // Default fallback price
        });
    }

    private static void simulateDelay(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { e.printStackTrace(); }
    }

}
