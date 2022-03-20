import java.util.*;

public class climbStairsWithVariableJumps {

    public static int nodp(int start, int n, int[] jumps) {

        if (start == n) { // we have reached our destination
            return 1;
        }
        int paths = 0, jump = jumps[start];
        // path variable indicates total path from start to n
        // jump variable indicates that uss floor pr khadh ke max kitne ki jump maar
        // sakte

        for (int i = 1; i <= jump; i++) {
            if (start + i <= n) {
                // we have made a jump of i
                // and now nodp has to return no of paths to reach from floor = start + i
                // i ki jump maar kr jis floor pr pauinche hain hum voh ab start banega
                paths += nodp(start + i, n, jumps);
            }
        }
        return paths;
    }

    // Using memoization
    public static int nodp_Mem(int start, int n, int[] jumps, int[] storage) {
        if (start == n) { // we have reached our destination
            storage[n] = 1;
            return 1;
        } else if (storage[start] != -1) { // it tells us that we have already calculated path to reach from start to n
            return storage[start];
        }
        // else calculate and store it for later use

        int paths = 0, jump = jumps[start];
        // path variable indicates total path from start to n
        // jump variable indicates that uss floor pr khadh ke max kitne ki jump maar
        // sakte

        for (int i = 1; i <= jump; i++) {
            if (start + i <= n) {
                // we have made a jump of i
                // and now nodp has to return no of paths to reach from floor = start + i
                // i ki jump maar kr jis floor pr pauinche hain hum voh ab start banega
                paths += nodp(start + i, n, jumps);
            }
        }
        storage[n] = paths;
        return paths;
    }

    // Using DP (iterative Method)
    public static int nodp_DP(int n, int[] jumps) {

        int[] storage = new int[n + 1];
        // size of storage is n + 1 bcoz we have floors from 0 to n
        // and hence each index stores no paths to reach from that floor to n
        storage[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            // ith index in jump array stores that how much max we can jump from that floor
            int jump = jumps[i];
            int paths = 0; // stores all path to reach from ith floor to n
            for (int j = 1; j <= jump; j++) {
                if (i + j <= n) {
                    paths += storage[i + j];
                }
            }
            storage[i] = paths;
        }
        return storage[0];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        // each index indicates that har ith floor pr khadh kr max. kitne tak ki jump
        // aap maar sakte
        int[] jumps = new int[n];
        for (int i = 0; i < n; i++) {
            jumps[i] = scn.nextInt();
        }

        // int[] storage = new int[n + 1];
        // for (int i = 0; i < storage.length; i++) {
        // storage[i] = -1;
        // }
        // System.out.println(nodp_Mem(0, n, jumps, storage));
        // System.out.println(nodp(0, n, jumps));

        System.out.println(nodp_DP(n, jumps));
    }

}
