import java.io.*;
import java.util.*;

public class stockSpanProblem {
    public static void display(int[] a) {
        StringBuilder sb = new StringBuilder();

        for (int val : a) {
            sb.append(val + "\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        int[] span = solve(a);
        display(span);
    }

    public static int[] solve(int[] arr) {

        int[] sol = new int[arr.length];

        Stack<Integer> stack = new Stack<>();

        // for every element we will check that is there any element greater than it in
        // the left part of array
        for (int i = 0; i < arr.length; i++) {

            if (stack.empty()) {

                sol[i] = i + 1;
            } else if (!stack.empty() && arr[stack.peek()] > arr[i]) {

                sol[i] = i - stack.peek();
            } else if (!stack.empty() && arr[stack.peek()] <= arr[i] ) {
                
                while (!stack.empty() && arr[stack.peek()] <= arr[i] ) {
                    stack.pop();
                }

                if (stack.empty()) {

                    sol[i] = i + 1;
                } else if (!stack.empty() && arr[stack.peek()] > arr[i]) {

                    sol[i] = i - stack.peek();
                }
            }
            stack.push(i);
        }

        return sol;
    }
}