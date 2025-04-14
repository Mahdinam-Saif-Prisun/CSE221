package Lab_05;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tempInitValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int numOfVertices = tempInitValues[0], numOfEdges = tempInitValues[1];
        int[] cityArr_1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int[] cityArr_2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();

        vertex[] graph = new vertex[numOfVertices + 1];
        for(int i = 1; i <= numOfVertices; i++) {
            graph[i] = new vertex();
        }
        for(int i = 1; i <= numOfEdges; i++) {
            int city_1 = cityArr_1[i - 1], city_2 = cityArr_2[i - 1];
            graph[city_1].edges.add(city_2);
            graph[city_2].edges.add(city_1);
        }

        ArrayList<String> order = DFS(graph, 1, new ArrayList<>());
        String result = String.join(" ", order);
        System.out.println(result);
    }

    public static class vertex {
        int color = 0;
        ArrayList<Integer> edges = new ArrayList<>();
    }

    public static ArrayList<String> DFS (vertex[] graph, int source, ArrayList<String> order) {
        graph[source].color = 1;
        order.add(Integer.toString(source));
        
        for(int destination : graph[source].edges) {
            if(graph[destination].color == 0) {
                order = (DFS(graph, destination, order));
            }
        }
        return order;
    }
}
