/* https://codeshare.io/6p8AdN
 * 
 * Write a function that determines if a given string has balanced paranthesis
 */

import java.util.HashSet;
import java.util.Set;
import java.util.Deque;
import java.util.ArrayDeque;

public class BalancedBracketsSolution {

    public static String filterCharacters(String s1, String s2) {
        Set<Character> allowedChars = new HashSet<>();
        for (int i = 0; i < s2.length(); i++) {
            allowedChars.add(s2.charAt(i));
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            char currentChar = s1.charAt(i);
            if (allowedChars.contains(currentChar)) {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    public static boolean isBalanced1(String s) {
        String parenthesis = "({[]})";
        String in = filterCharacters(s, parenthesis);
        while (in.contains("()") || in.contains("[]") || in.contains("{}")) {
            in = in.replace("()", "");
            in = in.replace("[]", "");
            in = in.replace("{}", "");
        }
        return (in.length() == 0);
    }

    // does not work
    public static boolean isBalanced2(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);

            if (stack.isEmpty())
                continue;

            char peek = stack.peek();
            if (peek == '(' && c == ')')
                stack.pop();
            if (peek == '{' && c == '}')
                stack.pop();
            if (peek == '[' && c == ']')
                stack.pop();
        }

        return stack.isEmpty();
    }

    // Driver Code
    public static void main(String[] arg) {
        String[] examples = { "(((abb()c)dd))", "{abcde()", "(a))b(" }; // true, false, false
        long start = System.nanoTime();
        for (String s : examples) {
            System.out.println(s + " is balanced: " + isBalanced1(s));
        }
        System.out.printf("Execution time: %,d nanosec%n%n", System.nanoTime() - start);
        start = System.nanoTime();
        for (String s : examples) {
            System.out.println(s + " is balanced: " + isBalanced2(s));
        }
        System.out.printf("Execution time: %,d nanosec%n", System.nanoTime() - start);
    }

}
