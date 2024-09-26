package io.bhz;

import java.util.stream.LongStream;

public class Miscellaneous {

    public static void main(String[] args) {
        System.out.println(primality(1));
        System.out.println(primality(12));
        System.out.println(primality(5));
        System.out.println(primality(7));
    }

    public static String primality(int n) {
        if (n == 2 || n == 3 || n == 5) return "Prime";

        if (n == 1 || n % 2 == 0 || (n - 1) % 6 != 0 && (n + 1) % 6 != 0) return "Not prime";

        for (int i = 3; i * i <= n; i += 2) if (n % i == 0) return "Not prime";

        return "Prime";
    }

    public static long flippingBits(long n) {
        return LongStream.range(0, 32).reduce(n, (acc, i) -> acc ^ (1L << i));
    }
}
