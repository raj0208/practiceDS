package com.raj.practice.Udemy;

import com.raj.practice.LeetCode.LeetCode;
import com.raj.practice.LeetCode.RandomPointerNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Questions {
    public static void main(String[] args) {
//        integer2Roman();
//        roman2Integer();
//        mypower();
//        reverseInteger();
//        trailingZeros();
//        isPalindromeInteger();
//        findSingleNumber();
//        fizzbuzz();
//        duplicateRandomPtrLink();
        twoSum();
    }

    private static void twoSum() {
        int[] nums = {2,7,11,15 };
        int target = 9;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {

            }
        }
    }

    private static void duplicateRandomPtrLink() {
        RandomPointerNode head = LeetCode.getLinkedList();

        RandomPointerNode temp;
        RandomPointerNode curr = head;

        // add new nodes next to current node
        while (curr != null) {
            temp = new RandomPointerNode(curr.getData());
            temp.setNext(curr.getNext());
            curr.setNext(temp);
            curr = temp.getNext();
        }

        curr = head;
        // add random points to new nodes
        while (curr != null) {
            curr.getNext().setRandom(curr.getRandom().getNext());
            curr = curr.getNext().getNext();
        }

        RandomPointerNode newHead = head.getNext();
        curr = head;
        while (curr != null) {
            temp = curr .getNext();
            curr.setNext(temp != null ? temp.getNext() : null);
            curr = temp;
        }

        BiConsumer<String, RandomPointerNode> printList = (s, n) -> {
            System.out.println("List: " + s);
            while (n != null) {
                System.out.println(n);
                n = n.getNext();
            }
        };

        printList.accept("Original",head);
        printList.accept("New Copy", newHead);
    }

    // TC : O(n), SC: O(1)
    private static void roman2Integer() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        String[] romans = { "LVIII", "MCMXCIV", "CMXCVIII" };

        for(String roman : romans) {
            int result = 0;
            int n = roman.length();
            for (int i = 0; i < n ; i++) {
                if ((i < n -1) && map.get(roman.charAt(i)) < map.get(roman.charAt(i + 1))) {
                    result -= map.get(roman.charAt(i));
                } else {
                    result += map.get(roman.charAt(i));
                }
            }

            System.out.println("Roman '" + roman + "' = " + result);
        }
    }

    // TC: O(13 * log(n)), SC : O(13)
    private static void integer2Roman() {
        int[] storeInt = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] storeRoman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int[] nums = {58, 1994, 998, 3558};
        /*
        Integer 58 = 'LVIII'
        Integer 1994 = 'MCMXCIV'
        Integer 998 = 'CMXCVIII'
        Integer 3558 = 'MMMDLVIII'
         */
        StringBuilder romanNumeral = new StringBuilder();

        for (int num : nums) {
            int n = num;
            romanNumeral.setLength(0);

            for (int i = 0; i < storeInt.length; i++) {
                while (n >= storeInt[i]) {
                    String symbol = storeRoman[i];
                    romanNumeral.append(symbol);
                    n -= storeInt[i];
                }
            }

            System.out.println("Integer " + num + " = '" + romanNumeral + "'");
        }
    }

    // TC : O(log2  n), SC O(1)
    private static void mypower() {
        double x = 2;
        int n = 100;

        long num = Math.abs((long) n);
        double result = 1.0;
        while (num != 0) {
            if (num % 2 == 1) { // if num is odd number
                result = result * x;
                num = num - 1;
            }
            x = x * x;
            num = num / 2;
        }

        System.out.println( n < 0 ? 1.0 / result : result);
    }

    // TC : O(log10 x), SC: O(1)
    private static void reverseInteger() {
        int[] nums = {-123,345234, -123123, Integer.MAX_VALUE, Integer.MIN_VALUE};
        int max = Integer.MAX_VALUE / 10;
        int min = Integer.MIN_VALUE / 10;

        for(int num : nums) {
            System.out.println("Number is " + num);
            boolean flag = true;
            int reversed = 0;

            while (num != 0) {
                int lastdigit = num % 10;

                if ((reversed > max) || (reversed < min)) {
                    flag = false;
                    break;
                }

                reversed = reversed * 10 + lastdigit;
                num = num / 10;
            }

            System.out.println(flag ? reversed : "Breached");
        }
    }

    // TC O(log5 n), SC: O(1)
    private static void trailingZeros() {
        int n = 125;

        int count = 0;
        int currPowerOfFive = 5;
        while ( n >= currPowerOfFive) {
            count += (n / currPowerOfFive);
            currPowerOfFive *= 5;
        }

        System.out.println("Trailing zeroes for " + n + "! will be " + count);
    }

    // TC O(log x), SC : O(1)
    private static void isPalindromeInteger() {
        int[] numbers = { 121, 1221, 12321, -12, 0, 120 };

        for(int number : numbers) {

            // if number is negative or is not zero but ends with zero
            if (number < 0) {
                System.out.println("Number is not palindrome coz 0");
                continue;
            }

            if (number != 0 && number % 10 == 0) {
                System.out.println("Number is not palindrome coz ending with zero");
                continue;
            }

            int firstHalf = number;
            int reverse = 0;

            while (firstHalf > reverse) {
                int lastdigit = firstHalf % 10;
                reverse = (reverse * 10) + lastdigit;
                firstHalf /= 10;
            }

            System.out.println("Number " + number + " is palindrome? " + ((firstHalf == reverse) || (firstHalf == reverse / 10)));
        }
    }

    private static void findSingleNumber() {
        int[] nums = {1,2,3,2,3,1,4,5,4};
        int singleNumber = 0;
        for (int num : nums) {
            singleNumber ^= num;
        }
        System.out.println("SingleNumber is " + singleNumber);
    }

    public static void fizzbuzz() {
        int n = 16;

        List<String> ans = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 == 0) {
                ans.add("FizzBuzz");
            } else if (i % 3 == 0) {
                ans.add("Fizz");
            } else if (i % 5 == 0 ) {
                ans.add("Buzz");
            } else {
                ans.add(i + "");
            }
        }

        System.out.println(Arrays.toString(ans.toArray()));
    }
}
