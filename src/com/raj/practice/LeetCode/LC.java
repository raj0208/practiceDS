package com.raj.practice.LeetCode;

public class LC {
    public static void main(String[] args) {
        fizzbuzz();
        singleNumber();
        reverseNumber();
        palindromeNumber();
        pow();
        nthRoot();
    }

    private static void nthRoot() {
        double x = 27;
        int n = 3;
        double left = 1.0;
        double right = x;
        double error = 0.001;

        while ((right - left) > error) {
            double mid = left + ((right - left) / 2);
            if (multiply(mid, n) > x)
                right = mid;
            else
                left = mid;
        }

        System.out.println(left);
    }

    private static double multiply(double x, int n) {
        double ans = 1;
        for (int i = 0; i < n; i++) {
            ans = ans * x;
        }

        return ans;
    }

    private static void pow() {
        int x = 5;
        int topower = 4;
        int n = Math.abs(topower);

        int ans = 1;
        while (n > 0) {
            if (n % 2 == 0) {
                x *= x;
                n = n / 2;
            } else {
                ans = ans * x;
                n = n - 1;
            }
        }
        if (topower < 0) {
            ans = 1 / ans;
        }

        System.out.println(ans);
    }

    private static void palindromeNumber() {
        int num = 123321;

        if (num < 0 || num % 10 == 0) {
            System.out.println(num + " is not a palindrome number");
            return;
        }

        int reversed = 0;
        while (num > reversed) {
            reversed = reversed * 10 + (num % 10);
            num /= 10;
        }

        System.out.println("is palindrome : " + ((num == reversed) || (
                num == reversed / 10)));
    }

    private static void reverseNumber() {
        int num = 123456;

        System.out.println("Original number is " + num);

        int min = Integer.MIN_VALUE / 10;
        int minLast = Integer.MIN_VALUE % 10;
        int max = Integer.MAX_VALUE / 10;
        int maxLast = Integer.MAX_VALUE % 10;

        int reversed = 0;
        int lastDigit;
        while (num > 0) {
            lastDigit = num % 10;
            if ((reversed > max || (reversed == max && lastDigit > maxLast)) ||
                    (reversed < min || (reversed == min && lastDigit < minLast))) {
                System.out.println("Exceeded limit");
                return;
            }

            reversed = reversed * 10 + lastDigit;
            num = num / 10;
        }

        System.out.println("Reversed number is " + reversed);
    }

    private static void fizzbuzz() {
        int n = 15;
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 || i % 3 == 0) {
                if (i % 3 == 0) {
                    System.out.print("Fizz");
                }
                if (i % 5 == 0) {
                    System.out.print("Buzz");
                }
                System.out.println("");
            } else {
                System.out.println(i);
            }
        }
    }

    private static void singleNumber() {
        int[] num = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        if (num == null || num.length == 0) {
            System.out.println("num array is zero");
            return;
        }
        int ans = num[0];
        for (int i = 1; i < num.length; i++) {
            ans ^= num[i];
        }
        System.out.println("Single number is " + ans);
    }
}
