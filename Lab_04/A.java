import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        String[] tempInp = br.readLine().split(" ");
        int vertices= Integer.parseInt(tempInp[0]), edges = Integer.parseInt(tempInp[1]);
        int[][] adjMatrix = new int[vertices][vertices];

        for(int i = 0; i < edges; i++) {
            tempInp = br.readLine().split(" ");
            int mainNode = Integer.parseInt(tempInp[0]);
            int adjacentNode = Integer.parseInt(tempInp[1]);
            int weight = Integer.parseInt(tempInp[2]);
            adjMatrix[mainNode - 1][adjacentNode - 1] = weight;
        }

        for(int i = 0; i < vertices; i++) {
            for(int j = 0; j < vertices; j++) {
                sb.append(adjMatrix[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
