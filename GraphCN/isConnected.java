import java.util.*;

public class isConnected {

    public static boolean isGraphConnected(int[][] graph) {

        // tell us that which all vertices are visited
        boolean[] visited = new boolean[graph.length];

        // we will traverse in whole graph and those vertex which are visited ae marked
        // true
        traversal(graph, 0, visited);

        // we will check in visited array if there is any vertex that is not visited
        for (int i = 0; i < visited.length; i++) {
            // if any vertex not visited hence return false
            if (visited[i] == false) {
                return false;
            }
        }

        return true;
    }

    public static void traversal(int[][] graph, int sv, boolean[] visited) {

        // mark that particular vertex true as visited
        visited[sv] = true;

        for (int i = 0; i < graph.length; i++) {

            if (graph[sv][i] == 1 && visited[i] == false) {
                traversal(graph, i, visited);
            }
        }
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

            System.out.println(isGraphConnected(graph));
        }
    }

}
