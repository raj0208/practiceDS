package com.raj.practice.interviewcake;

import java.util.HashSet;
import java.util.Set;

public class InterviewCake {
    public static void main(String[] args) {
//        findOneRepeating(new int[] { 1,5,3,6,2,4,5 });
//        findTwoRepeatingNumbers(new int[] { 1,2,4,3,4,5,1 });
//        uniqueTwoNumbers(new int[] { 1,2,4,3,4,5,1 });

        maxBagValue(new CakeType[] {
                new CakeType(7,160),
                new CakeType(3,90),
                new CakeType(2,15)
        }, 20);

    }

//    Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
//    Example 1:
//    Input: nums = [1,2,3,1]
//    Output: true
    private static void containsDuplicate() {
        int[] numbers = {1,2,3,1};

        if (numbers == null || numbers.length < 1) {
            System.out.println("Empty array");
            return;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            if (set.contains(numbers[i])) {
                System.out.println("Duplicate number");
                return;
            }
            set.add(numbers[i]);
        }

        System.out.println("No duplicates");
    }



//    # Find a duplicate, Space Edition BEAST MODE
//
//    # We have a list of integers, where:
//
//    # + The integers are in the range 1..n
//    # + The list has a length of n+1
//
//    # It follows that our list has at least one integer which appears at
//    # least twice. But it may have several duplicates, and each duplicate
//    # may appear more than twice.
//
//    # Write a function which finds an integer that appears more than once in
//    # our list. (If there are multiple duplicates, you only need to find one
//    # of them.)
//
//    # We're going to run this function on our new, super-hip Macbook Pro
//    # With Retina Display. Thing is, the damn thing came with the RAM
//    # soldered right to the motherboard, so we can't upgrade our RAM. So we
//    # need to optimize for space!
//
//    # Gotchas
//
//    # We can do this in O(1) space.
//    # We can do this in less than O(n^2) time, while keeping O(1) space.
//    # We can do this in O(n log n) time and O(1) space.
//    # We can do this without destroying the input.
    public static void findOneRepeating(int[] numbers) {
        int floor = 1;
        int ceiling = numbers.length - 1;

        while (floor < ceiling) {
            int midpoint = floor + (ceiling - floor)/2;
            int left_min = floor;
            int left_max = midpoint;
            int right_min = midpoint + 1;
            int right_max = ceiling;

            int items_in_lower_range = 0;

            for (int item : numbers) {
                if (item >=left_min && item <=left_max) {
                    items_in_lower_range++;
                }
            }

            int distinct_items = left_max - left_min + 1;

            if (items_in_lower_range > distinct_items) {
                floor = left_min;
                ceiling = left_max;
            }
            else {
                floor = right_min;
                ceiling = right_max;
            }
        }

        System.out.println(floor + " is the dupicate number");
    }

    public static void findTwoRepeatingNumbers(int[] arr) {
        int xor = arr[0];
        int set_bit_no;

        int i = 0;
        int size = arr.length;
        int n = size - 2;
        int x = 0, y = 0;

        /* Get the xor of all elements in arr[] and {1, 2 .. n} */
        for (i = 1; i < size; i++)
            xor ^= arr[i];

        for (i = 1; i <= n; i++)
            xor ^= i;

        /* Get the rightmost set bit in set_bit_no */
        set_bit_no = (xor & ~(xor - 1));

        /* Now divide elements in two sets by comparing rightmost set
           bit of xor with bit at same position in each element. */
        for (i = 0; i < size; i++) {
            int a = arr[i] & set_bit_no;
            if (a != 0)
                x = x ^ arr[i]; /*XOR of first set in arr[] */
            else
                y = y ^ arr[i]; /*XOR of second set in arr[] */
        }

        for (i = 1; i <= n; i++) {
            int a = i & set_bit_no;
            if (a != 0)
                x = x ^ i; /*XOR of first set in arr[] and {1, 2, ...n }*/
            else
                y = y ^ i; /*XOR of second set in arr[] and {1, 2, ...n } */
        }

        System.out.println(x + " and " + y + " are duplicates");
    }

    public static void uniqueTwoNumbers(int[] arr)
    {
        int sum = 0;
        for (int item : arr) {
            // Xor  all the elements of the array
            // all the elements occurring twice will
            // cancel out each other remaining
            // two unique numbers will be xored
            sum = (sum ^ item);
        }

        // Bitwise & the sum with it's 2's Complement
        // Bitwise & will give us the sum containing
        // only the rightmost set bit
        sum = (sum & -sum);

        // x and y will contains 2 unique elements elements initialized with 0 box number xored with 0 is number itself
        int x = 0;
        int y = 0;

        // traversing the array again
        for (int value : arr) {
            // Bitwise & the arr[i] with the sum Two possibilities either result == 0 or result > 0
            if ((value & sum) > 0) { // if result > 0 then arr[i] xored with the x
                x = (x ^ value);
            } else {  // if result == 0 then arr[i] xored with y
                y = (y ^ value);
            }
        }

        // print the the two unique numbers
        System.out.println("The 2 unique elements are " + x + " and " + y);
    }

    public static class CakeType {
        private int weight;
        private int value;

        public CakeType(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "CakeType{" +
                    "weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }

    public static void maxBagValue(CakeType[] cakeTypes, int weightCapacity) {
        long[] maxValuesAtCapacities = new long[weightCapacity + 1];

        for (int curreentCapacity = 0; curreentCapacity < weightCapacity; curreentCapacity++) {
            long currentMaxValue = 0;

            for (CakeType cakeType: cakeTypes) {

                if (cakeType.getWeight() == 0 && cakeType.getValue() != 0) {
                    throw new IllegalArgumentException();
                }

                if (cakeType.getWeight() <= curreentCapacity) {
                    long maxValueUsingCake = cakeType.getValue() + maxValuesAtCapacities[curreentCapacity - cakeType.getWeight()];

                    currentMaxValue = Math.max(currentMaxValue, maxValueUsingCake);
                }
            }

            maxValuesAtCapacities[curreentCapacity] = currentMaxValue;
        }

        System.out.println("Max " + maxValuesAtCapacities[weightCapacity]);
    }


}
