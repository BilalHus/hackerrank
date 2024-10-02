package io.bhz;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

public class Miscellaneous {

    public static void main(String[] args) {
//        maxCircle(
//                new int[][]{{1000000000,23}, {11, 3778}, {7, 47}, {11, 1000000000}}
//        );

        maxCircle(
                new int[][] {{1, 2}, {3, 4}, {1, 3}, {5, 7}, {5, 6}, {7, 4}}
        );
    }

    static int[] maxCircle(int[][] queries) {
        int[] result = new int[queries.length];
        Map<Integer, Integer> tree = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            add(a, b, tree, count);
        }


        return result;
    }

    static void add(int a, int b, Map<Integer, Integer> tree, Map<Integer, Integer> count) {
        count.putIfAbsent(a, 0);
        count.putIfAbsent(b, 0);

        if (tree.containsKey(a) && tree.containsKey(b)) {
            add(tree.get(a), tree.get(b), tree, count);
            count.put(a, count.get(a) + count.get(b));
        } else if (tree.containsKey(b)) {
            tree.put(a, b);
            if (count.get(a) == 0) {
                count.put(b, count.get(b) + 1);
            }
            count.put(b, count.get(a) + count.get(b));
        } else {
            tree.put(b, a);
            if (count.get(b) == 0) {
                count.put(a, count.get(a) + 1);
            }
            count.put(a, count.get(a) + count.get(b));
        }
    }

    public static String primality(int n) {
        if (n == 2 || n == 3 || n == 5) {
            return "Prime";
        }

        if (n == 1 || n % 2 == 0 || (n - 1) % 6 != 0 && (n + 1) % 6 != 0) {
            return "Not prime";
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return "Not prime";
            }
        }

        return "Prime";
    }

    public static long flippingBits(long n) {
        return LongStream.range(0, 32).reduce(n, (acc, i) -> acc ^ (1L << i));
    }
}
