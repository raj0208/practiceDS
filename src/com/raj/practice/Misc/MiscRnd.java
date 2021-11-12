package com.raj.practice.Misc;

public class MiscRnd {
    public static void main(String[] args) {
//        oddNumber();
        int[] A = new int[7];
        A[0] = 1;
        A[1] = 2;
        A[2] = 3;
        A[3] = 3;
        A[4] = 1;
        A[5] = 3;
        A[6] = 1;
        solution(3, A);
    }

    public static void firstMissingPositive(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("1");
            return;
        }

        for (int i = 0; i < arr.length; ) {
            if (arr[i] > arr.length || arr[i] <= 0 || arr[arr[i]] == arr[i])
                i++;
            else {
                int tmp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = tmp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                System.out.println(i + 1);
                return;
            }
        }

        System.out.println(arr[arr.length - 1] + 1);
    }

    public static void oddNumber() {
        int[] arr = new int[] {1,2,3,2,1,5,4,4,5};

        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            num ^= arr[i];
        }

        System.out.println(num);
    }

    public static int solution(int M, int[] A) {
        int N = A.length;
        int[] count = new int[M + 1];
        for (int i = 0; i <= M; i++)
            count[i] = 0;
        int maxOccurence = 1;
        int index = -1;
        for (int i = 0; i < N; i++) {
            if (count[A[i]] > 0) {
                int tmp = count[A[i]] + 1;
                if (tmp > maxOccurence) {
                    maxOccurence = tmp;
                    index = i;
                }
                count[A[i]] = tmp;
            } else {
                count[A[i]] = 1;
            }
        }
        return A[index];
    }

}
