package com.raj.practice.Udemy;

import com.raj.practice.LeetCode.LeetCode;
import com.raj.practice.LeetCode.RandomPointerNode;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Questions {
    public static void main(String[] args) {
//        integer2Roman();
//        roman2Integer();
//        mypower();
//       reverseInteger();
//        trailingZeros();
//        isPalindromeInteger();
//        findSingleNumber();
//        fizzbuzz();
//        duplicateRandomPtrLink();
//        twoSum();
//        longestCommonPrefix();
//        findRepeatedDnaSequences();
//        validAnagram();
//        longestPalindrame();
//        lengthOfLongestSubstring();
//        reverseString();
//        integerToWords();
        //dutchflagproblem();
//        myCalendarBooking();
//        getTopKURLS();
//        stringRotation();
//        maxProductSum();
//        mergeSortedArray();
//        productExceptSelf();
//        maxSubArraySum();

        maxArea();
    }

    // LC:
    private static void maxArea() {
        int[] height = {1,8,6,2,5,4,8,3,7}; //48

        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println("Max Area is " + max);




    }

    // LC: 53
    private static void maxSubArraySum() {
        int[] nums = {5,4,-1,7,8}; //23
        //int[] nums = {-2,1,-3,4,-1,2,1,-5,4};   // 6
        int curr = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], nums[i] + curr);
            max = Math.max(curr, max);
        }
        System.out.println(max);
    }

    // LC: 238
    private static void productExceptSelf() {
        int[] nums = {1,2,3,4};
        int size = nums.length;
        int left = 1;
        int right = 1;
        int[] res = new int[size];
        Arrays.fill(res, 1);

        for (int i = 0; i < size; i++) {
            res[i] = res[i] * left;
            left = left * nums[i];
            res[size - 1 - i] = res[size - 1 - i] * right;
            right = right * nums[size - 1 - i];
        }

        System.out.println(Arrays.toString(res));
    }

    // LC: 88
    private static void mergeSortedArray() {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;


        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }

        System.out.println(Arrays.toString(nums1));
    }

    private static void maxProductSum() {
        int[] nums = {7,1,5,3,6,4};

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int price : nums) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        System.out.println(maxProfit);
    }

    private static void stringRotation() {
        String s1 = "Hello";
        String s2 = "elloH";

        System.out.println(s1.concat(s1).contains(s2));
    }

    private static void myCalendarBooking() {
        int[][] booking = {
                {10,20},
                {21,25},
                {28,30},
                {26,29},
                {20, 30}
        };
        calendarUsingTreeMap(booking);
        calendarUsingList(booking);
    }

    private static void calendarUsingList(int[][] slots) {
        List<int[]> bookings = new ArrayList<>();

        for(int[] slot : slots) {
            boolean flag = true;
            for(int[] booked : bookings) {
                if (Math.max(booked[0], slot[0]) < Math.min(booked[1], slot[1])) {
                    System.out.println("Overlap of " + Arrays.toString(slot) + " with " + Arrays.toString(booked));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                bookings.add(slot);
                System.out.println("Added " + Arrays.toString(slot));
            }
        }
    }

    private static void calendarUsingTreeMap(int[][] booking) {
        TreeMap<Integer, Integer> calendar = new TreeMap<>();

        for (int[] book: booking) {
            // get the slot whose start time is equal or nearest (just less)
            Integer startKey = calendar.floorKey(book[0]);
            // if the slot end time is greater than startTime, there is overlap
            if (startKey != null && calendar.get(startKey) > book[0]) {
                System.out.println("Overlap of " + Arrays.toString(book) + " with " + startKey + "," + calendar.get(startKey));
                continue;
            }

            // get the slot whose start time is equal or nearest (just more)
            Integer endKey = calendar.ceilingKey(book[0]);
            // if the slot
            if (endKey != null && endKey < book[1]) {
                System.out.println("Overlap of " + Arrays.toString(book) + " with " + endKey + "," + calendar.get(endKey));
                continue;
            }

            calendar.put(book[0], book[1]);
            System.out.println("Added booking " + book[0] + "," + book[1]);
        }
    }


    private static void dutchflagproblem() {
        int[] array = {
            1,0,2,0,1,2,0,2,1,0,0,2,0,0,1,1,0,2,2,1,1,1,0,0,0,0
        };
        System.out.println(Arrays.toString(array));
        int low = 0, curr = 0, high = array.length - 1;
        int temp = 0;

        while(curr <= high) {
            switch(array[curr]) {
                case 0:
                    temp = array[low];
                    array[low] = array[curr];
                    array[curr] = temp;
                    low++;
                    break;
                case 1:
                    curr++;
                    break;
                case 2:
                default:
                    temp = array[high];
                    array[high] = array[curr];
                    array[curr] = temp;
                    high--;
                    break;
            }
        }
        System.out.println(Arrays.toString(array));

    }

    private static final String[] belowTwenty = { "", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private static final String[] tens = { "", "", "Twenty", "Thirty", "Forty",
            "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

    private static String words(int num) {
        StringBuilder stringBuilder = new StringBuilder();

        if (num < 20) {
            stringBuilder.append(belowTwenty[num]);
        } else if (num < 100) {
            stringBuilder.append(tens[num / 10]).append(" ").append(belowTwenty[num % 10]);
        } else if (num < 1000) {
            stringBuilder.append(words(num / 100)).append(" Hundred ").append(words(num % 100));
        } else if (num < 1000000) {
            stringBuilder.append(words(num / 1000)).append(" Thousand ").append(words(num % 1000));
        } else if (num < 1000000000) {
            stringBuilder.append(words(num / 1000000)).append(" Million ").append(words(num % 1000000));
        } else {
            stringBuilder.append(words(num / 1000000000)).append(" Billion ").append(words(num % 1000000000));
        }
        return stringBuilder.toString().trim();
    }


    static String[] LESS_THAN_20 = {
            "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    static String[] TENS = {
            "", "Ten", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty " , "Ninety "
    };

    static String[] HUNDRED = { " Hundred "};

    static String[] THOUSANDS = { "", "Thousand ", "Million ", "Billion " };

    public static void integerToWords() {
        int[] nums = {Integer.MAX_VALUE, 0, 1000000, 12345,1230567,50868, 110123};

        for (int num: nums.clone()) {
            System.out.println(num == 0 ? "Zero" : words(num));
        }

        for (int num: nums.clone()) {
            int i = 0;
            String words = "";
            if (num == 0) {
                System.out.println(LESS_THAN_20[num]);
                continue;
            }

            while (num != 0) {
                String s = converter(num % 1000);
                if (!s.isEmpty())
                    words = s + THOUSANDS[i] + words;
                num /= 1000;
                i++;
            }

            System.out.println(words.trim());
        }
    }

    private static String converter(int n) {
        if (n == 0) {
            return "";
        } else if (n < 20) {
            return LESS_THAN_20[n] + " ";
        }  else if (n < 100) {
            return TENS[n / 10] + converter(n % 10);
        } else {
            return LESS_THAN_20[n / 100] + HUNDRED[0] + converter(n % 100);
        }
    }

    private static void reverseString() {
        //String s = "   the sky    is     blue   ";
        String s = "     the      ";
        char[] ch = s.toCharArray();
        int n = ch.length;
        char[] result = new char[n];
        int result_index = 0;
        int end = n - 1;

        while(end >= 0) {
            // get index of valid character from right to left
            while (end >= 0 && ch[end] == ' ') end--;
            int start = end;
            // get index of valid character from right to left
            while (start >= 0 && ch[start] != ' ') start--;
            // add space if word already include in the result
            if (result_index > 0) result[result_index++] = ' ';

            for (int i = start + 1; i <= end; i++) {
                result[result_index++] = ch[i];
            }
            end = start - 1;
        }

        if (result[result_index - 1] == ' ') result_index--;  // truncate last space

        System.out.println("'" + new String(result, 0, result_index) + "'");
    }

    // TC : O(n), SC: O(n)
    private static void lengthOfLongestSubstring() {
        String s = "abba";
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            if (map.containsKey(ch)) {
                left = Math.max(left, map.get(ch) + 1);
            }
            map.put(ch, right);
            ans = Math.max(ans, right - left + 1);
        }

        System.out.println("Length of longest substring :" + ans);
    }

    static int start = 0, end = 0;
    private static void longestPalindrame() {
        String s = "bccbd";
        for (int i = 0; i < s.length(); i++) {
            expandAroundCenter(s, i, i);
            expandAroundCenter(s, i, i + 1);
        }

        System.out.println(s.substring(start, end + 1));
    }

    private static void expandAroundCenter(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        left = left + 1;
        right = right - 1;
        if (end - start + 1 < right - left + 1) {
            start = left;
            end = right;
        }
    }

    private static void validAnagram() {
        String s = "car";
        String t = "race";

        if ((s == null && t != null) ||
                (s != null && t == null) ||
                (s.length() != t.length())) {
            System.out.println("Not valid anagram");
            return;
        }

        int[] result = new int[26];

        for(int i =0; i < s.length(); i++) {
            result[s.charAt(i) -'a'] += 1;
            result[t.charAt(i) -'a'] -= 1;
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) {
                System.out.println("Not Valid Anagram");
                return;
            }
        }

        System.out.println("Valid anagram");
    }

    private static void findRepeatedDnaSequences() {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        Set<String> result = new HashSet<>();
        Set<String> set = new HashSet<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String seq = s.substring(i, i + 10);
            if (set.contains(seq)) {
                result.add(seq);
            } else {
                set.add(seq);
            }
        }

        result.forEach(System.out::println);
    }

    // TC: O(n*m) where n = length of array, m = longest string in array, SC : O(1)
    private static void longestCommonPrefix() {
        String[] strs = { "flower","flow","flight" };
        // Exception Scenario
        if (strs.length == 0) {
            System.out.println("Empty array");
            return;
        }

        // Logic
        String prefix = strs[0];

        for (int i = 1; i < strs.length ; i++) {
            while(strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    System.out.println("No prefix");
                    return;
                }
            }
        }

        // Result
        System.out.println("Longest Comment Prefix is " + prefix);
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
            if (i % 15 == 0) {
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

    public static void getTopKURLS() {
        String[] logs = {
                "/api/user", "/api/order", "/api/user", "/api/product",
                "/api/user", "/api/order", "/api/cart", "/api/user",
                "/api/product", "/api/cart", "/api/user", "/api/order"
        };

        TopKURL tracker = new TopKURL();

        for (String url: logs) {
            tracker.addAPICall(url);
        }

        List<Map.Entry<String, Integer>> topUrls = tracker.getTopKUrls(2);
        topUrls.sort((c1, c2) -> c2.getValue() - c1.getValue());
        topUrls.forEach(x -> System.out.println(x.getKey() + "->" + x.getValue()));
    }

    static class TopKURL {
        Map<String, Integer> urlTracker = new HashMap<>();

        public void addAPICall(String endPoint) {
            urlTracker.put(endPoint, urlTracker.getOrDefault(endPoint, 0) + 1);
        }

        public List<Map.Entry<String, Integer>> getTopKUrls(int topK) {
            PriorityQueue<Map.Entry<String, Integer>> queue =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
            //new PriorityQueue<>(Map.Entry.comparingByValue());

            for (Map.Entry<String, Integer> entry : urlTracker.entrySet()) {
                queue.offer(entry);
                if (queue.size() > topK) {
                    queue.poll();
                }
            }

            return new ArrayList<>(queue);
        }
    }
}
