// Basically Max Jumps in vertical and horizontal will be
// max_jump = no of DEST(rows/column) - no of SOURCE(rows/column)

// And for Diagonal 
// max_jump = min(dr-sr,dc-sc);
// as we cannot escape the grid
import java.util.*;

public class getMazePathWithJump {

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

        if(sr == dr && sc == dc)
        {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        ArrayList<String> response = new ArrayList<>();

        // Horizontal
        for (int i = 1; i <= dc - sc; i++) {
            ArrayList<String> hresp = getMazePaths(sr, sc + i, dr, dc);
            for(int j = 0;j<hresp.size();j++)
            {
                response.add("h" + i + hresp.get(j));
            }
        }
        // Vertically
        for (int i = 1; i <= dr - sr; i++) {
            ArrayList<String> vresp = getMazePaths(sr + i, sc , dr, dc);
            for(int j = 0;j<vresp.size();j++)
            {
                response.add("v" + i + vresp.get(j));
            }
        }
        // Diagonally
        // First we need to find diagonal jump
        int min_row = dr - sr;
        int min_col = dc - sc;
        int diag_jump = Math.min(min_row, min_col);
        for (int i = 1; i <= diag_jump; i++) {
            ArrayList<String> dresp = getMazePaths(sr + i, sc + i, dr, dc);
            for(int j = 0;j<dresp.size();j++)
            {
                response.add("d" + i + dresp.get(j));
            }
        }
        return response;
    }

}