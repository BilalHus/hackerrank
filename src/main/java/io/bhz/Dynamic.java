package io.bhz;

import java.util.List;

public class Dynamic {

    public static long candies(int n, List<Integer> arr) {
        int[] candiesLeft = new int[n];
        int[] candiesRight = new int[n];

        candiesLeft[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr.get(i) > arr.get(i - 1)) {
                candiesLeft[i] = candiesLeft[i - 1] + 1;
            } else {
                candiesLeft[i] = 1;
            }
        }

        candiesRight[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr.get(i) > arr.get(i + 1)) {
                candiesRight[i] = candiesRight[i + 1] + 1;
            } else {
                candiesRight[i] = 1;
            }
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(candiesLeft[i], candiesRight[i]);
        }

        return sum;
    }

    static int maxSubsetSum(int[] arr) {
        int prevA = 0;
        int prevB = Math.max(prevA, arr[0]);

        for (int i = 1; i < arr.length; i++) {
            int save = prevB;

            if (arr[i] <= 0) {
                prevB = Math.max(prevA, prevB);
            } else {
                prevB = Math.max(Math.max(prevB, prevA + arr[i]), arr[i]);
            }

            prevA = save;
        }

        return Math.max(prevA, prevB);
    }

}
