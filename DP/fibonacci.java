//import java.util.*;

public class fibonacci{

    
    public static int fibR(int n){

        if(n == 0 || n == 1){
            return n;
        }

        return fibR(n-1) + fibR(n-2);
    }
    public static void main(String[] args){

        int n = 44;
        // System.out.println(fib(n));
        System.out.println(fibR(n));
    }
}