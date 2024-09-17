package io.bhz;

import java.util.HashMap;
import java.util.Map;

public class Strings {

    public static int commonChild(String s1, String s2) {
        int[][] m = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                m[i + 1][j + 1] = s1.charAt(i) == s2.charAt(j) ? m[i][j] + 1 : Math.max(m[i + 1][j], m[i][j + 1]);
            }
        }

        return m[s1.length()][s2.length()];
    }

    static long substrCount(int n, String s) {
        int count = 0;

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {
                char current = s.charAt(j);
                char prev = s.charAt(j - 1);

                if (current == prev) {
                    count++;
                } else if (2 * j - i < n) {
                    boolean isValid = true;
                    for (int k = j + 1; k <= 2 * j - i; k++) {
                        if (s.charAt(k) != s.charAt(i)) {
                            isValid = false;
                            break;
                        }
                    }

                    if (isValid) {
                        count++;
                    }

                    break;
                } else {
                    break;
                }
            }
        }

        return count + n;
    }

    public static String isValid(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        HashMap<Integer, Integer> values = new HashMap<>();

        for (Integer i : map.values()) {
            if (values.containsKey(i)) {
                values.put(i, values.get(i) + 1);
            } else {
                values.put(i, 1);
            }
        }

        boolean isValid = false;

        if (values.size() == 1) {
            isValid = true;
        } else if (values.size() == 2) {
            Integer first = null;
            Integer second = null;

            for (Integer i : values.keySet()) {
                if (first == null) {
                    first = i;
                } else if (second == null) {
                    second = i;
                }
            }

            isValid = first == 1 && values.get(first) == 1 || second == 1 && values.get(second) == 1 ||
                    Math.abs(first - second) <= 1 && ((first > second && values.get(first) == 1 || second == 1)
                            || (second > first && values.get(second) == 1));
        }


        return isValid ? "YES" : "NO";
    }

    public static int alternatingCharacters(String s) {
        int counter = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                counter++;
            }
        }

        return counter;

    }

    public static int makeAnagram(String a, String b) {
        Map<Character, Integer> map = new HashMap<>();
        int bLeftovers = 0;

        for (char c : a.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }

        for (char c : b.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            } else {
                bLeftovers++;
            }
        }

        int aLeftovers = 0;
        for (Integer i : map.values()) {
            aLeftovers += i;
        }

        return aLeftovers + bLeftovers;
    }
}
