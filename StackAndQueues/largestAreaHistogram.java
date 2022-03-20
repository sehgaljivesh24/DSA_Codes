import java.util.*;

public class largestAreaHistogram {

    public static class Pair {

        int barHeight;
        int index;

        Pair(int barHeight, int index) {
            this.barHeight = barHeight;
            this.index = index;
        }
    }

    // next smaller to left
    public static int[] NSL(int[] arr) {

        int[] nsl = new int[arr.length];
        Stack<Pair> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {

            if (stack.empty()) { // It means there is no element smaller than it
                nsl[i] = -1;
            } else if (!stack.empty() && stack.peek().barHeight < arr[i]) {
                nsl[i] = stack.peek().index;
            } else if (!stack.empty() && stack.peek().barHeight >= arr[i]) { // size of bar at top of stack is larger

                while (!stack.empty() && stack.peek().barHeight >= arr[i]) {
                    stack.pop();
                }
                if (stack.empty()) {
                    nsl[i] = -1;
                } else if (!stack.empty() && stack.peek().barHeight < arr[i]) {
                    nsl[i] = stack.peek().index;
                }
            }
            stack.push(new Pair(arr[i], i));
        }

        return nsl;
    }

    public static int[] NSR(int[] arr) {

        int[] nsr = new int[arr.length]; // store index's of nsr for given element
        Stack<Pair> stack = new Stack<>(); // It will store elements which are to right of any element

        int index = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {

            if (stack.empty()) {
                nsr[i] = index;
            } else if (!stack.empty() && stack.peek().barHeight < arr[i]) {
                nsr[i] = stack.peek().index;
            } else if (!stack.empty() && stack.peek().barHeight >= arr[i]) {

                while (!stack.empty() && stack.peek().barHeight >= arr[i]) {
                    stack.pop();
                }
                if (stack.empty()) {
                    nsr[i] = index;
                } else if (!stack.empty() && stack.peek().barHeight < arr[i]) {
                    nsr[i] = stack.peek().index;
                }
            }

            stack.push(new Pair(arr[i], i));
        }

        return nsr;
    }

    public static int maxAreaOfHistogram(int[] arr) {

        int[] nsr = NSR(arr);
        int[] nsl = NSL(arr);
        // for each bar we need width of rectangle that it can form
        // width = nsr - nsl - 1;
        int[] width = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            width[i] = nsr[i] - nsl[i] - 1;
        }

        int[] area = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            area[i] = arr[i] * width[i];
        }

        int max = 0;
        for (int i = 0; i < area.length; i++) {

            if (max < area[i]) {
                max = area[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {

        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = scn.nextInt();
            }

            // each index represents height of bar in a histogram

            // Area of Rectangle = Height of Rectangle * Width of Rectangle
            // Height is known

            // Width is basically for a given height , how much a bar can extend both in
            // left side and right side
            // 1) A bar can expand both on left and right only when there are other bar's
            // with height greater than it
            // 2) we need to find how many "total bar's" are there which are greater or
            // equal to it
            // 3) which can found by calculating index's of NSL & NSR of each bar
            // 4) And then subtracting them
            //int x = Integer.MIN_VALUE;
            int area = maxAreaOfHistogram(arr);
            System.out.println(area);
        }
    }
}