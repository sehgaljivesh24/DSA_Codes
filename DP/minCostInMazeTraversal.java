import java.util.*;

public class minCostInMazeTraversal {

    // Recursive Fn -> it will return us Min Cost to travel in Maze
    // i.e. jo cells humein encountered hue hain unmein se janne ki cost ka sum
    // should be minimum
    public static int minCost(int[][] maze, int sr, int sc, int dr, int dc) {

        // Base Case -> Think of smallest Valid Input
        if (sr == dr && sc == dc) {
            return maze[sr][sc]; // now the min cost would be just to reach destination row n column
        }

        int min = Integer.MAX_VALUE;
        if (sc < dc) { // check such that ki hum right jate hue maze ke bahar na ho jayein
            min = Math.min(min, minCost(maze, sr, sc + 1, dr, dc));
        }
        if (sr < dr) { // check such that ki hum down jate hue maze ke bahar na ho jayein
            min = Math.min(min, minCost(maze, sr + 1, sc, dr, dc));
        }

        return min + maze[sr][sc];
    }

    // We will use Memoization as It will speed up the recursion
    // We will memorize the solutions to our subProblems
    // int[][] dp will store solution to our subproblem
    public static int minCost_Mem(int[][] maze, int sr, int sc, int dr, int dc, int[][] dp) {

        // Base Case-> Think of Smallest Valid Input
        if (sr == dr && sc == dc) {
            dp[sr][sc] = maze[sr][sc];
            return dp[sr][sc];
        } else if (dp[sr][sc] != 0) { // particular cell se dr , dc jane ki min cost hai then vahi use krlo
            return dp[sr][sc];
        }

        int min = Integer.MAX_VALUE;
        if (sc < dc) { // if we r moving in right direction
            min = Math.min(min, minCost_Mem(maze, sr, sc + 1, dr, dc, dp));
        }
        if (sr < dr) { // if we are moving in downward direction
            min = Math.min(min, minCost_Mem(maze, sr + 1, sc, dr, dc, dp));
        }

        dp[sr][sc] = maze[sr][sc] + min;
        return dp[sr][sc];
    }

    // DP Bottom Up Approach
    public static int minCost_DP(int[][] maze) {

        int[][] dp = new int[maze.length][maze[0].length];
        int rows = maze.length;
        int cols = maze[0].length;
        dp[rows - 1][cols - 1] = maze[rows - 1][cols - 1];
        for (int i = rows - 2; i >= 0; i--) {
            dp[i][cols - 1] = maze[i][cols - 1] + dp[i + 1][cols - 1];
        }

        for (int i = cols - 2; i >= 0; i--) {
            for (int j = rows - 1; j >= 0; j--) {

                int min = Integer.MAX_VALUE;
                // if there exists a path to go to right , then check if that path has min cost
                if (i < cols - 1) {
                    min = Math.min(min, dp[j][i + 1]);
                }
                // if there exists a path to go downwards ,then check if that paths has min cost
                if (j < rows - 1) {
                    min = Math.min(min, dp[j + 1][i]);
                }
                dp[j][i] = maze[j][i] + min;
            }
        }
        return dp[0][0];
    }

    public static int minCost_DP2(int[][] maze) {

        int n = maze.length;
        int m = maze[0].length;

        // Assign Storage
        // Meaning -> each cell will contain cheapest path to travel from that cell to
        // bottom right
        int[][] dp = new int[n][m];

        // I will go from last cell to first or Bottom to Up

        // first fill last row and start from always last column
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {

                if (i == n - 1 && j == m - 1) { // Last Cell
                    dp[i][j] = maze[i][j];
                } 
                else if (i == n - 1) { // last Row
                    dp[i][j] = maze[i][j] + dp[i][j + 1];
                } 
                else if (j == m - 1) { // Last Column
                    dp[i][j] = maze[i][j] + dp[i + 1][j];
                } 
                else { // any other cell has 2 option either go down or right
                    dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) + maze[i][j];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();

        int[][] maze = new int[n][m]; // this 2d array contains cost to enter to each cell
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maze[i][j] = scn.nextInt();
            }
        }
        System.out.println(minCost_DP(maze));
        // System.out.println(minCost_Mem(maze, 0, 0, n - 1, m - 1, new int[n][m]));
        // System.out.println(minCost(maze, 0, 0, n - 1, m - 1));
    }
}