import java.io.*;
import java.util.*;

public class iterativePreInPostOrderTraversal {
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

  public static void iterativePrePostInTraversal(Node node) {
    
    // PreOrder Starts
    Stack<Node> stack = new Stack<>();
    stack.push(node);
    while(stack.size() > 0 )
    {
        Node topNode = stack.pop();
        System.out.print(topNode.data + " ");

        if(topNode.right != null)   // If right child is present , push it in stack
        {
            stack.push(topNode.right);
        }
        if(topNode.left != null)   // If left child is present , push it in stack
        {
            stack.push(topNode.left);
        }
    }
    System.out.println();


    // InOrder Starts
    Stack<Pair> pstack = new Stack<>();
    Pair rpair = new Pair(node, 1);
    pstack.push(rpair);
    
    while(pstack.size() > 0)
    {
        Pair peekp = pstack.peek();

        if(peekp.state == 1)
        {
            if(peekp.node.left != null)  // agar uss pair ke node ka left child hai toh stack mein push krdo
            {
                Pair temp = new Pair(peekp.node.left, 1);   
                pstack.push(temp);
            }
            peekp.state++;
        }
        else if(peekp.state == 2)
        {
            System.out.print(peekp.node.data + " ");
            peekp.state++;
        }
        else
        {
            pstack.pop();
            if(peekp.node.right != null)  // agar uss pair ke node ka right child hai toh stack mein push krdo
            {
                Pair temp = new Pair(peekp.node.right, 1);
                pstack.push(temp);
            }
        }
    }
    System.out.println();

    // Post Order
    pstack = new Stack<>();
    Pair rp = new Pair(node,1);   // rp is root pair
    pstack.push(rp);

    while(pstack.size() > 0)
    {
        Pair topPair = pstack.peek();
        
        if(topPair.state == 1)   // Turn to push Left child of Node
        {
            if(topPair.node.left != null)
            {
                Pair cp = new Pair(topPair.node.left,1);  // cp = child pair
                pstack.push(cp);
            }
            topPair.state++;
        }
        else if(topPair.state == 2)  // Turn to push Right child of Node
        {
          if(topPair.node.right != null)
          {
              Pair cp = new Pair(topPair.node.right,1);  // cp = child pair
              pstack.push(cp);
          }
          topPair.state++;
        }
        else
        {
          System.out.print(topPair.node.data + " ");
          pstack.pop();
        }
    }
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
    iterativePrePostInTraversal(root);
  }

}