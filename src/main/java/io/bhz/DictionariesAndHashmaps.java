package io.bhz;

import java.util.*;

public class DictionariesAndHashmaps {

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Set<Integer>> freq = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (List<Integer> query : queries) {
            int type = query.get(0);
            int value = query.get(1);

            if (type == 1) {
                int previousFreq = counter.getOrDefault(value, 0);
                int currentFreq = previousFreq + 1;
                counter.put(value, currentFreq);

                Set<Integer> prev = freq.getOrDefault(previousFreq, new HashSet<>());
                prev.remove(value);
                freq.put(previousFreq, prev);

                Set<Integer> curr = freq.getOrDefault(currentFreq, new HashSet<>());
                curr.add(value);
                freq.put(currentFreq, curr);

            } else if (type == 2) {
                int previousFreq = counter.getOrDefault(value, 0);
                int currentFreq = previousFreq > 0 ? previousFreq - 1 : 0;
                counter.put(value, currentFreq);

                Set<Integer> prev = freq.getOrDefault(previousFreq, new HashSet<>());
                prev.remove(value);
                freq.put(previousFreq, prev);

                Set<Integer> curr = freq.getOrDefault(currentFreq, new HashSet<>());
                curr.add(value);
                freq.put(currentFreq, curr);

            } else {
                boolean hasValue = freq.getOrDefault(value, new HashSet<>()).size() > 0;
                result.add(hasValue ? 1 : 0);
            }
        }

        return result;
    }

    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> map = new HashMap<>();
        Map<Long, Long> cumulative = new HashMap<>();
        long counter = 0;

        for (Long i : arr) {
            map.put(i, map.getOrDefault(i, 0L) + 1);
        }

        if (r == 1) {
            return map.values().stream().mapToLong(v -> v * (v - 1) * (v - 2) / 6).sum();
        }

        for (long i : arr) {

            if (cumulative.containsKey(i / r) && i % r == 0 && map.containsKey(i * r)) {
                counter += cumulative.get(i / r) * (map.get(i * r) - cumulative.getOrDefault(i * r, 0L));
            }

            cumulative.put(i, cumulative.getOrDefault(i, 0L) + 1);
        }

        return counter;
    }

    public static int sherlockAndAnagrams(String s) {
        Map<String, Integer> cache = new HashMap<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = i; j >= 0; j--) {
                stringBuilder.append(s.charAt(j));
                char[] array = stringBuilder.toString().toCharArray();
                Arrays.sort(array);
                String sorted = new String(array);

                if (cache.containsKey(sorted)) {
                    cache.put(sorted, cache.get(sorted) + 1);
                } else {
                    cache.put(sorted, 1);
                }
            }
        }

        int sum = 0;
        for (int value : cache.values()) {
            sum += value * (value - 1) / 2;
        }
        return sum;
    }

    public static String twoStrings(String s1, String s2) {
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < s1.length(); i++) {
            set.add(s1.charAt(i));
        }

        boolean hasCommon = false;
        for (int i = 0; i < s2.length(); i++) {
            if (set.contains(s2.charAt(i))) {
                hasCommon = true;
                break;
            }
        }

        return hasCommon ? "YES" : "NO";
    }

    public static void checkMagazine(List<String> magazine, List<String> note) {
        Map<String, Integer> cache = new HashMap<>();

        for (String s : magazine) {
            if (cache.containsKey(s)) {
                cache.put(s, cache.get(s) + 1);
            } else {
                cache.put(s, 1);
            }
        }

        boolean isValid = true;

        for (String s : note) {
            if (!cache.containsKey(s)) {
                isValid = false;
                break;
            }

            int value = cache.get(s);

            if (value == 1) {
                cache.remove(s);
            } else {
                cache.put(s, value - 1);
            }
        }

        System.out.println(isValid ? "Yes" : "No");
    }
}
