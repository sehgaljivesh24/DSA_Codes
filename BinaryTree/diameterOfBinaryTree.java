import java.io.*;
import java.util.*;

public class diameterOfBinaryTree {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  public static int height(Node node) {
    if (node == null) {
      return -1;
    }

    int lh = height(node.left);
    int rh = height(node.right);

    int th = Math.max(lh, rh) + 1;
    return th;
  }

  // Diameter of Tree is farthest distance between 2 nodes in terms of edges

  // Diameter1 tells us Diameter of Tree in O(n2)
  // This is because for every node we calculate Height of its Left and Right
  // SubTree which is O(n) operation
  // Hence for n nodes each has time complexity O(n)
  // It sums up the Complexity to O(n2)
  public static int diameter1(Node node) {

    if (node == null) {
      return 0;
    }

    // Possibility 1 is that diameter of Tree lies on Left SubTree (tree of Left
    // Child)
    // It means there are 2 nodes in left SubTree that are farthest from each other
    // hence forming diameter
    int ld = diameter1(node.left);

    // Possibility 2 is that diameter of Tree lies on Right SubTree (tree of Right
    // Child)
    // It means that there are 2 nodes on Right Subtree that are farthest from each
    // other and hence forming diameter
    int rd = diameter1(node.right);

    // Possibility 3 is that there is one node that is at Left Subtree and one node
    // that is at Right SubTree
    // Hence there are depicting Height of Left and Right SubTree respectively
    int f = height(node.left) + height(node.right) + 2;

    // For Diameter of Tree , We will return the max of All
    return Math.max(f, Math.max(ld, rd));
  }

  // Diameter2 tells us Diameter of Tree in O(n)
  // In Diameter1 we had faith on fn that it will tell use Diameter of Left and
  // Right Subtree and then after that we calculated height for them
  // In this we will have faith on fn that It will tell us Both Diameter and
  // Height of L&R SubTree
  // Hence us Time
  // This will be achieved by returning Object which has Both diameter and Height of that Tree
  
  public static class DiaPair {  // Class contains 2 data members
    int h;
    int dia;
  }

  public static DiaPair diameter2(Node node) {
    if (node == null) {
      DiaPair p = new DiaPair();
      p.h = -1;
      p.dia = 0;
      return p;
    }

    DiaPair p1 = diameter2(node.left);
    DiaPair p2 = diameter2(node.right);

    DiaPair rootp = new DiaPair();
    rootp.h = Math.max(p1.h, p2.h) + 1;

    int f = p1.h + p2.h + 2;
    rootp.dia = Math.max(f, Math.max(p1.dia, p2.dia));

    return rootp;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    Node root = construct(arr);

    DiaPair pair = diameter2(root);
    System.out.println(pair.dia);
  }

}