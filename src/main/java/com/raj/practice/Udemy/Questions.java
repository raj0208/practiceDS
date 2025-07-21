package com.raj.practice.Udemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Questions {
    public static void main(String[] args) {
        trailingZeros();
//        isPalindromeInteger();
//        findSingleNumber();
//        fizzbuzz();
    }

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
