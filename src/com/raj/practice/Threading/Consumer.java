package com.raj.practice.Threading;

import java.util.List;

public class Consumer implements Runnable{
    private final List<Integer> taskQueue;

    public Consumer(List<Integer> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.isEmpty()) {
                System.out.println("Queue is empty," + Thread.currentThread().getName() + " is waiting");
                taskQueue.wait();
            }

            Thread.sleep(100);
            int i = taskQueue.remove(0);
            System.out.println("Consumed : " + i);
            taskQueue.notifyAll();
        }
    }
}
