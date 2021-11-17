package com.raj.practice.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode {

    public static void main(String[] args) {
//        longestPalindromeSubString();
        lengthOfLongestSubstring();
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
    }
