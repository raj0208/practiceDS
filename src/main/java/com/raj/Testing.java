package com.raj;

import java.util.*;
import java.util.function.Supplier;

public class Testing {
    public static void main(String[] args) {
        //integer2Roman();
        //romanToInteger();
//        reverseList();

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

    }

    private void mm() {
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

    private static void integer2Roman() {
        int num = 3749;
        int[] storeInt = {1000, 900, 500, 400, 100, 90, 50, 40,  10 , 9, 5, 4, 1};
        String[] storeRoman = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"  };

        StringBuilder res = new StringBuilder();
        for(int i = 0; i < storeInt.length; i++) {
            while (num >= storeInt[i]) {
                res.append(storeRoman[i]);
                num -= storeInt[i];
            }
        }
        System.out.println(res.toString());
    }


    private static void romanToInteger() {
        String s = "III";
        Map<String, Integer> map = new HashMap();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        int result = 0;
        int n = s.length();
        int value = 0;
        for (int i = 0; i < n; i++) {
            if ((i < n -1) && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                result -= map.get(s.charAt(i));
            } else {
                result += map.get(s.charAt(i));
            }
        }
        System.out.println(result);
    }

    public static void reverseList() {
        ListNode head = getList();

        if (head == null || head.next == null)
            return;

        ListNode newHead = new ListNode(0);
        newHead.next = head;

        ListNode current = newHead;

        while (current.next != null && current.next.next != null) {
            // Nodes to be swapped
            ListNode first = current.next;
            ListNode second = current.next.next;

            // Swapping
            first.next = second.next;
            second.next = first;
            current.next = second;

            // Move current pointer two nodes ahead
            current = first;
        }

        System.out.println("Done");
    }

    private static ListNode getList() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        return head;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
