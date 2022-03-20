
import java.util.*;

public class getMazePath {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int row = scn.nextInt();
        int column = scn.nextInt();
        System.out.println(getMazePaths(1, 1, row, column));

    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {

        // base Case
        if(sr>dr || sc>dc)
        {
            ArrayList<String> bcase = new ArrayList<>();
            return bcase;
        }
        else if(sr == dr && sc == dc)
        {
            ArrayList<String> bcase = new ArrayList<>();
            bcase.add("");
            return bcase;
        }
        // I moved a step horizontally now i need to start from sr , sc + 1;
        // bcoz i have moved 1 column to the left
        // and now i need path from that row and column
        ArrayList<String> horizontal = getMazePaths(sr, sc + 1, dr, dc); // horizontally move krdiya aage ka tum dekho
        
        // If a moved 1 step vertically means 1 row below
        // row will increase sr -> sr + 1
        // i need to get path from that row and column
        ArrayList<String> vertically = getMazePaths(sr + 1, sc, dr, dc); // vertically move krdiya aage ka tum dekho
        
        ArrayList<String> path = new ArrayList<>();

        for(int i = 0;i<horizontal.size();i++)
        {
            path.add("h" + horizontal.get(i));
        }
        for(int i = 0;i<vertically.size();i++)
        {
            path.add("v" + vertically.get(i));
        }
        return path;
    }
}