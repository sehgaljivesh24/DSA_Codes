import java.util.*;

// We have to reach n from 0 in minimum possible moves
// we have faith on minMoves that which ever floor we give it to start with
// it will tell us minimum no of moves to reach from that floor to n
public class climbStairsWithMinimumMoves {

    // Recursive Code
    public static int minMoves(int start, int n, int[] jumps) {

        // base case
        if (start == n) {
            return 1;
        }

        // This loop will help us check each intermediate floor for path with min_Moves
        int min_moves = Integer.MAX_VALUE;
        for (int i = 1; i <= jumps[start]; i++) { // we will check for each intermediate floor
            // and ask who has minimum steps to reach n
            if (start + i <= n) {
                int moves = minMoves(start + i, n, jumps);
                if (moves != 0) {
                    min_moves = Math.min(min_moves, moves); // we will choose path having min moves
                }
            }
        }
        if (min_moves == Integer.MAX_VALUE) {
            return 0; // it means there does not exist path from this floor
        }

        return min_moves + 1; // add own move to existing min_moves
    }

    // Recursive Code with Memoization --> save what u have calculated once and
    // again solve the same problem
    // every index on storage array will store min moves to reach n from ith floor
    public static int minMoves_Mem(int start, int n, int[] jumps, int[] storage) {

        // base case
        if (start == n) {
            return 1;
        } else if (storage[start] != -1) { // check if there exists a path from this floor to n with min_Moves
            return storage[start];
        }

        // This loop will help us check each intermediate floor for path with min_Moves
        int min_moves = Integer.MAX_VALUE;
        for (int i = 1; i <= jumps[start]; i++) { // we will check for each intermediate floor
            // and ask who has minimum steps to reach n
            if (start + i <= n) {
                int moves = minMoves(start + i, n, jumps);
                if (moves != 0) {
                    min_moves = Math.min(min_moves, moves); // we will choose path having min moves
                }
            }
        }
        if (min_moves == Integer.MAX_VALUE) {
            storage[start] = 0;
            return 0; // it means there does not exist path from this floor
        }

        storage[start] = min_moves + 1; // save min_Moves to reach from this floor to n
        return min_moves + 1; // add own move to existing min_moves
    }

    // We will now use DP -> Iterative
    public static int minMoves_DP(int n, int[] jumps) {

        int[] dp = new int[n + 1]; // size is of n+1 to store moves of path index 0 to n
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) { // small problem lies on end , hence start solving from end

            int min = Integer.MAX_VALUE;

            for (int j = 1; j <= jumps[i]; j++) { // ith floor will check for all intermediates
                // which have minMoves to reach n
                if (i + j <= n && dp[i + j] != 0) { // the intermediate floor should be within limits
                    // and intermediate floor should have a path
                    min = Math.min(min, dp[i + j]);
                }
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = 0;
            } else {
                dp[i] = min + 1;
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[] jumps = new int[n];
        // every index of this array tells us that how long can we jump from each floor
        for (int i = 0; i < n; i++) {
            jumps[i] = scn.nextInt();
        }

        // int moves = minMoves(0, n, jumps); // simple Recursive fn

        // int[] storage = new int[n + 1];
        // for (int i = 0; i < storage.length; i++) {
        //     storage[i] = -1;
        // }
        // int moves = minMoves_Mem(0, n, jumps, storage); // Memoization

        int moves = minMoves_DP(n, jumps);
        if (moves == 0) {
            System.out.println("null");
        } else {
            System.out.println(moves - 1);
        }
    }

}
