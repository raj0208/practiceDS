package com.raj.practice.Threading;

public class TestYield {
    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " produced " + i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                //Thread.yield();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " consumed " + i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                //Thread.yield();
            }
        }, "Consumer");

        producer.setPriority(Thread.MIN_PRIORITY);
        consumer.setPriority(Thread.MAX_PRIORITY);

        producer.start();
        consumer.start();
    }
}
