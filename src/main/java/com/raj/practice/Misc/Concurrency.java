package com.raj.practice.Misc;

import java.sql.SQLOutput;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Concurrency {
     static class Producer implements Runnable {
        @Override
        public void run() {

        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {
        executorService();
//        blockQueue();
    }

    private static void executorService() {
        ExecutorService service = Executors.newSingleThreadExecutor();
//        service.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Executing a task in executor service");
//            }
//        });
//        service.shutdown();
        Future future1 = service.submit(new Callable(){
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                Thread.sleep(5000);
                return "Callable Result";
            }
        });

        try {
            System.out.println("future.get() = " + future1.get());
        } catch (InterruptedException | ExecutionException e ) {
            e.printStackTrace();
        }

        service = Executors.newSingleThreadExecutor();
        Future future = service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Task submitted");
                    Thread.sleep(5000);
                    System.out.println("Task completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        while(future.isDone()) {
            try {
                System.out.println("Future task awaited");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Future completed");
    }

    private static void blockQueue() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);
        queue.offer("Rajesh");
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        try {
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

