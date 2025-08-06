package com.raj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Testing {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        System.out.println(map.get("key"));
        map.compute("key", (key, value) -> value == null ? null : value.toUpperCase(Locale.ROOT) );
        System.out.println(map.get("key"));




        Deque<String> dequeAsStack = new ArrayDeque <>();

        dequeAsStack.push("one");
        dequeAsStack.push("two");
        dequeAsStack.push("three");

        System.out.println(dequeAsStack.pop());
        System.out.println(dequeAsStack.pop());
        System.out.println(dequeAsStack.pop());

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
