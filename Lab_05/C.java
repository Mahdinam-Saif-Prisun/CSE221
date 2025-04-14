package Lab_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tempInitValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int numOfNodes = tempInitValues[0], numOfEdges = tempInitValues[1], source = tempInitValues[2], destination = tempInitValues[3];
        String[] nodeArr_1 = br.readLine().split(" ");
        String[] nodeArr_2 = br.readLine().split(" ");

        Node[] graph = new Node[numOfNodes + 1];
        for(int i = 1; i <= numOfNodes; i++) {
            graph[i] = new Node();
        }

        for(int i = 1; i <= numOfEdges; i++) {
            int node_1 = Integer.parseInt(nodeArr_1[i - 1]), node_2 = Integer.parseInt(nodeArr_2[i - 1]);
            graph[node_1].edges.add(node_2);
            graph[node_2].edges.add(node_1);
        }

        Queue<Integer> bfsQueue = new LinkedList<>();
        graph[source].color = 1;
        bfsQueue.add(source);

        while(!(bfsQueue.isEmpty())) {
            int self = bfsQueue.remove();
            PriorityQueue<Integer> tempHeap = new PriorityQueue<>(graph[self].edges);
            while(!tempHeap.isEmpty()) {
                int edgeVertex = tempHeap.remove();
                if(graph[edgeVertex].color == 0) {
                    graph[edgeVertex].color = 1;
                    bfsQueue.add(edgeVertex);
                    graph[edgeVertex].parent = self;
                }
            }
        }

        LinkedList<String> path = extractPath(graph, source, destination);
        String result = String.join(" ", path);
        if(result.equals("-1")) {
            ;
        }
        else {
            System.out.println(path.size() - 1);
        }
        System.out.println(result);

    }

    public static class Node {
        int color = 0, parent = 0;
        PriorityQueue<Integer> edges = new PriorityQueue<>();
    }

    public static LinkedList<String> extractPath(Node[] graph, int source, int destination) {
        
        LinkedList<String> result = new LinkedList<>();
        int runner = destination;
        while(runner != 0) {
            result.addFirst(Integer.toString(runner));
            if(runner == source) {
                return result;
            }
            runner = graph[runner].parent;
        }
        return new LinkedList<>(Arrays.asList("-1"));
    }
}
