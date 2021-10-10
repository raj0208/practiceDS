package com.raj.practice.interviewcake;

public class InterviewCake {
    public static void main(String[] args) {
//        findOneRepeating(new int[] { 1,5,3,6,2,4,5 });
        findTwoRepeatingNumbers(new int[] { 1,2,4,3,4,5,1 });
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
}
