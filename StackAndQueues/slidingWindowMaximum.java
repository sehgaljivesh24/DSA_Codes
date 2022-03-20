import java.util.*;


public class slidingWindowMaximum {

    public static int[] nge(int[] arr) {

        // nge will hold index of next greater element for each element
        // nge means sabse pehle element mere baad jo mughse bada ho

        int[] nge = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        int infinity = arr.length; // tells that if there is no greater element then largest element placed at
                                   // infinity
        for (int i = arr.length - 1; i >= 0; i--) {

            if (stack.empty()) {
                nge[i] = infinity;
            } else if (!stack.empty() && arr[stack.peek()] > arr[i]) {
                nge[i] = stack.peek();
            } else if (!stack.empty() && arr[stack.peek()] <= arr[i]) {

                while (!stack.empty() && arr[stack.peek()] <= arr[i]) {
                    stack.pop();
                }
                if (stack.empty()) {
                    nge[i] = infinity;
                } else if (!stack.empty() && arr[stack.peek()] > arr[i]) {
                    nge[i] = stack.peek();
                }
            }
            stack.push(i);
        }
        return nge;
    }

    public static void main(String[] args) {

        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scn.nextInt();
            }
            int k = scn.nextInt();

            int[] nge = nge(arr);

            int j = 0; // it will help us keep track of nge and will be used to jump if there is
                       // greater element

            for (int i = 0; i <= arr.length - k; i++) {

                if (j < i) { // by chance agar j peeche reh jaye toh initialize krdo
                    j = i;
                }

                while (nge[j] < i + k) { // kya j index pr jo element hai uska nge window ke andar hai ya nhi
                    j = nge[j]; // agar hai toh j will jump to current index vale element ka nge
                }

                System.out.println(arr[j]); // uss element ki given window mein ge
            }
            /*
             * for(int i = 0;i<nge.length;i++){
             * System.out.print(nge[i] + " ");
             * }
             */
        }
    }
}
