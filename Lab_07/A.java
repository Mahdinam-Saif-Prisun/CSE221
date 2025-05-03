package Lab_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int numOfVertices = tempValues[0], numOfEdges = tempValues[1], source = tempValues[2], destination = tempValues[3];
        int[] u_Nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int[] v_Nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int[] weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();

        //Creating graph
        Node[] graph = new Node[numOfVertices + 1];
        for(int i = 1; i <= numOfVertices; i++) {
            graph[i] = new Node(i);
        }
        for(int i = 0; i < numOfEdges; i++) {
            graph[u_Nodes[i]].edges.add(new int[] {v_Nodes[i], weights[i]});
        }

        //Djikstra
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        graph[source].distance = 0;
        pq.add(graph[source]);

        while(!pq.isEmpty()) {
            Node currNode = pq.remove();
            currNode.visited = true;
            //Relaxation
            for(int[] adjacent : currNode.edges) {
                Node adjNode = graph[adjacent[0]];
                if(!adjNode.visited && currNode.distance + adjacent[1] < adjNode.distance) {
                    adjNode.distance = currNode.distance + adjacent[1];
                    adjNode.parent = currNode.self;
                    pq.add(adjNode);
                }
            }
        }

        //Output formatting
        StringBuilder sb = new StringBuilder("");
        if(!graph[destination].visited) {
            sb.append(-1);
        }
        else {
            Node currNode = graph[destination];
            sb.append(currNode.distance);
            sb.append("\n");

            ArrayList<Integer> path = new ArrayList<>();
            path.add(0, destination);
            while(currNode.self != source) {
                path.add(0, currNode.parent);
                currNode = graph[currNode.parent];
            }

            for(int step : path) {
                sb.append(step);
                sb.append(" ");
            }
        }

        System.out.println(sb.toString());
    }


    //Node class
    public static class Node {
        int self;
        int distance = Integer.MAX_VALUE;
        int parent = 0;
        boolean visited = false;
        ArrayList<int[]> edges = new ArrayList<>();

        Node(int self) {
            this.self = self;
        }
    }

    //Node pq Comparator
    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node n_1, Node n_2) {
            return n_1.distance - n_2.distance;
        }
    }
}
