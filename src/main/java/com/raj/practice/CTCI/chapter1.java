package com.raj.practice.CTCI;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Chapter 1 Arrays
 */
public class chapter1 {
    public static void main(String[] args) {
        rotateMatrix();
    }

    private static void rotateMatrix() {
        int[][] matrix = new int[][] {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };

        for (int[] ints : matrix) {
            System.out.println(Arrays.stream(ints)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" , ")));
        }

        int size = matrix.length;

        for(int first = 0; first < size / 2; first++) {
            int last = size - 1 - first;

            for (int i = first; i < last; i++) {
                int offset = i - first;

                int temp = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = temp;
            }
        }

        for (int[] ints : matrix) {
            System.out.println(Arrays.stream(ints)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" , ")));
        }
    }
}
