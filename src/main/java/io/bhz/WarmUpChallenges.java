package io.bhz;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WarmUpChallenges {

    public static long repeatedString(String s, long n) {
        long count = 0;
        long countInReminder = 0;
        long reminder = n % s.length();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                count++;
            }
            if (i == reminder - 1) {
                countInReminder = count;
            }
        }

        return count * (n / s.length()) + countInReminder;
    }

    public static int jumpingOnClouds(List<Integer> c) {
        int x = 0;
        int jumps = 0;

        while (x < c.size() - 1) {
            x = x + 2 < c.size() && c.get(x + 2) == 0 ? x + 2 : x + 1;
            jumps++;
        }

        return jumps;
    }

    public static int countingValleys(int steps, String path) {
        int level = 0;
        int valleys = 0;

        for (char c : path.toCharArray()) {
            level = c == 'U' ? level + 1 : level - 1;

            if (level == 0 && c == 'U') {
                valleys++;
            }
        }

        return valleys;

    }

    public static int sockMerchant(int n, List<Integer> ar) {
        Set<Integer> cache = new HashSet<>();
        int pairs = 0;

        for (Integer i : ar) {
            if (cache.contains(i)) {
                pairs++;
                cache.remove(i);
            } else {
                cache.add(i);
            }
        }
        return pairs;
    }
}
