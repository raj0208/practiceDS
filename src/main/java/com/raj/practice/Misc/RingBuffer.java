package com.raj.practice.Misc;

public class RingBuffer {
    private int[] elements = null;
    private int writePos = 0;
    private int available = 0;
    private int capacity = 5;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
    }

    public int remainingCapacity() {
        return capacity - available;
    }

    public boolean put(int element) {
        if (available < capacity) {
            if (writePos >= capacity)
                writePos = 0;
            elements[writePos] = element;
            writePos++;
            available++;
            return true;
        }

        return false;
    }

    public int take() {
        if (available == 0) {
            return Integer.MIN_VALUE;
        }

        int nextSlot = writePos - capacity;
        if (nextSlot < 0) {
            nextSlot += capacity;
        }
        available--;
        return elements[nextSlot];
    }
}


//public class RingBuffer {
//    private int[] elements = null;
//    private int writePos = 0;
//    private int available = 0;
//    private int capacity = 0;
//
//    public RingBuffer(int capacity) {
//        this.capacity = capacity;
//        this.elements = new int[capacity];
//    }
//
//    public int remainingCapacity() {
//        return this.capacity - this.available;
//    }
//
//    public boolean put(int element) {
//        if (available < capacity) {
//            if (writePos >= capacity) {
//                writePos = 0;
//            }
//            this.elements[writePos] = element;
//            writePos++;
//            available++;
//            return true;
//        }
//        return false;
//    }
//
//    public int take() {
//        if (available == 0) {
//            return Integer.MIN_VALUE;
//        }
//
//        int nextSlot = writePos - available;
//        if (nextSlot < 0) {
//            nextSlot += capacity;
//        }
//        available--;
//        return elements[nextSlot];
//    }
//}