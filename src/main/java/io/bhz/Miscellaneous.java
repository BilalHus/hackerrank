package io.bhz;

import java.util.stream.LongStream;

public class Miscellaneous {

    public static void main(String[] args) {
        System.out.println(flippingBits(123456)); //4294843839
    }

    public static long flippingBits(long n) {
        return LongStream.range(0, 32).reduce(n, (acc, i) -> acc ^ (1L << i));
    }
}
