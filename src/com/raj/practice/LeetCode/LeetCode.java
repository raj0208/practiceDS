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
        duplicateNumber();
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
}
