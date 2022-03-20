// we divide graph's vertices into 2 independent sets where vertex of each set are connected
// but vertex in a set are not connected

// This means if we divide our graph using 2 colors , vertices in a set will be of same colour
// if a graph has even cycles then it can be coloured
// if a graph has odd cycles then it can not be colored

// ******  nbr of a vtx should have different color

import java.io.*;
import java.util.*;

public class isGraphBipartite {
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

   public static class Pair {

      int vtx;
      char col;

      Pair(int vtx, char col) {
         this.vtx = vtx;
         this.col = col;
      }
   }

   public static boolean isBipartite(ArrayList<Edge>[] graph) {

      char[] color = new char[graph.length];
      boolean[] visited = new boolean[graph.length];
      Queue<Pair> queue = new ArrayDeque<>();

      

      for (int i = 0; i < graph.length; i++) {

         if (visited[i] == false) {

            queue.add(new Pair(i, 'R'));
            while (queue.size() > 0) {
               // remove , mark* , work , add nbr's*

               Pair rem = queue.remove(); // remove

               if (visited[rem.vtx] == true) { // mark*

                  if (color[rem.vtx] != rem.col) { // we check the color of vtx which has occurred before
                     return false;
                  } else {
                     continue;
                  }
               }

               visited[rem.vtx] = true;

               color[rem.vtx] = rem.col; // work

               for (Edge edge : graph[rem.vtx]) { // add nbr's

                  char col = (rem.col == 'R' ? 'B' : 'R');

                  if (visited[edge.nbr] == false) {
                     queue.add(new Pair(edge.nbr, col));
                  }
               }
            }
         }
      }

      return true;
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

      System.out.println(isBipartite(graph));
   }
}