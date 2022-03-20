import java.util.ArrayDeque;
import java.util.*;;

public class bfs {

    public static void printBFS(int[][] graph) {

        boolean[] visited = new boolean[graph.length];

        Queue<Integer> q = new ArrayDeque<>();

        for (int j = 0; j < graph.length; j++) {
            
            if(!visited[j])
            {
                q.add(j);
            }

            while (q.size() > 0) {

                int fnt = q.remove();

                System.out.println(fnt);

                for (int i = 0; i < graph.length; i++) {
                    if (graph[fnt][i] == 1 && !visited[i]) {
                        q.add(i);
                        visited[i] = true;
                    }
                }
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

            printBFS(graph);
        }
    }

}
