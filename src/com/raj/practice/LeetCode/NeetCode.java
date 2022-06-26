package com.raj.practice.LeetCode;

import java.util.*;

public class NeetCode {
    public static void main(String[] args) {
        //containsDpulicate();
        //validAnagram();
        //twoSum();
        //isValidPalindrome();
        //missingNumber();
        groupAnagram();
    }

    /**
     * 49. Group Anagrams (https://leetcode.com/problems/group-anagrams/)
     * time complexity is O(m*n) or O( sum of all chars in strs).
     */
    private static void groupAnagram() {
        //Input: strs = ["eat","tea","tan","ate","nat","bat"]
        //Output: [["bat"],["nat","tan"],["ate","eat","tea"]

        List<String> strs = Arrays.asList("eat","tea","tan","ate","nat","bat");

        Map<String, List<String>> grouped = new HashMap<>();
        for (String s : strs) {
            char[] ca = new char[26];
            for (char c : s.toCharArray()) ca[c - 'a']++;
            String key = String.valueOf(ca);
//            char[] chars = s.toCharArray();
//            Arrays.sort(chars);
//            String key = String.valueOf(chars);
            grouped.putIfAbsent(key, new ArrayList<>());
            grouped.get(key).add(s);
        }


        List<List<String>>  result = new ArrayList<>(grouped.values());
        //result.addAll(grouped.values());

    }

    /**
     * 268. Missing Number (https://leetcode.com/problems/missing-number/)
     */
    private static void missingNumber() {
        // nums = [9,6,4,2,3,5,7,0,1], ans = 8
        // nums = [3,0,1], ans = 2
        int[] nums = {9,6,4,2,3,5,7,0,1};
        int res = nums.length;

        for(int i = 0; i < nums.length; i++) {
            res = res ^ i ^ nums[i]; // a ^ b ^ b = a
        }
        System.out.println(res);
        //return res;
    }

    /**
     * 125. Valid Palindrome (https://leetcode.com/problems/valid-palindrome/)
     */
    private static boolean isValidPalindrome() {
        String s = "";
        int start = 0, end = s.length() - 1;

        while (start < end) {
            while (start < end && !Character.isLetterOrDigit(s.charAt(start))) start++;
            while (start < end && !Character.isLetterOrDigit(s.charAt(end))) end--;
            if (Character.toLowerCase(s.charAt(start++)) != Character.toLowerCase(s.charAt(end--))) return false;
        }

        return true;

        /*
        char[] c = s.toCharArray();
        for (int i = 0, j = c.length - 1; i < j; ) {
            if (!Character.isLetterOrDigit(c[i])) i++;
            else if (!Character.isLetterOrDigit(c[j])) j--;
            else if (Character.toLowerCase(c[i++]) != Character.toLowerCase(c[j--]))
                return false;
        }
        return true;
         */
    }

    /**
     * 1. Two Sum (https://leetcode.com/problems/two-sum/)
     */
    private static void twoSum() {
        //nums = [2,7,11,15], target = 9
        int[] nums = {2,7, 11,15};
        int target = 22;
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();

        int diff = 0;
        for (int i = 0; i < nums.length; i++) {
            diff = target - nums[i];
            if (map.containsKey(diff)) {
                res[0] = map.get(diff);
                res[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }

        for (int i : res) {
            System.out.print(i + ",");
        }
    }

    /**
     * 242. Valid Anagram (https://leetcode.com/problems/valid-anagram/)
     */
    private static void validAnagram() {
        String s = "anagran";
        String t = "nagaram";

        if ((s == null && t != null) ||
            (s != null && t == null) ||
            (s.length() != t.length())) {
            System.out.println("Not Anagram");
            return;
        }
        int[] chars = new int[26];

        for(int i = 0; i < s.length(); i++) {
            chars[(s.charAt(i) - 'a')] += 1;
            chars[(t.charAt(i) - 'a')] -= 1;
        }
        for(int i : chars) {
            if (i != 0) {
                System.out.println("Not Anagram");
                return;
            }
        }
        System.out.println("Valid Anagram");
    }

    /**
     * 217. Contains duplicate (https://leetcode.com/problems/contains-duplicate/)
     */
    private static void containsDpulicate() {
        int[] arr = { 1,2,3,4,3,5};
        Set<Integer> unique = new HashSet<>();
        for(int i : arr) {
            if (!unique.contains(i)) {
                System.out.println("Duplicate");
                return;
            }
            unique.add(i);
        }

        System.out.println("Unique");
    }


}
