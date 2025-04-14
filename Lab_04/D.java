import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tempIn = br.readLine().split(" ");
        int numOfNodes = Integer.parseInt(tempIn[0]);
        int numOfEdges = Integer.parseInt(tempIn[1]);
        int oddCounter = 0;
        String[] u = br.readLine().split(" ");
        String[] v = br.readLine().split(" ");
        int[] degreeCounter = new int[numOfNodes];
        for (int i = 0; i < numOfEdges; i++) {
            degreeCounter[Integer.parseInt(u[i]) - 1]++;
            degreeCounter[Integer.parseInt(v[i]) - 1]++;
        }
        for(int degree : degreeCounter) {
            if(degree % 2 != 0) {
                oddCounter++;
            }
        }
        if(oddCounter == 0 || oddCounter == 2) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
}