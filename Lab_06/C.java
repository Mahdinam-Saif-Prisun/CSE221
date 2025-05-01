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
        int source_x = init_x;
        int source_y = init_y;
        int[][][] visited = new int[boardSize][boardSize][3];
        int[][] moveset = new int[][] {{1, 2}, {2, 1}, {-1, -2}, {-2, -1}, {1, -2}, {-2, 1}, {-1, 2}, {2, -1}};
        int moves = 0;

        //Arithmetic approximation
        if(Math.abs(init_x - destination_x) > 8 || Math.abs(init_y - destination_y) > 8) {
            if(Math.abs(init_x - destination_x) > Math.abs(init_y - destination_y)) {
                moves = (Math.abs(init_x - destination_x) / 2);
                source_x = init_x + ((destination_x - init_x) / Math.abs(destination_x - init_x)) * 2 * moves;
                source_y = init_y + ((destination_y - init_y) / Math.abs(destination_y - init_y)) * (moves % Math.abs(destination_y - init_y));
            }
            else {
                moves = (Math.abs(init_y - destination_y) / 2);
                source_y = init_y + ((destination_y - init_y) / Math.abs(destination_y - init_y)) * 2 * moves;
                source_x = init_x + ((destination_x - init_x) / Math.abs(destination_x - init_x)) * (moves % Math.abs(destination_x - init_x));
            }
        }

        // After approximation, we use BFS for the smaller scale part
        Queue<int[]> bfsQ = new LinkedList<>();
        visited[source_x - 1][source_y - 1][0] = 1;  // Mark the initial position as visited
        bfsQ.add(new int[] {source_x, source_y, 0});  // Add the start position with step count 0

        // Modified BFS loop to explore the grid
        while (!bfsQ.isEmpty()) {
            int[] current = bfsQ.remove();
            int current_x = current[0];
            int current_y = current[1];
            int steps = current[2];

            // If we reached the destination, break
            if (current_x == destination_x && current_y == destination_y) {
                break;
            }

            // Explore all possible moves
            for (int[] move : moveset) {
                int temp_x = current_x + move[0];
                int temp_y = current_y + move[1];

                // Check if the new position is within bounds
                if (temp_x > 0 && temp_x <= boardSize && temp_y > 0 && temp_y <= boardSize) {
                    // If the cell hasn't been visited yet, mark it as visited and add it to the queue
                    if (visited[temp_x - 1][temp_y - 1][0] == 0) {
                        visited[temp_x - 1][temp_y - 1] = new int[] {current_x, current_y, steps + 1};  // Record parent and steps
                        bfsQ.add(new int[] {temp_x, temp_y, steps + 1});  // Add the new position with incremented steps
                    }
                }
            }
        }

        //StepCounter
        if(visited[destination_x - 1][destination_y - 1][0] == 0) {
            moves = -1;
        }
        else {
            moves += visited[destination_x - 1][destination_y - 1][2];
        }

        System.out.println(moves);
    }
}