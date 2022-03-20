import java.util.*;

public class rainWaterTrapping {

    public static int[] ler(int[] arr) {

        int[] ler = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {

            if (stack.empty()) {
                ler[i] = arr.length;
                stack.push(arr[i]);
            } else if (!stack.empty() && stack.peek() > arr[i]) {
                ler[i] = stack.peek();
            } else if (!stack.empty() && stack.peek() <= arr[i]) {
                stack.pop();
                stack.push(arr[i]);
                ler[i] = arr.length;
            }
        }

        return ler;
    }

    public static int[] lef(int[] arr) {
        // for each element it will contain largest element in its right part

        int[] lef = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {

            if (stack.empty()) { // it means there is no larger element
                lef[i] = -1;
                stack.push(arr[i]);
            } else if (!stack.empty() && stack.peek() > arr[i]) {
                lef[i] = stack.peek();
            } else if (!stack.empty() && stack.peek() <= arr[i]) {
                stack.pop();
                stack.push(arr[i]);
                lef[i] = -1;
            }

        }
        return lef;
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        // return largest elements on right n left side
        // -1 indicates that there is no larger element then current on left side of it
        // n indicates that there is no larger element then current element on right
        // side of it

        int[] ler = ler(arr);
        int[] lef = lef(arr);

        int area = 0;
        for (int i = 0; i < n; i++) {

            if (lef[i] == -1 || ler[i] == n) {
                continue;
            } else {

                int height = Math.min(lef[i], ler[i]) - arr[i];
                area = height * 1 + area;
            }
        }
        System.out.println(area);
    }

}
