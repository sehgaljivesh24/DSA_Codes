
import java.util.*;

public class printSubsequence {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();
        printSS(s, ""); // Initially answer is empty

    }

    public static void printSS(String question, String ans) {

        if (question.length() == 0) {
            System.out.println(ans);
            return;
        }
        // roq = rest of question
        char ch = question.charAt(0);
        String roq = question.substring(1);

        printSS(roq, ans + ch);
        printSS(roq, ans);

    }

}