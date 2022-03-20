import java.util.*;

public class getStairPath1 {

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    System.out.println(getStairPaths(n));

  }

    public static ArrayList<String> getStairPaths(int n) {

        if(n == 0)
        {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        else if(n<0)
        {
            ArrayList<String> bres = new ArrayList<>();
            return bres;
        }

        // Jump1
        ArrayList<String> jump1 = getStairPaths(n-1);
        // Jump2
        ArrayList<String> jump2 = getStairPaths(n-2);
        // Jump3
        ArrayList<String> jump3 = getStairPaths(n-3);

        ArrayList<String> jump = new ArrayList<>();
        for(int i = 0;i<jump1.size();i++)
        {
            jump.add(1 + jump1.get(i));
        }
        for(int i = 0;i<jump2.size();i++)
        {
            jump.add(2 + jump2.get(i));
        }
        for(int i = 0;i<jump3.size();i++)
        {
            jump.add(3 + jump3.get(i));
        }
        return jump;
    }
}
