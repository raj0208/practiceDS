package com.raj.practice.Misc;

public class CropText {
    public static void main(String[] args) {
        String text = "very-long-input-will-be-truncated";
        System.out.println(cropText(text, 20));
        text = "a-b-c-d-e-f-g-veryveryverylongword";
        System.out.println(cropText(text, 20));
    }

    private static String cropText(String text, int length) {
        // validate parameters
        if (text == null || length == 0) {
            throw new IllegalArgumentException();
        }

        if (length >= text.length()) {
            return text;
        }

        String SEPARATOR = "-";

        int maxLength = text.indexOf(SEPARATOR, length);
        String result = null;
        if (maxLength >= length) {
            result = text.substring(0, maxLength);
        } else {
            maxLength = text.lastIndexOf(SEPARATOR);
            if (maxLength != -1) {
                result = text.substring(0, maxLength);
            } else {
                result = text;
            }
        }

        return result;
    }
}
