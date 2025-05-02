package Lab_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfNodes = Integer.parseInt(br.readLine());
        int[] tempValues;
        Node[] graph = new Node[numOfNodes + 1];
        for(int i = 0; i <= numOfNodes; i++) {
            graph[i] = new Node();
        }

        //Creating the graph
        for(int i = 1; i < numOfNodes; i++) {
            tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
            int node_1 = tempValues[0], node_2 = tempValues[1];
            graph[node_1].edges.add(node_2);
            graph[node_2].edges.add(node_1);            
        }

        //Main work
        int edge_1, edge_2;
        ArrayList<Integer> bfsPath = BFS(graph, 1);
        edge_1 = bfsPath.get(bfsPath.size() - 1);
        for(int i = 1; i <= numOfNodes; i++) {
            graph[i].color = 0;
            graph[i].distance = 0;
        }
        bfsPath = BFS(graph, edge_1);
        edge_2 = bfsPath.get(bfsPath.size() - 1);
        int diameter = graph[edge_2].distance;

        //Output
        System.out.printf("%d \n%d %d\n", diameter ,edge_1, edge_2);
    }


    //Creating node class
    public static class Node {
        int color = 0;
        int distance = 0;
        ArrayList<Integer> edges = new ArrayList<>();
    }


    //BFS
    public static ArrayList<Integer> BFS(Node[] graph, int source) {
        ArrayList<Integer> path = new ArrayList<>();
        Queue<Integer> bfsQueue = new LinkedList();
        bfsQueue.add(source);
        while(!bfsQueue.isEmpty()) {
            int current = bfsQueue.remove();
            path.add(current);
            graph[current].color = 1;
            for(int node : graph[current].edges) {
                if(graph[node].color == 0) {
                    graph[node].distance = graph[current].distance + 1;
                    bfsQueue.add(node);
                }
            }
        }
        return path;
    }
}



