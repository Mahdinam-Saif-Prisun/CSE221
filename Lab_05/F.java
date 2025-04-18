package Lab_05;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dimension = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        char[][] grid = new char[dimension[0]][dimension[1]];
        boolean[][] visited = new boolean[dimension[0]][dimension[1]];
        for(int i = 0; i < dimension[0]; i++) {
            String tempLine = br.readLine();
            for(int j = 0; j < dimension[1]; j++) {
                grid[i][j] = tempLine.charAt(j);
            }
        }
        int maxDiamonds = maxDiamondCounter(grid, visited);
        System.out.println(maxDiamonds);
    }

    public static int diamondCounter (char[][] grid, boolean[][] visited, int row, int col) {
        int rowBound = grid.length;
        int colBound = grid[0].length;
        int diamonds = 0;
        Deque<int[]> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(new int[] {row, col});
        visited[row][col] = true;
        while(!bfsQueue.isEmpty()) {
            int[] position = bfsQueue.remove();
            int[] dRow = {-1, 1, 0, 0};
            int[] dCol = {0, 0, -1, 1};
            for(int dist = 0; dist < 4; dist++) {
                int rowIndex = dRow[dist] + position[0];
                int colIndex = dCol[dist] + position[1];
                if(rowIndex >= 0 && rowIndex < rowBound && colIndex >= 0 && colIndex < colBound) {
                    if(visited[rowIndex][colIndex] == false && grid[rowIndex][colIndex] != '#') {
                        bfsQueue.add(new int[]{rowIndex, colIndex});
                        visited[rowIndex][colIndex] = true;
                    }
                }
            }
            if(grid[position[0]][position[1]] == 'D') {
                diamonds++;
            }
        }
        return diamonds;
    }

    public static int maxDiamondCounter(char[][] grid, boolean [][] visited) {
        int rowBound = grid.length;
        int colBound = grid[0].length;
        int maxDiamonds = 0;
        for(int tempRow = 0; tempRow < rowBound; tempRow++) {
            for(int tempCol = 0; tempCol < colBound; tempCol++) {
                if(visited[tempRow][tempCol] == false && grid[tempRow][tempCol] != '#') {
                    maxDiamonds = Math.max(maxDiamonds, diamondCounter(grid, visited, tempRow, tempCol));
                }
            }
        }
        return maxDiamonds;
    }
}
