import java.util.*;

public class input {

    // class/blueprint to store details of an edge
    public static class Edge {

        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static boolean hasPath(ArrayList<Edge>[] graph,int src,int dest,boolean[] visited)
    {
        if(src == dest)
        {
            return true;
        }
        visited[src] = true;

        // src vertex will ask its adjacent vertices that is there any path between u and dest
        // If Yes then there exists a path between me and dest as I can travel to my adjacent vertex

        // src will ask from all nbr 
        for(Edge edge : graph[src])
        {
            if(visited[edge.nbr] == false)
            {
                boolean check = hasPath(graph, edge.nbr, dest,visited);
                if(check)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int v = scn.nextInt();
        int e = scn.nextInt();

        // we will make an array of vertices , where each vertex contains arrayList containing edges connecting it
        ArrayList<Edge>[] graph = new ArrayList[v];
        // size of array will be equal to number of vertices as for each vertex we need to store all edges connecting it

        // By default for each vertex there is null stored
        // We need to initialize each vertex with empty ArrayList
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Now 'e' no of edges are given as input
        for (int i = 0; i < e; i++) {
            // every edge will contain 2 vertices and also weight of the edge
            int v1 = scn.nextInt();
            int v2 = scn.nextInt();
            int wt = scn.nextInt();

            // hum v1 vertex pr gye aur vahain padi ArrayList mein edge daaldi which is from v1(src) to v2(nbr)
            graph[v1].add(new Edge(v1, v2, wt));

            // ab hum v2 vertex pr gye aur vahain padi arrayList mein edge daaldi which is from v2(src) to v1(nbr)
            graph[v2].add(new Edge(v2, v1, wt));
            // v2 se v1 ja rhi Edge

        }

        int src = scn.nextInt();
        int dest = scn.nextInt();

        boolean[] visited = new boolean[graph.length];
        System.out.println(hasPath(graph, src, dest,visited));
    }
}