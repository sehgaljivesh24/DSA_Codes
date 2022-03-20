
import java.util.*;

public class duplicateBrackets {

    public static boolean checkDuplicateBrackets(String s) {

        Stack<Character> st = new Stack<>(); // Stack of Characters

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch != ')') {
                st.push(ch);
            } else {
                if (st.peek() == '(') {
                    return true;
                } else {
                    while (st.peek() != '(') {
                        st.pop();
                    }
                    st.pop();
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            String s = scn.nextLine();
            System.out.println(checkDuplicateBrackets(s));
        }
    }

}
