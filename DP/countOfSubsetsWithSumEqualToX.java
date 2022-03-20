import java.util.Scanner;

public class countOfSubsetsWithSumEqualToX {

    // Recursive Fn will return count of all Subsets in which
    // sum of all elements is equal to target
    public static int countSubsetSumR(int[] arr, int target, int n) {

        // Base Condition
        // 1) If target == 0 ,we have found out that there exists a subset which have
        // sum of elements as 0
        if (target == 0) {
            return 1;
        } else if (n == 0 && target > 0) {
            // 2) If there are no elements left in array and there is target left to find
            return 0;
        }

        // Main Code
        // We have 2 cases
        // 1) If Value of element is greater than target then it can't be included
        // 2) If value of element is less or equal to target we have choice

        if (arr[n - 1] > target) {
            // return No. of subsets which can be formed of remaining elements
            // which satisfy the given target
            return countSubsetSumR(arr, target, n - 1);
        } else {

            // Find all subsets which satisfy the target
            // when the given element is included or not
            // we have to add both the instance
            int count = 0;
            // If element is included -> find subset which satisfy remaining condition
            count += countSubsetSumR(arr, target - arr[n - 1], n - 1);
            // If element is not included
            // Count if there exists subset which satisfy the target
            count += countSubsetSumR(arr, target, n - 1);
            return count;
        }
    }

    // Now we will convert Recursive Code to Iterative Code -> DP
    public static int countSubsetSumDP(int[] arr, int target) {

        // First we will need a storage to store result of individual Sub problems
        int[][] dp = new int[arr.length + 1][target + 1];
        // here Row -> will tells us how many elements are there in our array
        // Column -> will tells us what target has to be achieved

        // Just like in Recursive Code we have Base Cond. and Main Code
        // We convert Base Code to Initialization
        // Main Code remains same only Fn Calls replaced with Storage Cells

        // We Iterate over Cells using Loop -> Variables i n j

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                if (j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0 && j > 0) {
                    dp[i][j] = 0;
                } else {
                    if (arr[i - 1] > j) {
                        // return No. of subsets which can be formed of remaining elements
                        // which satisfy the given target
                        dp[i][j] = dp[i - 1][j];
                    } else {

                        // Find all subsets which satisfy the target
                        // when the given element is included or not
                        // we have to add both the instance
                        int count = 0;
                        // If element is included -> find subset which satisfy remaining condition
                        count += dp[i - 1][j - arr[i - 1]];
                        // If element is not included
                        // Count if there exists subset which satisfy the target
                        count += dp[i - 1][j];
                        dp[i][j] = count;
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
        System.out.println(countSubsetSumDP(arr, target));
        //System.out.println(countSubsetSumR(arr, target, n));
    }
}