import java.util.*;

public class temp {

    public  int[] ngeR(int[] nums2) {

        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[nums2.length];

        for (int i = nums2.length - 1; i >= 0; i--) {
            if (stack.empty()) {
                nge[i] = -1;
            } else if (!stack.empty() && stack.peek() > nums2[i]) {
                nge[i] = stack.peek();
            } else if (!stack.empty() && stack.peek() < nums2[i]) {
                while (!stack.empty() && stack.peek() < nums2[i]) {
                    stack.pop();
                }
                if (stack.empty()) {
                    nge[i] = -1;
                } else if (!stack.empty() && nums2[i] < stack.peek()) {
                    nge[i] = stack.peek();
                }
            }
            stack.push(nums2[i]);
        }
        return nge;
    }

    public  int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // arr1 is subset of arr2
        // which means every element in arr1 is present in arr2

        // for each element in arr1 we need to find it in arr2
        // and then for that element we need to find nge in arr2

        // This is can be done by :->
        // 1. first finding nge to right for arr2
        // 2. Then using hashmap to assign every element's nge
        // 3. Then iterating over arr1 and finding required result

        int[] nge = ngeR(nums2);
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            hm.put(nums2[i], nge[i]);
        }

        int[] nge1 = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            nge1[i] = hm.get(nums1[i]);
        }

        return nge1;
    }

    public static void main(String[] args){

        int[] num1  = {4,1,2};
        int[] num2  = {1,3,4,2};

        int[] nge = nextGreaterElement(num1,num2);

        for(int val : nge){
            System.out.println(val);
        }

    }
}