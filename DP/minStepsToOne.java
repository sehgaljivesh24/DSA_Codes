import java.util.*;

// Basically we have to explore all 3 options to find minimum no of steps to reach 1
// For each number explore all 3 options , mind minimum of all and it will be the answer to reach from that no to 1
public class minStepsToOne {

    // This was completely wrong way to do as there were different path which could
    // have been used to reach 1 faster
    public static int mstoR(int n) {

        if (n == 1) {
            return 0;
        }

        if (n % 2 == 0) {
            return 1 + mstoR(n / 2);
        } else if (n % 3 == 0) {
            return 1 + mstoR(n / 3);
        } else {
            return 1 + mstoR(n - 1);
        }
    }

    // Recursive Solution
    public static int mst1R(int n) {

        if (n == 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;

        if (n % 2 == 0) { // if the no is divisible by 2 then find the steps taken to reach from n/2 to 1
            int steps = 1 + mst1R(n / 2); // add 1 to steps taken to from n/2 to 1
            min = Math.min(min, steps); // calculate min of min & steps taken after dividing n by 2
        }
        if (n % 3 == 0) { // if the no is divisible by 3 then find the steps taken to reach from n/3 to 1
            int steps = 1 + mst1R(n / 3);
            min = Math.min(min, steps); // if steps taken to reach 1 after dividing by 3 are less than it is fastest way
                                        // to reach 1
        }
        min = Math.min(min, 1 + mst1R(n - 1)); // if steps taken to reach 1 after subtracting 1 are less than it is the
                                               // fastest way to reach 1

        return min; // we return the fastest or minimum steps to reach from n to 1
    }

    // Memorization
    public static int mst1M(int n, int[] storage) {

        if (n == 1) {
            storage[n] = 0;
            return 0;
        } else if (storage[n] != 0) { // provoked when steps to reach from n to 1 are already present
            return storage[n];
        }

        // We have 3 options -> 1) to subtract 1 from n. 2)Divide n by 2. 3)Divide n by 3 
        int op1 = 1 + mst1M(n - 1); // 
        int min = op1;

        if (n % 2 == 0) { // if the no is divisible by 2 then find the steps taken to reach from n/2 to 1
            int op2 = 1 + mst1R(n / 2); // add 1 to steps taken to from n/2 to 1
            min = Math.min(min, op2); // if op2 has less steps then present min steps then it is answer
        }
        if (n % 3 == 0) { // if the no is divisible by 3 then find the steps taken to reach from n/3 to 1
            int op3 = 1 + mst1R(n / 3);
            min = Math.min(min, op3); // if steps taken to reach 1 after dividing by 3 are less than it is fastest way
                                        // to reach 1
        }
        storage[n] = min; // store steps taken to reach 1 from n in nth index of storage array
        return storage[n]; // we return the fastest or minimum steps to reach from n to 1
    }

    public static int mst1M(int n) {
        int[] storage = new int[n + 1]; // used to store steps taken by each no to reach 1
        return mst1M(n, storage);
    }

    // DP
    public static int mst1DP(int n) {

        int[] storage = new int[n + 1];
        storage[1] = 0;

        for (int i = 2; i < storage.length; i++) {

            int min = Integer.MAX_VALUE;

            if (i % 2 == 0) {
                min = Math.min(min, 1 + storage[i / 2]);
            }
            if (i % 3 == 0) {
                min = Math.min(min, 1 + storage[i / 3]);
            }
            min = Math.min(min, 1 + storage[i - 1]);

            storage[i] = min;
        }
        return storage[n];
    }

    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();

            System.out.println(mst1DP(n));
            System.out.println(mst1M(n));
            // System.out.println(mstoR(n));
        }
    }

}
