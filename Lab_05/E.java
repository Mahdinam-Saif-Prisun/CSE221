package Lab_05;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tempInitValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int numOfVertices = tempInitValues[0], numOfEdges = tempInitValues[1];

        //Creating graph
        vertex[] graph = new vertex[numOfVertices + 1];
        for(int i = 1; i <= numOfVertices; i++) {
            graph[i] = new vertex();
        }
        for(int i = 1; i <= numOfEdges; i++) {
            tempInitValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
            int node_1 = tempInitValues[0], node_2 = tempInitValues[1];
            graph[node_1].edges.add(node_2);
        }

        if(checkForDisconnectedGraph(graph)) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }

    //Vertex for the graph
    public static class vertex {
        int color = 0;
        ArrayList<Integer> edges = new ArrayList<>();
    }

    // The checker function
    public static boolean cycleChecker(vertex[] graph, int source) {
        graph[source].color = 1;
        boolean preCheckValue = false;
        for(int edge : graph[source].edges) {
            if(graph[edge].color == 1) {
                return true;
            }
            if(graph[edge].color == 0) {
                preCheckValue =  preCheckValue || cycleChecker(graph, edge);
            }
        }
        graph[source].color = 2;
        return preCheckValue;
    }

    public static boolean checkForDisconnectedGraph(vertex[] graph) {
        boolean preCheckValue = false;
        for(int i = 1; i < graph.length; i++) {
            if(graph[i].color == 0) {
                preCheckValue = preCheckValue || cycleChecker(graph, i);
            }
        }
        return preCheckValue;
    }
}
