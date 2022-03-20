import java.io.*;
import java.util.*;

public class graphHasPath {
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

  public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
    if (src == dest) {
      return true;
    }
    visited[src] = true;

    // We will faith on fn that it will check if there is a path from our neighbour/adjacent vertices
    // for a src vertex Information of its nbr lies in edge's present in ArrayList corresponding to that vertex
    for (Edge edge : graph[src]) {
      // graph[src] will return arrayList corresponding to that vertex one by one we will check with all edges
      
      if (visited[edge.nbr] == false) {
        boolean check = hasPath(graph, edge.nbr, dest, visited);
        // If yes then there is a path from src too
        if (check) {
          return true;
        }
      }

    }

    return false;
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
    int dest = Integer.parseInt(br.readLine());

    boolean[] visited = new boolean[v];
    System.out.println(hasPath(graph, src, dest, visited));

  }

}