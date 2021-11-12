package com.raj.practice.Misc;

public class CropText {
    public static void main(String[] args) {
        String text = "Codility We test coders";
        int size= 14;
        text = "The quick brown fox jumps over the lazy dog";
        size = 39;
//        String text = "very-long-input-will-be-truncated";
        System.out.println(cropText(text, 39));
//        text = "a-b-c-d-e-f-g-veryveryverylongword";
//        System.out.println(cropText(text, 20));
    }

    public String solution(String message, int K) {
        // write your code in Java SE 8
        if (K >= message.length()) {
            return message;
        }

        String[] words = message.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {
            String tmp = result +  " " + words[i];
            if (tmp.trim().length() <= K) {
                result = tmp.trim();
            } else {
                break;
            }
        }

        return result;
    }


    private static String cropText(String message, int K) {
        String[] words = message.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {
            String tmp = result +  " " + words[i];
            if (tmp.trim().length() <= K) {
                result = tmp.trim();
            } else {
                break;
            }
        }

        return result;
    }

    private static String cropText1(String text, int length) {
        // validate parameters
        if (text == null || length == 0) {
            throw new IllegalArgumentException();
        }

        if (length >= text.length()) {
            return text;
        }

        String SEPARATOR = " ";
        int maxLength = length;
        String result = null;

        while(result == null) {
            maxLength = text.indexOf(SEPARATOR, maxLength);
            if (maxLength <= length) {
                result = text.substring(0, maxLength);
            } else {
                maxLength = text.indexOf(SEPARATOR, maxLength);; //text.lastIndexOf(SEPARATOR);
//                if (maxLength != -1) {
//                    result = text.substring(0, maxLength);
//                } else {
//                    result = text;
//                }
            }
        }

        return result;
    }
}
