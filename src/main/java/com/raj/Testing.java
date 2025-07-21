package com.raj;

public class Testing {
    public static void main(String[] args) {
        System.out.println(factorial(10));
    }

    public static int factorial(int n) {
        System.out.println("n = " + n);
        if (n <= 2) {
            System.out.println("returning " + n);
            return n;
        }
        int x = n * factorial(n - 1);
        System.out.println("returning n=" + n + ", x=" + x);
        return x;
    }





}
