import java.util.*;
public class temp {

    public static class Edge{

        int src;
        int nbr;
        int wt;

        Edge(int src,int nbr,int wt)
        {
            this.src = src;
            this.nbr = nbr;
            this.wt  = wt;
        }
    }
    public static void main(String[] args)
    {
        Scanner scn = new Scanner(System.in);

        int v = scn.nextInt();
        int e = scn.nextInt();

        // Adjacency list is array of vertices , every index represent a vertex 
        // and each vertex has a ArrayList corresponding to it , which contains edges which are connected to it
        ArrayList<Edge>[] graph = new ArrayList[v];
    
        // By default there is null placed at every index in our array
        // we need to initialize each index/vertex by empty arrayList
        for(int i = 0; i < graph.length;i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0;i<e;i++)
        {
            int v1 = scn.nextInt();
            int v2 = scn.nextInt();
            int wt = scn.nextInt();

            // we will put edge between v1 and v2 in both Vertices's ArrayList
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }     
    }
    
}
