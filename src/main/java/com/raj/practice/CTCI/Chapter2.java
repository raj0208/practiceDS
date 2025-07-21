package com.raj.practice.CTCI;

import java.util.Arrays;
import java.util.Stack;

public class Chapter2 {
    public static void main(String[] args) {
        sortStack();
    }

    private static void sortStack() {
        Stack<Integer> stack = new Stack<>();
        for (Integer integer : new Integer[]{5, 7, 10, 4, 2, 1}) {
            stack.add(integer);
        }

        Stack<Integer> temp = new Stack<>();
        while (stack.size() > 0) {
            Integer value = stack.pop();

            while (temp.size() > 0 && value < temp.peek())
                stack.push(temp.pop());

            temp.push(value);
        }

        for (Integer integer : temp) {
            stack.push(integer);
        }

        System.out.println(Arrays.toString(stack.toArray()));
    }
}
