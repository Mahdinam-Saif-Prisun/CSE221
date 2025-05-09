package Lab_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int[] tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int numOfCities = tempValues[0], numOfRoads = tempValues[1];

        //Creating graph
        Node[] graph = new Node[numOfCities + 1];
        for(int i = 1; i <= numOfCities; i++) {
            graph[i] = new Node();
        }

        for(int i = 0; i < numOfRoads; i++) {
            tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
            int u = tempValues[0], v = tempValues[1], w = tempValues[2];
            graph[u].edges.add(new int[] {v, w});
            graph[v].edges.add(new int[] {u, w});
        }

        //Djikstra modded
        PriorityQueue<State> pq = new PriorityQueue<>();
        graph[1].danger = 0;
        pq.add(new State(1, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int u = curr.self;
            if (graph[u].visited) continue;
            graph[u].visited = true;

            for (int[] edge : graph[u].edges) {
                int v = edge[0];
                int maxDanger = Math.max(graph[u].danger, edge[1]);
                
                if (graph[v].danger == -1 || graph[v].danger > maxDanger) {
                    graph[v].danger = maxDanger;
                    pq.add(new State(v, graph[v].danger));
                }
            }
        }

        //Output formatting
        for(int i = 1; i <= numOfCities; i++) {
            sb.append(graph[i].danger).append(" ");
        }

        //Output
        System.out.println(sb.toString());
    }


    //Node class
    public static class Node {
        int danger = -1;
        boolean visited = false;
        ArrayList<int[]> edges = new ArrayList<>();
    }

    //State class
    static class State implements Comparable<State> {
        int self, danger;
        State(int node, int danger) {
            this.self = node;
            this.danger = danger;
        }

        public int compareTo(State other) {
            return this.danger - other.danger;
        }
    }

}
