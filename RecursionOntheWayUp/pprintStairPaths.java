import java.util.*;

public class pprintStairPaths {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        printStairPaths(n, "");
    }

    public static void printStairPaths(int n, String path) {
        if(n == 0)
        {
            System.out.println(path);
            return;
        }
        else if( n < 0)
        {
            return;
        }

        // If I took 1 jump
        printStairPaths(n-1, path + "1");
        // If I took 2 jump
        printStairPaths(n-2, path + "2");
        // If I took 3 jump
        printStairPaths(n-3, path + "3");
    }

}