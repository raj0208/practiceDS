package com.raj.practice.Threading;

import java.util.List;

public class Producer implements Runnable{
    private final List<Integer> taskQueue;
    private final int           MAX_CAPACITY;

    public Producer(List<Integer> taskQueue, int capacity) {
        this.taskQueue = taskQueue;
        this.MAX_CAPACITY = capacity;
    }

    @Override
    public void run() {
        int counter = 0;
        while(true) {
            try {
                produce(counter++);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void produce(int counter) throws InterruptedException {
        synchronized (taskQueue) {
            while(taskQueue.size() == MAX_CAPACITY) {
                System.out.println("Queue is full, " + Thread.currentThread().getName() + " is waiting, size: " + taskQueue.size());
                taskQueue.wait();
            }

            Thread.sleep(1000);
            taskQueue.add(counter);
            System.out.println("Produced " + counter);
            taskQueue.notifyAll();
        }
    }
}
