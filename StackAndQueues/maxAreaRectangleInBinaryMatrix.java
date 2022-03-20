import java.util.*;

public class maxAreaRectangleInBinaryMatrix {

    
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int row = scn.nextInt();
        int column = scn.nextInt();

        int[][] arr = new int[row][column];

        int[][] expHorz = null;
        int[][] expVert = null;

        int[][] area = new int[row][column];
        for (int i = 0; i < expHorz.length; i++) {
            for (int j = 0; j < expHorz[0].length; j++) {
                if (expHorz[i][j] != 0 && expVert[i][j] != 0) {
                    area[i][j] = expHorz[i][j] * expVert[i][j];
                }
            }
        }

        int maxArea = 0;
        for (int i = 0; i < area.length; i++) {
            int min = area[i][0];
            for (int j = 1; j < area.length; j++) {
                if(min> area[i][j]){
                    min = area[i][j];
                }
            }

            maxArea = Math.max(maxArea, min);
        }
        System.out.println(maxArea);
    }

}
