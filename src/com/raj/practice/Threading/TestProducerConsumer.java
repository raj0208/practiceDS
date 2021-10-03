package com.raj.practice.Threading;

import java.util.ArrayList;
import java.util.List;

public class TestProducerConsumer {
    public static void main(String[] args) {
        List<Integer> taskQueue = new ArrayList<>();
        int maxCapacity = 5;
        Thread producer = new Thread(new Producer(taskQueue, maxCapacity), "Producer");
        Thread consumer = new Thread(new Consumer(taskQueue), "Consumer");

        producer.start();
        consumer.start();
    }
}
