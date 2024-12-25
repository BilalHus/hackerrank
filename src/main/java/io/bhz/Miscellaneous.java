package io.bhz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

public class Miscellaneous {

    public static void main(String[] args) {

    }


    static int[] maxXor(int[] arr, int[] queries) {
        BinaryTreeNode root = new BinaryTreeNode();
        for (int i : arr) {
            BinaryTreeNode.insert(i, root);
        }

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            result[i] = queries[i] ^ BinaryTreeNode.findMaxXor(queries[i], root);
        }

        return result;
    }

    static class BinaryTreeNode {
        BinaryTreeNode zero;

        BinaryTreeNode one;

        int value = -1;

        static void insert(int number, BinaryTreeNode root) {

            BinaryTreeNode node = root;

            for (int i = 31; i >= 0; i--) {
                int bit = (number >> i) & 1;

                if (bit == 1) {
                    if (node.one == null) {
                        node.one = new BinaryTreeNode();
                    }
                    node = node.one;
                } else {
                    if (node.zero == null) {
                        node.zero = new BinaryTreeNode();
                    }
                    node = node.zero;
                }
            }

            node.value = number;
        }


        static int findMaxXor(int number, BinaryTreeNode root) {

            BinaryTreeNode node = root;

            for (int i = 31; i >= 0; i--) {
                int bit = (number >> i) & 1;

                if (bit == 1 && node.zero != null) {
                    node = node.zero;
                } else if (bit == 1) {
                    node = node.one;
                } else if (node.one != null) {
                    node = node.one;
                } else {
                    node = node.zero;
                }
            }

            return node.value;
        }
    }


    static int[] maxXorSlow(int[] arr, int[] queries) {
        int[] result = new int[queries.length];
        Arrays.sort(arr);

        for (int i = 0; i < queries.length; i++) {
            int xor = queries[i];
            for (int j = 0; j < 31; j++) {
                xor ^= 1 << j;
            }

            int index = Arrays.binarySearch(arr, xor);
            if (index < 0) {
                index = -(index + 1);
            }

            result[i] = queries[i] ^ arr[Math.min(index, arr.length - 1)];
        }

        return result;
    }


    static class Node {

        public Node parent;
        public int value;
        public int children = 1;

        public Node(int value) {
            this.value = value;
            parent = this;
        }

        public Node getRepresentative() {
            return this == parent ? this : parent.getRepresentative();
        }

        public int rehang(Node to) {
            Node fromRepr = this.getRepresentative();
            Node toRepr = to.getRepresentative();
            if (toRepr.children == 0 && fromRepr.children == 0) {
                toRepr.children = 1;
            } else if (toRepr != fromRepr) {
                toRepr.children += fromRepr.children;
            }
            fromRepr.parent = toRepr;
            return toRepr.children;
        }
    }

    static int[] maxCircle(int[][] queries) {
        int[] result = new int[queries.length];
        Map<Integer, Node> map = new HashMap<>();

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            if (!map.containsKey(a)) {
                Node node = new Node(a);
                map.put(a, node);
            }

            if (!map.containsKey(b)) {
                Node node = new Node(b);
                map.put(b, node);
            }

            Node aNode = map.get(a);
            Node bNode = map.get(b);

            int children;

            if (aNode.children > bNode.children) children = bNode.rehang(aNode);
            else children = aNode.rehang(bNode);

            if (i == 0) {
                result[i] = children;
            } else {
                result[i] = Math.max(children, result[i - 1]);
            }
        }


        return result;
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
