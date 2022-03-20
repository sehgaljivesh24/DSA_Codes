import java.util.*;

public class mergeOverlappingInterval {

    public static class Pair implements Comparable<Pair>{
        int st;
        int et;

        Pair(int st, int et) {
            this.st = st;
            this.et = et;
        }

        public int compareTo(Pair o) {
            if (this.st != o.st) {
                return this.st - o.st;
            } else {
                return this.et - o.et;
            }
        }
    }

    public static void mergeMeetings(Pair[] arr) {

        ArrayList<Pair> al = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] != null) {
                        if (arr[i].st > arr[j].st && arr[i].st <= arr[j].et) {
                            arr[i].st = arr[j].st;

                            if (arr[i].et < arr[j].et) {
                                arr[i].et = arr[j].et;
                            }
                            arr[j] = null;
                        } else if (arr[j].st >= arr[i].st && arr[j].st <= arr[i].et) {

                            if (arr[i].et < arr[j].et) {
                                arr[i].et = arr[j].et;
                            }
                            arr[j] = null;
                        }
                    }
                }
                al.add(arr[i]);
            }
        }
        for (Pair p : al) {
            System.out.println(p.st + " " + p.et);
        }
    }

    public static void merge(Pair[] arr) {

        Arrays.sort(arr);

        Stack<Pair> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {

            if(i == 0){
                stack.push(arr[i]);
                continue;
            }

            if (arr[i].st > stack.peek().et) {
                stack.push(arr[i]);
            } else {

                Pair p = stack.peek();
                p.et = Math.max(p.et, arr[i].et);
            }
        }
        Stack<Pair> res = new Stack<>();
        while (stack.size() > 0) {
            Pair p = stack.pop();
            res.push(p);
        }

        while (res.size() > 0) {
            Pair p = res.pop();
            System.out.println(p.st + " " + p.et);
        }
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        Pair[] arr = new Pair[n];

        for (int i = 0; i < n; i++) {
            int st = scn.nextInt();
            int et = scn.nextInt();

            Pair p = new Pair(st, et);
            arr[i] = p;
        }
        merge(arr);
    }

}
