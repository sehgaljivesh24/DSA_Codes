import java.util.*;

public class celebrityProblem {

    // O(n2) Time Complexity
    // We will use concept-> Indegree-> how many people know you
    // Outdegree-> how many people you know

    // And for every person we will keep a record of them
    public static void celebrity_1(int[][] arr) {

        int[] indeg = new int[arr.length];
        int[] outdeg = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 1) {
                    // It means i knows j
                    indeg[j]++;
                    outdeg[i]++;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            // A person will be a celebrity if he knows no one and everyone knows him except
            // himself
            if (indeg[i] == arr.length - 1 && outdeg[i] == 0) {
                System.out.println(i);
                return;
            }
        }
        System.out.println("none");
    }

    // Time Complexity -> O(n2)
    public static void celebrity_2(int[][] arr) {

        // for a Person to be a celebrity it should follow 2 conditions
        // 1. Row of celebrity should contain all zero's as it should not know anyone
        // 2. Column of Celebrity should contain 1 except himself

        for (int i = 0; i < arr.length; i++) {

            // check row
            int outdeg = 0;
            for (int j = 0; j < arr.length; j++) {

                if (i != j && arr[i][j] != 0) { // used to check that he should not know anyone
                    outdeg++;
                }
            }
            // check column of a person
            int indeg = 0;
            for (int j = 0; j < arr.length; j++) {

                if (i != j && arr[j][i] == 1) { // used to check that all knows him or not
                    indeg++;
                }
            }
            if (indeg == arr.length - 1 && outdeg == 0) {
                System.out.println(i);
                return;
            }
        }

    }

    // Time Complexity -> O(n)
    public static void celebrity_3(int[][] arr) {

        // In this we will use technique of eliminate the candidates who are not
        // eligible for a celebrity

        // A celebrity is known by everyone
        // IF a person doesn't know a another person,then another person can't be a
        // celebrity but first one can

        // fill all potential candidates into stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            stack.push(i);
        }

        while (stack.size() != 1) {

            int a = stack.pop();
            int b = stack.pop();

            if (arr[a][b] == 1 ? true : false) {
                stack.push(b); // because b is possible candidate
            } else {
                stack.push(a);
            }
        }
        // know we have a potential candidate in our stack
        // again check 2d array for rows and column

        int c = stack.pop();

        for (int i = 0; i < arr.length; i++) {

            if (i != c && (arr[c][i] != 0 || arr[i][c] != 1)) {
                System.out.println("none");
                return;
            }
        }
        System.out.println(c);

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        celebrity_3(arr);
    }
}
