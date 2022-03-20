
import java.util.*;

public class getKPC {

    public static void main(String[] args) throws Exception {

        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        System.out.println(getKPC(str));
        // int num = Character.getNumericValue(str.charAt(0));
        // System.out.println(num);

    }

  public static ArrayList<String> getKPC(String str) {

    if(str.length() == 0)
    {
        ArrayList<String> bres = new ArrayList<>();
        bres.add("");  // empty string
        return bres;
    }

    char ch = str.charAt(0);
    String res = str.substring(1);
    ArrayList<String> rres = getKPC(res);
    int num = ch - 48;          // Convert Character(Number) -> to Digit
    String temp = "nothing";
    switch (num)
    {
      //comparing value of variable against each case
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
    
    ArrayList<String> answer = new ArrayList<>();
    for(int i = 0;i<temp.length();i++)
    {
        char ch1 = temp.charAt(i);
        for(int j = 0;j<rres.size();j++)
        {
            answer.add(ch1 + rres.get(j));
        }
    }
    return answer;
  }
}