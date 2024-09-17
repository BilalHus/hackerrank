package io.bhz;

import java.util.*;

public class Search {

    public static void main(String[] args) {
        whatFlavors(List.of(2, 1, 3, 5, 6), 5);
    }

    public static void whatFlavors(List<Integer> cost, int money) {
        Map<Integer, Integer> cache = new HashMap<>();

        for(int i = 0; i < cost.size(); i++) {
            int element = cost.get(i);
            int diff = money - element;

            if (cache.containsKey(diff)) {
                System.out.println(cache.get(diff) + " " + (i + 1));
                break;
            } else {
                cache.put(element, i + 1);
            }
        }
    }
}
