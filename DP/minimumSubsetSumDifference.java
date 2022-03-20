import java.util.Scanner;

public class minimumSubsetSumDifference {

    // fss -> first subset ka sum
    // sss -> second subset ka sum
    public static int minDiff(int[] arr, int n, int fss, int sss) {

        if (n == 0) {
            int x = fss - sss;
            return (x > 0) ? x : -x;
        }

        int op1 = minDiff(arr, n - 1, fss + arr[n - 1], sss - arr[n - 1]);
        int op2 = minDiff(arr, n - 1, fss, sss);

        return Math.min(op1, op2);
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
        System.out.println(minDiff(arr, n, 0, sum));
    }
}
