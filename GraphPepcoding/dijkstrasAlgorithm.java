
import java.util.*;
import java.io.*;

// this Algorithm is used to find shortest path in terms of weight between 2 vertices
// We use Priority Queue to implement this algo 
// this is bcoz every time Priority Queue gives priority to path which has minimum weight

public class dijkstrasAlgorithm {

    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static class Pair implements Comparable<Pair> {

        int vtx;
        String path;
        int wsf;

        Pair(int vtx, String path, int wsf) {
            this.vtx = vtx;
            this.path = path;
            this.wsf = wsf;
        }

        public int compareTo(Pair o) {
            return this.wsf - o.wsf;
        }
    }

    public static void dijkstrasAlgo(ArrayList<Edge>[] graph, int src) {

        boolean[] visited = new boolean[graph.length];
        Pair[] info = new Pair[graph.length];

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, src + "", 0));

        while (pq.size() > 0) {
            // remove, mark*, work, add nbr's*

            Pair rem = pq.remove();

            info[rem.vtx] = rem;

            if (visited[rem.vtx] == true) {
                continue;
            }
            visited[rem.vtx] = true;

            System.out.println(rem.vtx + " via " + rem.path + " @ " + rem.wsf);

            for (Edge edge : graph[rem.vtx]) {
                if (visited[edge.nbr] == false) {
                    pq.add(new Pair(edge.nbr, rem.path + edge.nbr, rem.wsf + edge.wt));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int v = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        int src = Integer.parseInt(br.readLine());

        dijkstrasAlgo(graph, src);
    }
}