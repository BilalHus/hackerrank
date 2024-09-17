package io.bhz;

public class RecursionBacktracking {

    public static void main(String[] args) {
        System.out.println(stepPerms(5));
        System.out.println(stepPerms(1));
        System.out.println(stepPerms(3));
        System.out.println(stepPerms(7));
    }

    public static int stepPerms(int n) {
        if (n < 3) return n;

        long a = 1, b = 2, c = 4;

        for (int i = 4; i <= n; i++) {
            long saveC = c;
            long saveB = b;
            c = (c + b + a) % 10000000007L;
            b = saveC;
            a = saveB;
        }

        return (int) c;
    }

    public static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int a = 0, b = 1;

        for (int i = 2; i <= n; i++) {
            int save = b;
            b += a;
            a = save;
        }

        return b;
    }

}
