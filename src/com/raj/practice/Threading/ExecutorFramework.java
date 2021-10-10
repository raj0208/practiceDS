package com.raj.practice.Threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorFramework {
    private static ExecutorService executorService = null;
    private static volatile Future taskOneResults = null;
    private static volatile Future taskTwoResults = null;

    public static void main(String[] args) {
        executorService = Executors.newFixedThreadPool(2);

        while(true) {
            try {
                checkTask();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkTask() throws Exception {
        if (taskOneResults == null
            || taskOneResults.isDone()
            || taskOneResults.isCancelled()) {
            taskOneResults = executorService.submit(() -> {
                while(true) {
                    System.out.println("Executing task one" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        if (taskTwoResults == null
                || taskTwoResults.isDone()
                || taskTwoResults.isCancelled()) {
            taskTwoResults = executorService.submit(() -> {
                while(true) {
                    System.out.println("Executing task two " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
