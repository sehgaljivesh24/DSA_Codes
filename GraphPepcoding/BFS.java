import java.util.*;

public class BFS {

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
        String psf;

        Pair(int src, String psf) {
            this.vtx = src;
            this.psf = psf;
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

        int src = scn.nextInt();
        boolean[] visited = new boolean[v];

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(src, src + ""));

        while (queue.size() > 0) {
            // remove , mark* , work , add nbr's*

            Pair rem = queue.remove();
            if (visited[rem.vtx] == true) {
                continue;
            }

            visited[rem.vtx] = true;
            System.out.println(rem.vtx + "@" + rem.psf);

            for (Edge edge : graph[rem.vtx]) {
                if (visited[edge.nbr] == false) {
                    queue.add(new Pair(edge.nbr, rem.psf + edge.nbr));
                }
            }

        }
    }
}
