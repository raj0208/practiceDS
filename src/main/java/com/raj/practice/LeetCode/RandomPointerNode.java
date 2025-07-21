package com.raj.practice.LeetCode;

public class RandomPointerNode {
    private RandomPointerNode next;
    private RandomPointerNode random;
    private int data;

    public RandomPointerNode(int data) {
        this.data = data;
    }

    public RandomPointerNode getNext() {
        return next;
    }

    public void setNext(RandomPointerNode next) {
        this.next = next;
    }

    public RandomPointerNode getRandom() {
        return random;
    }

    public void setRandom(RandomPointerNode random) {
        this.random = random;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RandomPointerNode{" +
                "next=" + (next != null ? next.getData() : " null") +
                ", random=" + (random!= null ? random.getData() : "null") +
                ", data=" + data +
                '}';
    }
}
