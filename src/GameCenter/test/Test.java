package GameCenter.test;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] temp = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        Random r = new Random();

        for(int i = 0;i < temp.length; i++){
            int index = r.nextInt(temp.length);
            int t = temp[i];
            temp[i] = temp[index];
            temp[index] = t;

        }

        int [][] data = new int[4][4];

        for(int i = 0;i < temp.length; i++){
            data[i / 4][i % 4] = temp[i];
        }

        for (int i = 0; i < temp.length; i++) {
            System.out.print(temp[i] + " ");
        }

        System.out.println();

        for(int i = 0;i < 4;i ++) {
            for (int j = 0; j < 4; j++)
                System.out.printf("%d ", data[i][j]);
            System.out.println();
        }
    }

}
