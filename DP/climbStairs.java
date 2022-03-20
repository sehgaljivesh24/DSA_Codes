import java.util.*;

public class climbStairs {

    // Recursive fn -> nodp -> number of different paths
    // This code is inefficient as we have calculated that there is 1 path to reach
    // from 1 to 0 and 2 paths to reach from 2 to 0
    // It is calculating / solving the same problem again and again
    public static int nodp(int n) {

        if (n == 0) {
            return 1;
        }

        int paths = 0;
        if (n - 3 >= 0) {
            // we have made a jump of 3 , now u tell us how many paths are there to reach 0
            // from n-3
            paths += nodp(n - 3);
        }
        if (n - 2 >= 0) {
            // we made a jump of 2 , now u tell us that how many paths are there to reach 0
            // from n-2
            paths += nodp(n - 2);
        }
        if (n - 1 >= 0) {
            // we have made a jump of 1 , now u tell us how many paths are there to reach 0
            // from n-1
            paths += nodp(n - 1);
        }

        return paths;
    }

    // We will use technique called Memorization
    // We will take help of storage array where aat each index we store no of paths
    // to reach 0 from that floor-> 'i'
    // storage array will be of size n + 1
    // index -> 0 to n -> as we have to calculate paths to reach from n to 0
    public static int nodp_Mem(int n, int[] storage) {

        if (n == 0) {
            storage[n] = 1;
            return 1;
        } else if (storage[n] != 0) {
            // we will check that whether there is path that has already been calculated to
            // reach from n to 0
            return storage[n]; // basically return value present at that index
        }

        // if not calculated then find
        int paths = 0;
        if (n - 3 >= 0) { // if jump of 3 steps is possible
            // then say nodp_Mem to return no of paths to reach from n-3 to 0
            paths += nodp_Mem(n - 3, storage);
        }
        if (n - 2 >= 0) { // if jump of 2 is possible
            // then say to nodp to return no of paths to reach from n-2 to 0
            paths += nodp_Mem(n - 2, storage);
        }
        if (n - 1 >= 0) { // if jump of 1 is possible
            // then say to nodp to return no of paths to reach from n-1 to 0
            paths += nodp_Mem(n - 1, storage);
        }

        // we have calculated ways to reach from n to 0
        // now store them in storage array in case there is need in future
        storage[n] = paths;
        return paths;
    }

    // We will use DP now to solve it iteratively
    public static int nodp_DP(int n) {

        // we will use storage array to store no of paths to reach from ith index to 0
        // each index represents a level
        int[] storage = new int[n + 1];
        storage[0] = 1;

        for (int i = 1; i <= n; i++) {
            int paths = 0;

            if (i - 3 >= 0) { // we have made a jump of 3
                paths += storage[i - 3];
            }
            if (i - 2 >= 0) { // we have made a jump of 2
                // i-2 index of storage contains paths to reach from i-2 to 0
                paths += storage[i - 2];
            }
            if (i - 1 >= 0) { // we have made a jump of 1
                // i - 1 index of storage contains paths to reach from i-1 to 0
                paths += storage[i - 1];
            }

            storage[i] = paths;
        }
        return storage[n];
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        System.out.println(nodp_DP(n));
        //System.out.println(nodp_Mem(n, new int[n + 1]));
        //System.out.println(nodp(n));
    }

}
