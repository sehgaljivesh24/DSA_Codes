import java.util.*;

public class spreadOfInfection {

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

    public static class Pair {

        int vtx;
        int toi; // time of infection -> toi

        Pair(int vtx, int toi) {
            this.vtx = vtx;
            this.toi = toi;
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int v = scn.nextInt();
        int e = scn.nextInt();

        ArrayList<Edge>[] graph = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            int v1 = scn.nextInt();
            int v2 = scn.nextInt();
            int wt = scn.nextInt();

            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        int soi = scn.nextInt();  // soi -> src of infection
        int time = scn.nextInt(); // for how much time we need to spread infection

        int count = 0;

        boolean[] visited = new boolean[v];
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(soi, 1));

        while(queue.size() > 0)
        {
            // remove , mark* , work , add nbr's*
            Pair prem = queue.remove();

            // mark
            if(visited[prem.vtx] == true)
            {
                continue;
            }
            if(prem.toi > time)
            {
                break;
            }
            visited[prem.vtx] = true;

            // work
            count++;

            // add nbr's*
            for(Edge edge : graph[prem.vtx])
            {
                if(visited[edge.nbr] == false)
                {
                    queue.add(new Pair(edge.nbr, prem.toi + 1));
                }
            }
        }
        /*
        boolean[] visited = new boolean[v];
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(soi, 1));
        visited[soi] = true;
        count++;

        while(queue.peek().toi != time)
        {
            // remove , mark , work , add nbr's
            Pair rem = queue.remove();
            
            for(Edge edge : graph[rem.vtx])
            {
                if(visited[edge.nbr] == false)
                {
                    queue.add(new Pair(edge.nbr, rem.toi + 1));
                    visited[edge.nbr] = true;
                    count++;
                }
            }
        }*/
        System.out.println(count);
    }
}
