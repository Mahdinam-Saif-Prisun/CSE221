package Lab_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int boardSize = Integer.parseInt(br.readLine());
        int[] tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();

        //Shifting to 0-th indexing
        int init_x = tempValues[0] - 1, init_y = tempValues[1] - 1, fin_x = tempValues[2] - 1, fin_y = tempValues[3] - 1;

        //Necessary arrays
        int[][] moveSet = new int[][] {{1, 2}, {2, 1}, {-1, 2}, {2, -1}, {1, -2}, {-2, 1}, {-1, -2}, {-2, -1}};
        boolean[][] visited = new boolean[boardSize][boardSize];
        int[][] steps = new int[boardSize][boardSize];

        //BFS approach
        Queue<int[]> bfsq = new LinkedList<>();
        bfsq.add(new int[] {init_x, init_y});
        visited[init_x][init_y] = true;
        
        //bfs Loop
        while(!bfsq.isEmpty()) {
            int[] curr_coord = bfsq.remove();
            int curr_x = curr_coord[0], curr_y = curr_coord[1];
            int numOfMoves = steps[curr_x][curr_y];
            for(int[] moves : moveSet) {
                int temp_x = curr_x + moves[0];
                int temp_y = curr_y + moves[1];

                //Pruning and enqueueing
                if((temp_x >= 0 && temp_y >= 0 && temp_x < boardSize && temp_y < boardSize) && (!visited[temp_x][temp_y])) {
                    visited[temp_x][temp_y] = true;
                    steps[temp_x][temp_y] = numOfMoves + 1;
                    bfsq.add(new int[] {temp_x, temp_y});
                }
            }
        }

        //Finding out result
        int result = steps[fin_x][fin_y];
        if(!visited[fin_x][fin_y]) {
            result = -1;
        }

        //Output
        System.out.println(result);
    }
}