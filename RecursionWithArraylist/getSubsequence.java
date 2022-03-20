import java.util.*;

public class getSubsequence {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        System.out.println(gss(str));
    }

    // abc -> ,c,b,bc,a,ac,ab,abc
    // bc -> ,c,b,bc
    public static ArrayList<String> gss(String str) {

        if(str.length() == 0)
        {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);
        String res = str.substring(1);
        ArrayList<String> rres = gss(res);

        ArrayList<String> nres = new ArrayList<>();

        for (int i = 0; i < rres.size(); i++) {
            nres.add("" + rres.get(i));
        }

        for (int i = 0; i < rres.size(); i++) {
            nres.add(ch + rres.get(i));
        }

        return nres;
    }

}