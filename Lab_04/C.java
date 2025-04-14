import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int numOfNodes = Integer.parseInt(br.readLine());
        int[][] matrix = new int[numOfNodes][numOfNodes];
        for(int i = 0; i < numOfNodes; i++) {
            String[] adjacentNodes = br.readLine().split(" ");
            for (int j = 1; j < adjacentNodes.length; j++) {
                matrix[i][Integer.parseInt(adjacentNodes[j])] = 1;
            }
        }
        for(int[] row : matrix) {
            for(int value : row) {
                sb.append(value);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
