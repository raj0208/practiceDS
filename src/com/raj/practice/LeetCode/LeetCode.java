package com.raj.practice.LeetCode;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LeetCode {

    public static void main(String[] args) {
        // fizzduzz();
//        singleNumber();  // LC 136
//        reverseNumber(); // LC 7
//        isNumberPalindrome(); // LC 9, TC: O(Log n) , SC: O(1)
//        factorialTrailingZeroes(); // LC 172, TC:log(n), SC: O(1)


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
        //factorialTrailingZeroes();
        //power();
//        reverseString();
//        longestCommonPrefix();
//        longestNumber();
//        missingNumber();
//        findDuplicate();
//        singleNumber();  // LC 136
//        reverseNumber(); // LC 7
//        isNumberPalindrome(); // LC 9, TC: O(Log n) , SC: O(1)
//        jobChaining();
//        testNullPointer();
//        cosumersupplier();

        maxProfit();
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
    }

    public static void maxProfit() {
        CakeType[] cakes = {
                new CakeType(3, 40),
                new CakeType(5, 70),
        };

        maxDuffelBagValue(cakes, 8);
    }

    private static void maxDuffelBagValue(CakeType[] cakes, int weightCapacity) {
        long[] maxValuesAtCapacities = new long[weightCapacity + 1];

        for (int currentCapacity = 0; currentCapacity <= weightCapacity; currentCapacity++) {
            long currentMaxValue = 0;

            for (CakeType cake : cakes) {
                if (cake.getWeight() == 0 && cake.getValue() != 0) {
                    throw new RuntimeException("Max value is infinity!");
                }

                if (cake.getWeight() <= currentCapacity) {
                    long maxValueUsingCake = cake.getValue() + maxValuesAtCapacities[currentCapacity - cake.getWeight()];

                    currentMaxValue = Math.max(maxValueUsingCake, currentMaxValue);
                }
            }

            maxValuesAtCapacities[currentCapacity] = currentMaxValue;
        }

        System.out.println("Max value for weight capacity " + weightCapacity + " is " + maxValuesAtCapacities[weightCapacity]);

    }

    private static void cosumersupplier() {
        Map<String, Integer> nameMap = new HashMap<>();
        nameMap.putIfAbsent("Raj", 100);
        System.out.println(nameMap.computeIfAbsent("Raj", String::length));
        Integer value = nameMap.computeIfAbsent("John", s -> s.length());
        System.out.println(value);

        // Only input no output, Consumer only accepts
        Consumer<String> print = System.out::println;
        print.accept("Consumr accepted the data");
        BiConsumer<String, Integer> biprint = (s, i) -> System.out.println(s + " data is " + i );
        biprint.accept("String", 10);

        // Supplier only return the data (get)
        Supplier<String> getName = () -> "Supplier returning the data";
        System.out.println(getName.get());

        // Function apply logic to passed data and returns
        Function<Integer, String> format = s -> s + " returned by function";
        System.out.println(format.apply(5));

        //Prediate tests the condition and return boolean
        Predicate<Integer> greaterThan5 = s -> s > 5;
        System.out.println(greaterThan5.test(6));
    }

    public static void testNullPointer() {
        List<Integer> myList = null;

        if (isEven(10) || myList.get(0) == 2 || myList.get(1) == 4) { //1
            myList = List.of(2, 3);
        }

        if (isEven(myList.get(0)) && myList.contains(4) && isEven(myList.get(3))) { //2
            myList = null;
        }

        System.out.println("First number: " + myList.get(0)); //3.
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }



    private static void jobChaining() {
        List<int[]> jobs = Arrays.asList(
                new int[]{1, 25, 3, 1},
                new int[]{2, 25, 23, 1},
                new int[]{23, 25, 0, 1},
                new int[]{4, 25, 3, 1},
                new int[]{3, 25, 23, 1}
        );

        Map<Integer, int[]> child = new HashMap<>();
        Map<Integer, int[]> parent = new HashMap<>();

        for (int[] job : jobs
        ) {
            parent.putIfAbsent(job[0], job);
        }

        
    }

    private static void reverseNumber() {
        int x = Integer.MAX_VALUE/10 + 9;
        int n = x;
        int reversed = 0;
        int lastMax = Integer.MAX_VALUE / 10;
        int lastMaxDigit = Integer.MAX_VALUE % 10;
        int lastMin = Integer.MIN_VALUE / 10;
        int lastMinDigit = Integer.MIN_VALUE % 10;

        while (x != 0) {
            int lastDigit = x % 10;
            if ((reversed > lastMax || (reversed == lastMax && lastDigit > lastMaxDigit)) ||
                    (reversed < lastMin || (reversed == lastMin && lastDigit < lastMinDigit))) {
                System.out.println("Breached limit");
                return;
            }

            reversed = reversed * 10 + lastDigit;
            x = x / 10;
        }
        System.out.println(reversed + " is reverse of " + n);
    }

    private static void singleNumber() {
        int[] num = {4,2,1,2,1};
        if (num == null || num.length == 0) {
            System.out.println("empty error");
            return;
        }

        int res = 0;
        for (int i = 0; i < num.length; i++) {
            res ^= num[i];
        }
        System.out.println(res + " is single number");
    }

    //    We have a list of integers, where:
//
//    The integers are in the range 1..n1..n
//    The list has a length of n+1n+1
//    It follows that our list has at least one integer which appears at least twice. But it may have several duplicates, and each duplicate may appear more than twice.
//
//    Write a function which finds an integer that appears more than once in our list. Don't modify the input! (If there are multiple duplicates, you only need to find one of them.)
//
//    We're going to run this function on our new, super-hip MacBook Pro With Retina Display™. Thing is, the damn thing came with the RAM soldered right to the motherboard, so we can't upgrade our RAM. So we need to optimize for space!
    private static void findDuplicate() {
        int[] num = {1,2,3,4,5,3,3,3,3};

        int low = 1;
        int high = num.length - 1;
        while (low < high) {
            int mid = low + ((high - low)/2);
            int llow = low;
            int lhigh = mid;
            int rlow = mid + 1;
            int rhigh = high;

            int count = 0;
            for(int n : num) {
                if (n >= llow && n <= lhigh) {
                    count++;
                }
            }

            int distinct = lhigh - llow + 1;
            if (count > distinct) {
                low = llow;
                high = lhigh;
            } else {
                low = rlow;
                high = rhigh;
            }
        }

        System.out.println(low);
    }

    private static void missingNumber() {
        int[] arr = {3,6,1,2};
        int missing = 0;
        int arrtotal = 0;

        for(int i = 0; i < arr.length; i++) {
            missing ^= (i + 1);
            arrtotal ^= arr[i];
        }

        missing ^= arr.length + 1;
        System.out.println(missing ^ arrtotal);
    }

    private static void largestNumber() {
        int[] arr = {3, 30, 34, 5, 9};
        String[] s = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            s[i] = String.valueOf(arr[i]);
        }
        Arrays.sort(s, (x,y) -> (y+x).compareTo(x+y));
        for (String s1 : s) {
            System.out.println(s1);
        }
    }


    private static void longestNumber() {
        int[] arr = { 3,30,34,5,9};
        String[] s = new String[arr.length];

        for (int i = 0; i < arr.length; i++) {
            s[i] = String.valueOf(arr[i]);
        }
        Arrays.sort(s, (x,y) -> (x+y).compareTo(y+x));

        StringBuilder sb = new StringBuilder();
        for (String s1 : s) {
            sb.append(s1);
        }

        System.out.println(sb.toString());
    }


    // 14. Longest Common Prefix
    private static void longestCommonPrefix() {
        List<String> strs = Arrays.asList( "leetcode", "leet", "lead"  );
        String prefix = strs.get(0);

        for (int i = 1; i < strs.size(); i++) {
            while (!strs.get(i).startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    System.out.println("No prefix");
                    return;
                }
            }
        }

        System.out.println("Prefix " + prefix);
    }

    // 344. ReverseString
    private static void reverseString() {
        char[] s = "Rajesh".toCharArray();
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char c = s[left];
            s[left] = s[right];
            s[right] = c;
            left++;
            right--;
        }

        System.out.println();
    }

    // 50 Pow(x, n)
    private static void power() {
        double x = 2.0;
        int n = -2147483648;

        double ans = 1.0;
        long num = n;
        if (n < 0) {
            num = Math.abs(num);
        }

        while ( num > 0) {
            if (num % 2 == 0) {
                x = x * x;
                num = num / 2;
            } else {
                ans = ans * x;
                num  = num - 1;
            }
        }

        if ( n < 0) {
            System.out.println((double)1.0 / (double)ans);
        } else {
            System.out.println(ans);
        }
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

//  9.  Given an integer x, return true if x is palindrome integer.
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




        Stack<String> stack = new Stack<>();
        stack.push("asdfa");
        stack.push("asdf");
        stack.pop();

        
    }

    public static void duplicateLinked() {
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
