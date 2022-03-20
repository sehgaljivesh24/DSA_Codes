import java.util.*;

public class numberOfStudentsUnableToEatLunch {
    public static int countStudents(int[] students, int[] sandwiches) {

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < students.length; i++) {
            queue.add(students[i]);
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = sandwiches.length - 1; i >= 0; i--) {
            stack.push(sandwiches[i]);
        }/*
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }*/
        
        int count = 0;
        // keeps track ki jitni baar queue mein bnde vapis peeche gye
        // kya saare logoin ne sandwich nhi khana tha
        // if yes then break
        while (!stack.empty() && count != queue.size()) {

            if (stack.peek() == queue.peek()) {
                stack.pop();
                queue.remove();

                // count reset only when there is someone to eat top of stack
                if (count != 0) { // ab aisa koi nhi jisne khana nhi sandwich
                    count = 0;
                }
            } else {
                int val = queue.remove();
                queue.add(val);
                count++; // increase the count who doesnt want to eat
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int[] arr1 = { 1, 1, 1, 0, 0, 1 };
        int[] arr2 = { 1, 0, 0, 0, 1, 1 };

        System.out.println(countStudents(arr1, arr2));
    }
}