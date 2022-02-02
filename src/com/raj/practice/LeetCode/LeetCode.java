package com.raj.practice.LeetCode;

import jdk.nashorn.api.tree.CompilationUnitTree;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCode {

    public static void main(String[] args) {
//        longestPalindromeSubString();
//        lengthOfLongestSubstring();
//        decodeString();
//        wordBreak();
//        sortColors();
//        canJump();
//        moveZeroes();
//        asteroidCollision();
//        maxProfitBySelling();
//        disappearedNumbers();
//        duplicateNumber();
//        majorityElement();
//        maxProduct();
//        kadanes_maxsubarray();
//        duplicateLinkedList();
//        dupList();
//        mergeInterval();
//        countBinarySubstrings();
//        swapIntegers();Î
//        findMax();

//        String[] meow = {"aaa"};
//        String s = "aaa";
//        System.out.println(meow[0].equals(s));
//        isNumberPalindrome();
        factorialTrailingZeroes();
    }

//  172.  Given an integer n, return the number of trailing zeroes in n!.
    private static void factorialTrailingZeroes() {
        int num = 25; // 10! = 3628800
        int count = 0;

        while (num > 0) {
            num /= 5;
            count += num;
        }

        System.out.println("Zero count " + count);
    }

//    Given an integer x, return true if x is palindrome integer.
//    An integer is a palindrome when it reads the same backward as forward.
//    For example, 121 is a palindrome while 123 is not.
    private static void isNumberPalindrome() {
        int num = 123;

        if (num < 0 || (num % 10 == 0 && num != 0)) {
            System.out.println("Not a palindrome");
            return;
        }

        int reversedNum = 0;
        while(num > reversedNum) {
            reversedNum = (reversedNum * 10) + (num % 10);
            num /= 10;
        }

        System.out.println("Is Palindrome " + (num == reversedNum || num == reversedNum / 10));
    }

    private static void findMax() {
        int a = 2, b = 5, c = 9;
        int max = a;

        boolean flag = max < b && (max = b) > 0;
        flag = max < c && (max = c) > 0;
        System.out.println(max);
    }

    private static void swapIntegers() {
        int x = 5;
        int y = 9;
        System.out.println("x=" + x + ",y=" + y);
        x = x ^ y ^ ( y = x);
        System.out.println("x=" + x + ",y=" + y);
        x = x + y - (y = x);
        System.out.println("x=" + x + ",y=" + y);
        x = x * y / (y = x);
        System.out.println("x=" + x + ",y=" + y);
    }

    /**
     * Method to return 1 if it ends with second string else 0
     */
    public static int endWithString(String line) {
        int result = 0;
        // Error handling - return 0 if line is null or it cannot be split into 2
        if (line == null || line.split(",").length < 2)
            return result;

        String[] arrLine = line.split(",");
        String str = arrLine[0].trim();
        String endWith = arrLine[1].trim();

        // Error handling - return 0 if source or endwith is null or source length is leess than endswith length
        if (str == null || endWith == null || str.length() < endWith.length()) {
            return result;
        }

        return str.endsWith(endWith) ? 1 : 0;
    }

    private static void countBinarySubstrings() {
        String s = "00110011";
        int ans = 0, prev = 0, cur = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) != s.charAt(i)) {
                ans += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } else {
                cur++;
            }
        }

        System.out.println(ans + Math.min(prev, cur));;
    }

    private static void mergeInterval() {
        int[][] intervals = new int[][] {
                new int[] { 1,3 },
                new int[] { 2,6 },
                new int[] { 8,10 },
                new int[] { 11,15 },
        };
        List<int[]> merged = new ArrayList<>();
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        int[] curr = intervals[0];
        for (int i = 1; i < intervals.length; i++) {

            if (curr[1] >= intervals[i][0]) {
                if (curr[1] < intervals[i][1])
                curr[1] = intervals[i][1];
            } else {
                merged.add(curr);
                curr = intervals[i];
            }
        }
        merged.add(curr);
        int[][] m = merged.toArray(new int[0][0]);
        for (int i = 0; i < merged.size(); i++) {
            System.out.println(merged.get(i)[0] + ", " + merged.get(i)[1]);
        }

    }

    public static void longestPalindromeSubString() {
        String s = "abcbd";

        if (s == null || s.length() == 0) {
            System.out.println("Empty string");
            return;
        }

        int start = 0, end  = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);

            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        System.out.println(s.substring(start, end + 1));
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public static void lengthOfLongestSubstring() {
        String s = "pwwkew";

        int len = s.length();
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0, j = 0; j < len; j++) {
            if (map.containsKey(s.charAt(j)))
                i = Math.max(map.get(s.charAt(j)), i);
            ans = Math.max(ans, j - i + 1);

            map.put(s.charAt(j), j + 1);
        }
        System.out.println("Longest length is " + ans);
    }

    public static void decodeString() {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        String temp = "";
        String s = "3[a2[c]]"; //"3[a]2[bv]";

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                numStack.push(Integer.valueOf("" + s.charAt(i)));
            } else if (s.charAt(i) == '[') {
                strStack.push(sb.toString());
                sb.setLength(0);
            } else if (s.charAt(i) == ']') {
                temp = strStack.pop();
                temp += String.join("", Collections.nCopies(numStack.pop(), sb.toString()));
                sb.setLength(0);
                sb.append(temp);
                temp = "";
            } else if (Character.isAlphabetic(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }

        System.out.println(sb.toString());
    }

    public static void wordBreak() {
        String s = "leetcode";
        HashSet<String> dict = new HashSet<>();
        dict.add("leet");
        dict.add("code");

        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i ; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        System.out.println(dp[s.length()]);
    }

    public static void sortColors() {
        int[] arr = new int[] {2,0,2,1,1,0};

        int low = 0, high = arr.length - 1, curr = 0;

        while(curr <= high) {
            switch(arr[curr]) {
                case 0:
                    arr[curr] ^= arr[low];
                    arr[low] ^= arr[curr];
                    arr[curr] ^= arr[low];;
                    curr++;
                    low++;
                    break;
                case 1:
                    curr++;
                    break;
                case 2:
                    arr[curr] ^= arr[high];
                    arr[high] ^= arr[curr];
                    arr[curr] ^= arr[high];;
                    high--;
                    break;
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void canJump() {
        int[] num = { 2,3,1,1,4 };

        int len = num.length;
        int reachable = 0;
        boolean reached = false;
        for (int i = 0; i < len && i <= reachable; i++) {
            reachable = Math.max(reachable, i + num[i]);
            if (reached = (reachable >= len - 1))
                break;
        }

        System.out.println("Reached : " + reached);
    }

    public static void moveZeroes() {
        int[] num = {0,1,0,3,12};

        int curr = 0;
        for ( int i = 0; i < num.length; i++) {
            if (num[i] != 0) {
                num[curr++] = num[i];
            }
        }
        for (int i = curr; i < num.length; i++) {
            num[i] = 0;
        }

        System.out.println(Arrays.toString(num));
    }

    public static void asteroidCollision() {
        int[] asteroids = { -2, 5, 10, -15 };
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] > 0) {
                stack.push(asteroids[i]);
            } else {
                while(!stack.empty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroids[i])) {
                    stack.pop();
                }

                if (stack.empty() || stack.peek() < 0) {
                    stack.push(asteroids[i]);
                } else if (Math.abs(asteroids[i]) == Math.abs(stack.peek())) {
                    stack.pop();
                }
            }
        }

        System.out.println(Arrays.toString(stack.toArray()));
    }

    public static void maxProfitBySelling() {
        int[] prices = { 6,5,4,3,2,1}; //{7,1,5,3,6,4 };

        if (prices.length == 0) return;

        int maxProfit = 0;
        int buyPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - buyPrice);
            if (prices[i] < buyPrice)
                buyPrice = prices[i];
        }

        System.out.println("Max Profit " + maxProfit);

    }

    public static void maxProfitByMultipleTxns() {
        int[] prices = {2,1,5,3,6,4};
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        System.out.println("Max Profit : " + maxProfit);
    }

    public static void disappearedNumbers() {
        int[] nums = { 1,2,2,4,1 };
        for (int i = 0; i < nums.length; i++) {
            int n = Math.abs(nums[i]);
            if (nums[n - 1] > 0)
                nums[n-1] *= -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                System.out.println(i + 1);
        }
    }

    public static void duplicateNumber() {
        int[] nums =  {1,2,2,4,1}; //{ 4,3,2,7,8,2,3,1};

        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int n = Math.abs(nums[i]);
            if (nums[n - 1] > 0)
                nums[n-1] *= -1;
            else
                l.add(nums[i]);
        }
        l.forEach(System.out::println);
    }

    public static void majorityElement() {
        int[] num = {2,2,1,1,2,1,2};

        int count = 0;
        int majority = 0;
        for (int i = 0; i < num.length; i++) {
            if (count == 0) {
                majority = num[i];
            }
            if (num[i] == majority) count++;
            else count--;
        }

        System.out.println(majority);
    }

    public static void maxProduct() {
        int[] num = { -1,-2,-3,0,3,5 };
        int res = num[0];
        int min = 1, max = 1;
        for (int i = 0; i < num.length; i++) {
            int temp = max * num[i];
            max = Math.max(Math.max(temp, min * num[i]), num[i]);
            min = Math.min(Math.min(temp, min * num[i]), num[i]);
            res = Math.max(res, max);
        }

        System.out.println("Max : " + res);
    }

    public static void kadanes_maxsubarray() {
        int[] num = {-2,1,-3,4,-1,2,1,-5,4};
//        int[] num = {5,4,-1, 7, 8};
        int curr = num[0];
        int max = curr;

        for (int i = 1; i < num.length; i++) {
            curr = Math.max(num[i], num[i] + curr);
            max = Math.max(max, curr);
        }
        System.out.println(max);
        
    }

    public static void duplicateLinkedList() {
        RandomPointerNode head = getLinkedList();

        RandomPointerNode curr = head;
        while (curr != null) {
            RandomPointerNode node = new RandomPointerNode(curr.getData());
            node.setNext(curr.getNext());
            curr.setNext(node);
            curr = node.getNext();
        }

        // set random pointer
        curr = head;
        while (curr != null) {
            curr.getNext().setRandom(curr.getRandom().getNext());
            curr = curr.getNext().getNext();
        }

        RandomPointerNode newHead = head.getNext();
        curr = head;

        while(curr != null) {
            RandomPointerNode temp = curr.getNext();
            curr.setNext(temp != null ? temp.getNext() : null);
            curr = temp;
        }

        while(newHead != null) {
            System.out.println(newHead.toString());
            newHead = newHead.getNext();
        }

        System.out.println("duplicate");
    }

    private static void dupList() {
        RandomPointerNode head = getLinkedList();
        Map<Integer, RandomPointerNode> map = new HashMap<>();

        RandomPointerNode newHead = new RandomPointerNode(0);
        RandomPointerNode curr = newHead;
        while(head != null) {
            RandomPointerNode temp = map.getOrDefault(head.getData(), null);
            if (temp == null) {
                temp = new RandomPointerNode(head.getData());
                map.put(temp.getData(), temp);
            }
            curr.setNext(temp);
            curr = temp;

            if (!map.containsKey(head.getRandom().getData())) {
                map.put(head.getRandom().getData(), new RandomPointerNode(head.getRandom().getData()));
            }
            curr.setRandom(map.get(head.getRandom().getData()));

            head = head.getNext();
        }

        curr = newHead;

        while((curr = curr.getNext()) != null) {
            System.out.println(curr.toString());
        }
    }

    private static RandomPointerNode getLinkedList() {
        RandomPointerNode one = new RandomPointerNode(1);
        RandomPointerNode two = new RandomPointerNode(2);
        RandomPointerNode three = new RandomPointerNode(3);
        RandomPointerNode four = new RandomPointerNode(4);

        one.setNext(two);
        one.setRandom(three);

        two.setNext(three);
        two.setRandom(two);

        three.setNext(four);
        three.setRandom(one);

        four.setRandom(two);

        return one;
    }

    private static void rectangleOverlap() {
        
    }
}
