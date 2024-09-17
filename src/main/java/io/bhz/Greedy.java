package io.bhz;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Greedy {

    public static int maxMin(int k, List<Integer> arr) {
        Collections.sort(arr);
        int maxMin = Integer.MAX_VALUE;

        for (int i = 0; i < arr.size() - k + 1; i++) {
            maxMin = Math.min(maxMin, arr.get(i + k - 1) - arr.get(i));
        }

        return maxMin;
    }

    static int getMinimumCost(int k, int[] c) {
        int sum = 0;
        Arrays.sort(c);

        for (int i = c.length - 1, j = 0; i >= 0; i--, j++) {
            sum += (1 + j / k) * c[i];
        }

        return sum;
    }

    public static int luckBalance(int k, List<List<Integer>> contests) {
        Comparator<List<Integer>> comparator = (o1, o2) -> {
            int importance = o2.get(1) - o1.get(1);
            return importance != 0 ? importance : o2.get(0) - o1.get(0);
        };

        contests.sort(comparator);

        int sum = 0;

        for (int i = 0, j = 0; i < contests.size(); i++) {
            int importance = contests.get(i).get(1);
            int luck = contests.get(i).get(0);

            if (j < k) {
                sum += luck;
                j++;
            } else if (importance == 1) {
                sum -= luck;
            } else {
                sum += luck;
            }
        }

        return sum;
    }

    public static int minimumAbsoluteDifference(List<Integer> arr) {
        Collections.sort(arr);
        int minDiff = arr.get(arr.size() - 1) - arr.get(0);

        for (int i = 0; i < arr.size() - 1; i++) {
            int diff = arr.get(i + 1) - arr.get(i);
            if (minDiff > diff) {
                minDiff = diff;
            }
        }

        return minDiff;
    }
}
