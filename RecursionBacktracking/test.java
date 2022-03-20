import java.util.*;
public class test{
  public static void main(String[] args)
  {
     Scanner scn = new Scanner(System.in);
     int n = scn.nextInt();     // takes size of array
     int[] arr = new int[n];     // declare an array
     for(int i = 0;i<arr.length;i++)
     {
        arr[i] = scn.nextInt();
     }

     int[] arr_ans = answer(arr);   //calling the fn which will solve our problem
     for(int i =0;i<arr_ans.length;i++)
{
System.out.print(arr_ans[i] + " ");
}
     
  }
  
  public static int[] answer(int[] arr)
 {
     int[] new_arr = new int[arr.length];
    
     int mult = 1;
     for(int i = 0;i<arr.length;i++)
     {
        mult = mult*arr[i];
     }
     for(int i = 0;i<arr.length;i++)
     {
         new_arr[i] = mult/arr[i];
     }

     return new_arr;
 }
}