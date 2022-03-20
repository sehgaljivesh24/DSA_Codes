import java.util.*;

public class getPathBFS {

    public static ArrayList<Integer> getPathByBFS(int[][] graph, int src, int dest) {

        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> hm = new HashMap<>();

        boolean[] visited = new boolean[graph.length];

        queue.add(src);
        visited[src] = true;

        boolean found = false;
        while (queue.size() > 0) {
            int front = queue.remove();
            if (front == dest) {
                found = true;
                break;
            }

            for (int i = 0; i < graph.length; i++) {
                if (graph[front][i] == 1 && visited[i] == false) {
                    queue.add(i);
                    hm.put(i, front);
                    visited[i] = true;
                }
            }
        }
        if (found == true) {
            ArrayList<Integer> list = new ArrayList<>();

            list.add(dest);
            while (hm.containsKey(dest)) {
                int val = hm.get(dest);
                list.add(val);
                dest = val;
            }
            return list;

        } else {
            return new ArrayList<>();
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

                // edge is formed by v1 and v2

                graph[v1][v2] = 1;
                graph[v2][v1] = 1;
            }
            int src = scn.nextInt();
            int dest = scn.nextInt();

            ArrayList<Integer> list = getPathByBFS(graph, src, dest);
            System.out.println(list);
        }
    }

}
