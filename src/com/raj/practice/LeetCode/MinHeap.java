package com.raj.practice.LeetCode;

import java.util.PriorityQueue;


public class MinHeap extends PriorityQueue<Integer> {
    private int capacity;
    private int size;


    public MinHeap(int capacity) {
        super(capacity, (a,b) -> b-a);
        this.capacity = capacity;
        this.size = 0;

    }

    public void offerNew(int num) {
        if (capacity == size) {
            if (num < this.peek()) {
                this.poll();
                size--;
            } else return;
        }

        this.offer(num);
        size++;
    }

    public int pollLast() {
        if (size == 0)
            throw new IllegalArgumentException("No item in the queue");

        size--;
        return (int)this.poll();
    }

    public int top() {
        return (int)this.peek();
    }
}
