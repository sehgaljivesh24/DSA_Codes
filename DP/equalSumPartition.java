import java.util.Scanner;

public class equalSumPartition {

    public static boolean equalSumPartitionR(int[] arr, int target, int n) {

        // Base Condition -> think of smallest valid Input
        if (target == 0) {
            // there exists a subset whose sum of elements is equal to target
            return true;
        } else if (n == 0 && target > 0) {
            // it means we have created all possible subsets
            // and there are no elements left in our array
            // Also there is still target to achieve
            return false;
        }

        if (arr[n - 1] > target) {
            // we can't include it in our subset
            // As if we add it it will not help us in achieving it
            return equalSumPartitionR(arr, target, n - 1);
            // we will say to fn to check in remaining elements
            // that is there any subset which will satisfy the target
        } else {

            // we have 2 choices
            // 1) to include the element in our subset
            // Now we will ask Fn to check if there exists subsets
            // in which sum of elements will be equal to remaining target
            boolean op1 = equalSumPartitionR(arr, target - arr[n - 1], n - 1);
            if (op1) {
                return true;
            }
            // 2) We have not included it in our subset
            boolean op2 = equalSumPartitionR(arr, target, n - 1);
            if (op2) {
                return true;
            }
        }
        return false;
    }

    public static boolean equalSumPartitionDP(int[] arr) {

        int target = 0;
        for (int i = 0; i < arr.length; i++) {
            target += arr[i];
        }
        if(target %2 != 0){
            return false;
        }

        // Assign Storage -> for that we have find its dimensions
        // 2 things are changing
        boolean[][] dp = new boolean[arr.length + 1][target/2 + 1];

        // We will use Loop to find results of our sub problem
        // variable i -> represent no of elements present in our array
        // variable j -> represent target to be achieved

        // Code written in Recursion will come as it izz
        // Initialization in 2d Array will be done to represent Base Case
        // As it will help us to solve bigger Problem
        // And Main Code which has calls to fn will be replaced by cells
        // as each cell contains results of sub problems

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                // Base Condition
                if (j == 0) {
                    // if target is 0 then there exists a empty subset
                    // sum of elements in it is 0
                    dp[i][j] = true;
                } else if (i == 0 && j > 0) {
                    // It means there are no elements left in our array
                    // but there is still a target to achieve
                    dp[i][j] = false;
                } else {

                    // Main Code
                    if (arr[i - 1] > j) {
                        // we can't include it in our subset
                        // As if we add it it will not help us in achieving it
                        dp[i][j] = dp[i - 1][j];
                        // we will say to fn to check in remaining elements
                        // that is there any subset which will satisfy the target
                    } else {

                        // we have 2 choices
                        // 1) to include the element in our subset
                        // Now we will ask Fn to check if there exists subsets
                        // in which sum of elements will be equal to remaining target
                        boolean op1 = dp[i - 1][j - arr[i - 1]];
                        // 2) We have not included it in our subset
                        boolean op2 = dp[i - 1][j];
                        if (op1 || op2) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = false;
                        }
                    }
                }

            }
        }
        return dp[arr.length][target/2];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
            sum += arr[i];
        }

        if (sum % 2 != 0) { // if it can't be divided by 2 then the array can't be broken into 2 subsets
            System.out.println("false");
            return;
        } else {
            System.out.println(equalSumPartitionR(arr, sum / 2, n));
        }

    }

}
