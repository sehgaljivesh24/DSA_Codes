//package StackAndQueues;

import java.util.*;

public class balancedBrackets {

    public static boolean checkBalancedBrackets(String s) {

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                st.push(ch);
            } else if (ch == ')') {

                if (st.size() == 0 || st.peek() != '(') {
                    return false;
                } else if (st.peek() == '(') {
                    st.pop();
                }
            } else if (ch == ']') {
                if (st.size() == 0 || st.peek() != '[') {
                    return false;
                } else if (st.peek() == '[') {
                    st.pop();
                }
            } else if (ch == '}') {
                if (st.size() == 0 || st.peek() != '{') {
                    return false;
                } else if (st.peek() == '{') {
                    st.pop();
                }
            }
        }
        return st.isEmpty();
    }

    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            String s = scn.nextLine();
            System.out.println(checkBalancedBrackets(s));
        }
    }

}
