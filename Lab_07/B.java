package Lab_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int[] tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int numOfVertices = tempValues[0], numOfEdges = tempValues[1], friend_1 = tempValues[2], friend_2 = tempValues[3];

        //Creating graph
        Node[] graph_1 = new Node[numOfVertices + 1];
        Node[] graph_2 = new Node[numOfVertices + 1];
        for(int i = 1; i <= numOfVertices; i++) {
            graph_1[i] = new Node(i);
            graph_2[i] = new Node(i);
        }

        for(int i = 0; i < numOfEdges; i++) {
            tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
            Node currNode = graph_1[tempValues[0]];
            currNode.edges.add(new int[] {tempValues[1], tempValues[2]});
            currNode = graph_2[tempValues[0]];
            currNode.edges.add(new int[] {tempValues[1], tempValues[2]});
        }

        //friends data
        djikstra(graph_1, friend_1);
        djikstra(graph_2, friend_2);

        //Main work
        int meetingPoint = -1;
        int minDist = Integer.MAX_VALUE;
        int tempDist;

        for(int i = 1; i <= numOfVertices; i++) {
            if(graph_1[i].visited && graph_2[i].visited) {
                tempDist = Math.max(graph_1[i].distance, graph_2[i].distance);
                if(tempDist < minDist) {
                    minDist = tempDist;
                    meetingPoint = i;
                }
                else if(tempDist == minDist) {
                    if(meetingPoint == -1) {
                        meetingPoint = i;
                    }
                    else {
                        meetingPoint = Math.min(i, meetingPoint);
                    }
                }
            }
        }

        //output formatting
        if(meetingPoint != -1) {
            sb.append(minDist);
            sb.append(" ");
        }
        sb.append(meetingPoint);

        //Output
        System.out.println(sb.toString());
    }

    //Node class
    public static class Node {
        int self;
        int distance = Integer.MAX_VALUE;
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

    //Djikstra path
    public static void djikstra (Node[] graph, int source) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        graph[source].distance = 0;
        pq.add(graph[source]);

        while(!pq.isEmpty()) {
            Node currNode = pq.remove();
            currNode.visited = true;
            
            //Relaxation
            for(int[] adjacent : currNode.edges) {
                Node adjNode = graph[adjacent[0]];
                int newDist = currNode.distance + adjacent[1];
                if(newDist < adjNode.distance) {
                    adjNode.distance = newDist;
                    pq.add(adjNode);
                }
            }
        }
    }
}
