import java.util.*;

public class goldMine {

    // return the maxGold which can be collected from a cell to last column in all
    // possible paths possible
    public static int maxGoldPath(int[][] arr, int sr, int sc, int[][] dir) {

        // Base Case -> Think of smallest Valid Input
        if (sc == arr[0].length - 1) { // It means we have reached Last Column
            return arr[sr][sc]; // we will return gold present in it as we have reached to end of our column
        }
        // Explore all the options and find max gold
        int gold = Integer.MIN_VALUE;

        for (int i = 0; i < dir.length; i++) {
            // 0th column gives us direction of next row
            // 1st column gives us direction of next column

            int nxtr = sr + dir[i][0]; // next row
            int nxtc = sc + dir[i][1]; // next column
            if (nxtr >= 0 && nxtr < arr.length && nxtc < arr[0].length) {
                gold = Math.max(gold, maxGoldPath(arr, nxtr, nxtc, dir));
            }
        }
        return gold + arr[sr][sc];
    }

    // This Recursive Code will use Memoization ie we will store our previously
    // calculated results
    public static int maxGoldPath_Mem(int[][] arr, int sr, int sc, int[][] dir, int[][] dp) {

        if (sc == arr[0].length - 1) { // It means we have reached Last Column
            dp[sr][sc] = arr[sr][sc];
            return arr[sr][sc]; // we will return gold present in it as we have reached to end of our column
        } else if (dp[sr][sc] != 0) { // check if gold has been calculated or not
            return dp[sr][sc];
        }
        // Explore all the options and find max gold
        int gold = Integer.MIN_VALUE;

        for (int i = 0; i < dir.length; i++) {
            // 0th column gives us direction of next row
            // 1st column gives us direction of next column

            int nxtr = sr + dir[i][0]; // next row
            int nxtc = sc + dir[i][1]; // next column
            if (nxtr >= 0 && nxtr < arr.length && nxtc < arr[0].length) {
                gold = Math.max(gold, maxGoldPath(arr, nxtr, nxtc, dir));
            }
        }
        dp[sr][sc] = gold + arr[sr][sc];
        return dp[sr][sc];
    }

    public static int maxGold(int[][] arr) {
        // This fn will pick each cell in first column
        // And call a recursive fn which will return maxGold present between path from
        // that cell to Last Column

        // Also we will create a Direction array -> which will help us to move to cells
        // in next column
        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
        int gold = Integer.MIN_VALUE;

        int[][] dp = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            // gold = Math.max(gold, maxGoldPath(arr, i, 0, dir)); // Simple Recursion
            gold = Math.max(gold, maxGoldPath_Mem(arr, i, 0, dir, dp));
        }
        return gold;
    }

    // using DP
    public static int maxGold_DP(int[][] arr) {

        // Assign Storage and Meaning
        // Each cell will contain MaxGold that can be collected from All Possible paths
        // from that cell to End Column
        int[][] dp = new int[arr.length][arr[0].length];

        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } }; // Direction Array

        // Check for Direction
        // Smaller Problem exists in last Column and bigger at First One -> Start from
        // last column
        // And Rows goes from bottom to Up
        for (int j = arr[0].length - 1; j >= 0; j--) {
            for (int i = arr.length - 1; i >= 0; i--) {
                // Break grid into 2 parts
                // 1) Last Column will contain gold as it izz
                // 2) Remaining will choose path having maxGold
                if (j == arr[0].length - 1) {
                    dp[i][j] = arr[i][j];
                } else {
                    // Find Max Gold from All Available Options
                    int gold = Integer.MIN_VALUE;

                    for (int op = 0; op < dir.length; op++) {

                        int nxtr = i + dir[op][0];
                        int nxtc = j + dir[op][1];

                        if (nxtr >= 0 && nxtr < arr.length) {
                            gold = Math.max(gold, dp[nxtr][nxtc]);
                        }
                    }
                    dp[i][j] = arr[i][j] + gold;
                }
            }
        }
        int gold = dp[0][0];
        for (int i = 1; i < arr.length; i++) {
            gold = Math.max(gold, dp[i][0]);
        }

        return gold;
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        System.out.println(maxGold_DP(arr));
    }
}