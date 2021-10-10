package com.raj.practice;

public class Misc {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().wait();

        Thread.currentThread().notify();
    }
}
