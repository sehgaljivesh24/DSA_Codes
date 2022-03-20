import java.util.*;

public class getPathDFS {

    public static ArrayList<Integer> getPathByDFS(int[][] graph, int src, int dest, boolean[] visited) {

        // kahain src hi humara dest toh nhi,if yes add it in arrayList and return
        if (src == dest) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(dest);
            return list;
        }
        visited[src] = true;

        // Now we will ask our adjacent vertices that is there any path from them to
        // dest
        // If there is a path then return ArrayList from that vertex to dest and I will
        // add myself in it

        for (int i = 0; i < graph.length; i++) {
            // har vertex se pucha ki mein adjacent huin ya nhi
            if (graph[src][i] == 1 && visited[i] == false) {
                ArrayList<Integer> list = getPathByDFS(graph, i, dest, visited);
                if (list.size() > 0) {
                    list.add(src);
                    return list;
                }
            }
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            int v = scn.nextInt();
            int e = scn.nextInt();

            int[][] graph = new int[v][v];
            for (int i = 0; i < e; i++) {
                int v1 = scn.nextInt();
                int v2 = scn.nextInt();

                // there exists a edge between v1 and v2
                graph[v1][v2] = 1;
                graph[v2][v1] = 1;
            }

            int src = scn.nextInt();
            int dest = scn.nextInt();

            boolean[] visited = new boolean[graph.length];
            ArrayList<Integer> path = getPathByDFS(graph, src, dest, visited); // return arrayList containing path from src
                                                                               // to dest
            System.out.println(path);
        }
    }
}
