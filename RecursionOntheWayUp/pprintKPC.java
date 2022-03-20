import java.util.*;

public class pprintKPC {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String question = scn.next();
        printKPC(question, "");
    }

    public static void printKPC(String question, String asf) {

        if(question.length() == 0)
        {
            System.out.println(asf);
            return;
        }

        char ch = question.charAt(0);
        int num = ch - 48;
        String temp = "nothing";
        switch (num) {
        // comparing value of variable against each case
        case 0:
            temp = ".;";
            break;
        case 1:
            temp = "abc";
            break;
        case 2:
            temp = "def";
            break;
        case 3:
            temp = "ghi";
            break;
        case 4:
            temp = "jkl";
            break;
        case 5:
            temp = "mno";
            break;
        case 6:
            temp = "pqrs";
            break;
        case 7:
            temp = "tu";
            break;
        case 8:
            temp = "vwx";
            break;
        case 9:
            temp = "yz";
            break;
        }

        String qof = question.substring(1);
        for(int i = 0;i<temp.length();i++)
        {
            printKPC(qof,asf + temp.charAt(i));
        }
    }

}