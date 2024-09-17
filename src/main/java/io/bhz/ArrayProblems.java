package io.bhz;

import java.util.ArrayList;
import java.util.List;

public class ArrayProblems {

    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        long[] a = new long[n];

        for (List<Integer> query : queries) {
            a[query.get(0) - 1] += query.get(2);
            if (query.get(1) < a.length) {
                a[query.get(1)] -= query.get(2);
            }
        }

        long currentSum = 0;
        long maxSum = 0;
        for (long l : a) {
            currentSum += l;

            if (currentSum >= maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum;
    }

    static int minimumSwaps(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i + 1) {
                int save = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = save;
                count++;
            }
        }

        return count;
    }

    public static void minimumBribes(List<Integer> q) {
        List<Integer> p = new ArrayList<>(q.size());
        boolean chaotic = false;
        int bribes = 0;

        for (int i = 0; i < q.size(); i++) {
            p.add(i + 1);
        }

        for (int i = 0; i < p.size(); i++) {

            if (q.get(i).equals(p.get(i))) {
                continue;
            } else if (i < q.size() - 1 && q.get(i).equals(p.get(i + 1))) {
                swap(p, i, i + 1);
                bribes++;
            } else if (i < q.size() - 2 && q.get(i).equals(p.get(i + 2))) {
                swap(p, i + 1, i + 2);
                swap(p, i, i + 1);
                bribes += 2;
            } else {
                chaotic = true;
                break;
            }
        }

        if (chaotic) {
            System.out.println("Too chaotic");
        } else {
            System.out.println(bribes);
        }
    }

    public static void swap(List<Integer> a, int i, int j) {
        int save = a.get(i);
        a.set(i, a.get(j));
        a.set(j, save);
    }

    public static List<Integer> rotLeft(List<Integer> a, int d) {
        rotateSymmetrically(a, 0, a.size() - 1);
        rotateSymmetrically(a, 0, a.size() - d - 1);
        rotateSymmetrically(a, a.size() - d, a.size() - 1);
        return a;
    }

    public static void rotateSymmetrically(List<Integer> a, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int saved = a.get(i);
            a.set(i, a.get(j));
            a.set(j, saved);
        }
    }


    public static int hourglassSum(List<List<Integer>> arr) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                int sum = arr.get(i).get(j) + arr.get(i).get(j + 1) + arr.get(i).get(j + 2)
                        + arr.get(i + 1).get(j + 1)
                        + arr.get(i + 2).get(j) + arr.get(i + 2).get(j + 1) + arr.get(i + 2).get(j + 2);
                maxSum = Math.max(sum, maxSum);
            }
        }

        return maxSum;

    }
}