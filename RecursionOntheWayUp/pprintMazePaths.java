
import java.util.*;

public class pprintMazePaths {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int row = scn.nextInt();
        int column = scn.nextInt();
        printMazePaths(1, 1, row, column, "");
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static void printMazePaths(int sr, int sc, int dr, int dc, String psf) {
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return;
        } else if (sr > dr || sc > dc) {
            return;
        }

        // Option1 move horizontally
        printMazePaths(sr, sc + 1, dr, dc, psf + "h");

        // Option2 move vertically
        printMazePaths(sr + 1, sc, dr, dc, psf + "v");

    }

}