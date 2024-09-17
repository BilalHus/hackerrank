package io.bhz;

import java.util.LinkedList;
import java.util.Scanner;

public class StacksQueues {

    public static void twoStackQueue() {
        LinkedList<Integer> firstStack = new LinkedList<>();
        LinkedList<Integer> secondStack = new LinkedList<>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) {
                firstStack.addLast(scan.nextInt());
            } else if (operation == 2) {
                prepareSecondStack(firstStack, secondStack);
                secondStack.pollLast();
            } else if (operation == 3) {
                prepareSecondStack(firstStack, secondStack);
                System.out.println(secondStack.peekLast());
            }
        }
        scan.close();
    }

    private static void prepareSecondStack(LinkedList<Integer> firstStack, LinkedList<Integer> secondStack) {
        if (secondStack.isEmpty()) {
            while (!firstStack.isEmpty()) {
                secondStack.addFirst(firstStack.pop());
            }
        }
    }

    public static String isBalanced(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        boolean valid = true;

        for (int i = 0; i < s.length(); i++) {
            char b = s.charAt(i);
            if (b == '{' || b == '[' || b == '(') {
                stack.addLast(b);
                continue;
            } else if (stack.isEmpty()) {
                valid = false;
                break;
            }

            char c = stack.getLast();

            if (c == '{' && b == '}' || c == '[' && b == ']' || c == '(' && b == ')') {
                stack.pollLast();
            } else {
                valid = false;
                break;
            }

        }
        return stack.isEmpty() && valid ? "YES" : "NO";
    }
}
