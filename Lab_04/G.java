import java.util.ArrayList;
import java.util.Scanner;

public class G {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfNodes = sc.nextInt(), numOfQueries = sc.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[numOfNodes + 1];
        for (int i = 0; i <= numOfNodes; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        int result = -1;
        for (int i = 0; i < numOfQueries; i++) {
            int node = sc.nextInt(), query = sc.nextInt();
            if(graph[node].isEmpty()) {
                int[] sieve = new int[numOfNodes + 1];
                for(int j = 1; j <= numOfNodes; j++) {
                    if(j == node) {
                        continue;
                    }
                    if(sieve[j] == 1) {
                        continue;
                    }
                    if(gcd(node, j) != 1) {
                        for(int k = 0; k <= numOfNodes; k += j) {
                            sieve[k] = 1;
                        }
                    }
                    else {
                        graph[node].add(j);
                    }
                }
            }
            if(query > graph[node].size()) {
                result = -1;
            }
            else {
                result = graph[node].get(query - 1);
            }
            System.out.println(result);
        }
    }

    public static int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}