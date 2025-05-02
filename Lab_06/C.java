package Lab_06;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int boardSize = sc.nextInt();
        int init_x = sc.nextInt();
        int init_y = sc.nextInt();
        int destination_x = sc.nextInt();
        int destination_y = sc.nextInt();

        // --- Start Timer After Inputs ---
        long startTime = System.nanoTime();

        int[][][] visited = new int[boardSize][boardSize][3];
        int[][] moveset = new int[][] {{1, 2}, {2, 1}, {-1, -2}, {-2, -1}, {1, -2}, {-2, 1}, {-1, 2}, {2, -1}};
        int moves;

        //Modified BFS
        Queue<int[]> bfsQ = new LinkedList<>();
        visited[init_x - 1][init_y - 1] = new int[] {-1, -1, 0};
        bfsQ.add(new int[] {init_x, init_y, 0});
        while(!bfsQ.isEmpty()) {
            int[] current = bfsQ.remove();
            if(current[0] == destination_x && current[1] == destination_y) {
                break;
            }
            for(int[] movingPos : moveset) {
                int temp_x = current[0] + movingPos[0];
                int temp_y = current[1] + movingPos[1];
                if((temp_x > 0 && temp_x <= boardSize) && (temp_y > 0 && temp_y <= boardSize)) {
                    if(((Math.abs(destination_x - current[0]) >= Math.abs(destination_x - temp_x) - 3) || (Math.abs(destination_y - current[1]) >= Math.abs(destination_y - temp_y) - 3))) {
                        if(visited[temp_x - 1][temp_y - 1][0] == 0) {
                            visited[temp_x - 1][temp_y - 1] = new int[] {current[0], current[1], current[2] + 1};
                            bfsQ.add(new int[] {temp_x, temp_y, current[2] + 1});
                        }
                    }
                }
            }
        }

        //StepCounter
        if(visited[destination_x - 1][destination_y - 1][0] == 0) {
            moves = -1;
        }
        else {
            moves = visited[destination_x - 1][destination_y - 1][2];
        }

        System.out.println(moves);


        long endTime = System.nanoTime();

        // --- Output Runtime in Milliseconds ---
        double runtimeMs = (endTime - startTime) / 1e6;
        System.out.printf("Runtime: %.3f ms\n", runtimeMs);
    }
}