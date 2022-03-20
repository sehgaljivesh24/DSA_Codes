import java.util.*;

public class targetSumSubset {

    // Recursive Fn
    // We have Faith in it that it will find if there exists a subset in given array
    // in which sum of value of elements is equal to target
    public static boolean targetSumSubsetR(int[] arr, int target, int n) {

        // Base Condition -> Think of Smallest Possible Input
        if (target == 0) { // It means we have achieved our goal
            return true;
        } else if (n == 0 && target > 0) { // It means there are no elements in array
            // we have not found any subset that has achieved the goal
            // hence no subset found
            return false;
        }

        // Take Help of Choice Diagram
        if (arr[n - 1] > target) {
            // If a particular element's value is greater than our target
            // we can't include it
            return targetSumSubsetR(arr, target, n - 1);
            // we will say to our fn that check if there exists a subset in which
            // sum of elements is equal to target
        } else {
            // Now we have choice to include it or not
            // We will have 2 case
            // 1) If we include the element, then we will say to fn to check if there exists
            // a subset in which sum of elements is equal to remaining target as it has done
            // some work
            // 2) If we don't include the element,then we will say to fn to check
            // Final -> In whichever we find the subset it will be answer

            boolean case1 = targetSumSubsetR(arr, target - arr[n - 1], n - 1);
            if (case1) {
                return true;
            }
            boolean case2 = targetSumSubsetR(arr, target, n - 1);
            if (case2) {
                return true;
            }
        }
        return false;
    }

    // Each Cell will store result of SUb Problems
    public static boolean targetSumSubsetDP(int[] arr, int target) {

        // Create a Storage Array with Dimension's as
        // In Recursion the Variables which were changing shall account for the
        // dimensions
        boolean[][] dp = new boolean[arr.length + 1][target + 1];

        // Now Recursive Code will be converted to Iterative with Storage Grid
        // In loop -> i -> represents no. of elements present in current array
        // j -> represents target to achieved at particular moment

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                // Base Condition
                if (j == 0) {
                    // if target(j) is 0 , then there always exists a Empty subset
                    // in which sum of elements will be equal to 0
                    dp[i][j] = true;
                } else if (i == 0 && j > 0) {
                    // If there are no elements left in array and there is a target to be achieved
                    // Then it is not possible as there are no subsets
                    dp[i][j] = false;
                }
                // No here comes Code with Choice Diagram where
                // The code written in Recursion comes into play as it izz
                // Simply replace the Fn call with Storage Cell
                else {

                    if (arr[i - 1] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        boolean case1 = dp[i - 1][j - arr[i - 1]];
                        boolean case2 = dp[i - 1][j];

                        if (case1 || case2) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = false;
                        }
                    }
                }
            }
        }

        return dp[arr.length][target];
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int target = scn.nextInt();
        System.out.println(targetSumSubsetDP(arr, target));
    }
}