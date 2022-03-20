
import java.io.*;
import java.util.*;

public class countIslands {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[m][n];

        for (int i = 0; i < arr.length; i++) {
            String parts = br.readLine();
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(parts.split(" ")[j]);
            }
        }

        int count = 0;
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        // We need to traverse in a 2d Array , where each cell is a vertex
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {

                if (arr[i][j] == 0 && visited[i][j] == false) // means that vertex is a land and has not been visited
                {
                    // visited is false only when it has not been visited before and is in different component
                    count = count + 1;
                    DFSMark(arr, i, j, visited);
                }
            }
        }
        System.out.println(count);
    }

    // Basically this function help us to keep a track of all vertex which are visited
    public static void DFSMark(int[][] arr, int i, int j, boolean[][] visited) {

        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] == 1 || visited[i][j] == true) {

            // First 4 cond'n helps to keep in boundaries
            // 2nd last cond'n checks that only land gets marked as visited
            // Last check if it has already been visited or not
            return;
        }

        visited[i][j] = true;

        // We have marked the current cell as visited
        // Also we need to mark all the cells which are land and are adjacent to it as visited
        // This is because they are part of 1 island or 1 component

        DFSMark(arr, i - 1, j, visited);
        DFSMark(arr, i + 1, j, visited);
        DFSMark(arr, i, j + 1, visited);
        DFSMark(arr, i, j - 1, visited);
    }

}