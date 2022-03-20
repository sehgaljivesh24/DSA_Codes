import java.util.*;

public class printMazePathWithJump {

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
        }
        // Horizontal 
        for (int moves = 1; moves <= dc - sc; moves++) {
            printMazePaths(sr, sc + moves, dr, dc, psf + "h" + moves);
        }
        // Vertical Moves
        for (int moves = 1; moves <= dr - sr; moves++) {
            printMazePaths(sr + moves, sc , dr, dc, psf + "v" + moves);
        }
        // Diagonal
        // not to cross any column or row
        for (int moves = 1; moves <= Math.min(dc-sc,dr-sr); moves++) {
            printMazePaths(sr + moves, sc + moves, dr, dc, psf + "d" + moves);
        }
    }

}