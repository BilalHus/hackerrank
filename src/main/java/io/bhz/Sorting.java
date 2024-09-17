package io.bhz;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    public static int maximumToys(List<Integer> prices, int k) {
        Collections.sort(prices);
        int counter = 0;

        for (int i : prices) {
            if (k - i >= 0) {
                counter++;
                k-=i;
            } else {
                break;
            }
        }

        return counter;
    }

    public static void countSwaps(List<Integer> a) {
        int counter = 0;
        for (int i = 0; i < a.size(); i++) {

            for (int j = 0; j < a.size() - 1; j++) {
                if (a.get(j) > a.get(j + 1)) {
                    int saved = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, saved);
                    counter++;
                }
            }
        }

        System.out.printf("Array is sorted in %d swaps.%n", counter);
        System.out.printf("First Element: %d%n", a.get(0));
        System.out.printf("Last Element: %d%n", a.get(a.size() - 1));
    }


    class Player {
        String name;
        int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    class Checker implements Comparator<Player> {
        public int compare(Player a, Player b) {
            int diff = b.score - a.score;
            return diff != 0 ? diff : a.name.compareTo(b.name);
        }
    }
}
