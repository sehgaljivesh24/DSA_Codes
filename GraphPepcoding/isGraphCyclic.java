import java.io.*;
import java.util.*;

public class isGraphCyclic {
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

    public static boolean isCyclic(ArrayList<Edge>[] graph) {

        Queue<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == false) {
                queue.add(i);
                
                while (queue.size() > 0) {
                    // remove , mark* , work , add nbr's*

                    // remove the front of queue
                    int vtx = queue.remove();

                    // check if it is already present or not
                    if (visited[vtx] == true) // if yes then there is a cycle as there are more than 1 way to reach a vtx
                    {
                        return true;
                    }
                    visited[vtx] = true;

                    // no work

                    // add nbr's in queue
                    for (Edge edge : graph[vtx]) {
                        if (visited[edge.nbr] == false) {
                            queue.add(edge.nbr);
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
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
        System.out.println(isCyclic(graph));
    }
}
