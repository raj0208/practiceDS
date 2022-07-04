package com.raj.practice.LeetCode;

import java.util.*;

public class NeetCode {
    public static void main(String[] args) {
        /****************/
        //longestValidParanthesis(); // TC:  O(n), SC: O)n)

        //containsDpulicate();
        //validAnagram();
        //twoSum();
        //isValidPalindrome();
        //missingNumber();
        //groupAnagram();
        //validParenthesis();
        //hasLinkedListCycle();

        //topKElements();
        validSudoku();
    }

    private static char[][] getSudoku() {
        char[][] array = {
            {'5','3','.','.','7','.','.','.','.'}
            ,{'6','.','.','1','9','5','.','.','.'}
            ,{'.','9','8','.','.','.','.','6','.'}
            ,{'8','.','.','.','6','.','.','.','3'}
            ,{'4','.','.','8','.','3','.','.','1'}
            ,{'7','.','.','.','2','.','.','.','6'}
            ,{'.','6','.','.','.','.','2','8','.'}
            ,{'.','.','.','4','1','9','.','.','5'}
            ,{'.','.','.','.','8','.','.','7','3'}
        };

        return array;
    }

    /**
     * 36. Valid Sudoku (https://leetcode.com/problems/valid-sudoku/)
     */
    private static void validSudoku() {
        char[][] board = getSudoku();
        Set<String> seen = new HashSet<>();

        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                char num = board[row][col];
                if (num != '.')
                    if (!seen.add(num + " R" + row) ||
                            !seen.add(num + " C" + col) ||
                            !seen.add(num + " B" + row/3 + "-" + col/3)) {
                        System.out.println("Not Valid");
                        return;
                    }
            }
        }

        System.out.println("Valid");

    }

    /**
     * 347. Top K Frequent Elements (https://leetcode.com/problems/top-k-frequent-elements/)
     */
    private static void topKElements() {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        Map<Integer,Integer> map = new HashMap<>();
        List<Integer>[] bucket = new List[nums.length + 1];

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i : map.keySet()) {
            int frequency = map.get(i);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(i);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >=0; i--) {
            if (bucket[i] != null) {
                boolean  flag = false;
                for(int j : bucket[i]) {
                    res.add(j);
                    if (flag = (res.size() == k)) {
                        break;
                    }
                }
                if (flag) break;
            }
        }

        System.out.println(res.toString());
    }

    /**
     * 141. Linked List Cycle (https://leetcode.com/problems/linked-list-cycle/)
     */
    private static void hasLinkedListCycle() {
        // https://leetcode.com/problems/linked-list-cycle/submissions/
        /*
        public boolean hasCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;

                if (slow == fast) {
                    return true;
                }
            }

            return false;
        } */
    }

    /**
     * 20. Valid Parentheses (https://leetcode.com/problems/valid-parentheses/)
     */
    private static void validParenthesis() {
        String s = null;
        s = "()[]{}";
//        s = "((";
//        s = "(}";

        Map<Character, Character> map = new HashMap <>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                if (stack.size() == 0 || map.get(c) != stack.peek()) {
                    System.out.println("Invalid");
                    break;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        System.out.println("Valid " + (stack.size() == 0));
    }

    /**
     * 32. Longest Valid Parentheses (https://leetcode.com/problems/longest-valid-parentheses/)
     */
    private static void longestValidParanthesis() {
        // s = ")()())", 4
        // s = "(()", 2
        // s = "", 0
        String s = ")()())";

        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.size() == 0) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        System.out.println(ans);
    }

    /**
     * 49. Group Anagrams (https://leetcode.com/problems/group-anagrams/)
     * time complexity is O(m*n) or O( sum of all chars in strs).
     * O(n klogk)
     */
    private static void groupAnagram() {
        //Input: strs = ["eat","tea","tan","ate","nat","bat"]
        //Output: [["bat"],["nat","tan"],["ate","eat","tea"]

        List<String> strs = Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat");

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


        List<List<String>> result = new ArrayList<>(grouped.values());
        //result.addAll(grouped.values());

    }

    /**
     * 268. Missing Number (https://leetcode.com/problems/missing-number/)
     */
    private static void missingNumber() {
        // nums = [9,6,4,2,3,5,7,0,1], ans = 8
        // nums = [3,0,1], ans = 2
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int res = nums.length;

        for (int i = 0; i < nums.length; i++) {
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
        int[] nums = {2, 7, 11, 15};
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

        for (int i = 0; i < s.length(); i++) {
            chars[(s.charAt(i) - 'a')] += 1;
            chars[(t.charAt(i) - 'a')] -= 1;
        }
        for (int i : chars) {
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
        int[] arr = {1, 2, 3, 4, 3, 5};
        Set<Integer> unique = new HashSet<>();
        for (int i : arr) {
            if (!unique.contains(i)) {
                System.out.println("Duplicate");
                return;
            }
            unique.add(i);
        }

        System.out.println("Unique");
    }


}
