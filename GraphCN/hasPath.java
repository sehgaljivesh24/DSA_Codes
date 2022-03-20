import java.util.*;

public class hasPath {

    public static boolean hasPathh(int[][] graph, int src, int dest, boolean[] visited) {
        // kahain mein hi toh vertex nhi jahain jana hai
        if (src == dest) {
            return true;
        }

        visited[src] = true;
        // Now we will ask our adjacent vertices that is there any path from them to
        // destination
        for (int i = 0; i < graph.length; i++) {

            if (graph[src][i] == 1 && visited[i] == false) {
                boolean check = hasPathh(graph, i, dest, visited);

                // If there exists a path then return true
                if (check) {
                    return true;
                }
            }

        }

        return false;
    }

    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            int v = scn.nextInt();
            int e = scn.nextInt();

            int[][] graph = new int[v][v];

            for (int i = 0; i < e; i++) {
                int v1 = scn.nextInt();
                int v2 = scn.nextInt();

                graph[v1][v2] = 1;
                graph[v2][v1] = 1;
            }

            int src = scn.nextInt();
            int dest = scn.nextInt();

            boolean[] visited = new boolean[graph.length];
            System.out.println(hasPathh(graph, src, dest, visited));
        }
    }
}
