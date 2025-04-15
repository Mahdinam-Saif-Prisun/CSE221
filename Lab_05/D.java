package Lab_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder("");

        int numOfNodes = Integer.parseInt(st.nextToken());
        int numOfEdges = Integer.parseInt(st.nextToken());
        int source = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());
        int pivot = Integer.parseInt(st.nextToken());

        int[] parent_1 = new int[numOfNodes + 1];
        int[] parent_2 = new int[numOfNodes + 1];
        int[] color = new int[numOfNodes + 1];
        ArrayList<Integer>[] graph = new ArrayList[numOfNodes + 1];
        for (int i = 0; i <= numOfNodes; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < numOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            int node_1 = Integer.parseInt(st.nextToken());
            int node_2 = Integer.parseInt(st.nextToken());
            graph[node_1].add(node_2);
        }
        
        String result;

        // First Half
        Queue<Integer> bfsQueue = new LinkedList<>();
        color[source] = 1;
        bfsQueue.add(source);

        while(!(bfsQueue.isEmpty())) {
            int self = bfsQueue.remove();
            for(int edgeVertex : graph[self]) {
                if(color[edgeVertex] == 0) {
                    color[edgeVertex] = 1;
                    bfsQueue.add(edgeVertex);
                    parent_1[edgeVertex] = self;
                }
                if(color[pivot] == 1) {
                    break;
                }
            }
            if(color[pivot] == 1) {
                break;
            }
        }
        ArrayList<Integer> path_1 = extractPath(graph, parent_1, source, pivot);
        for(int i = 1; i <= numOfNodes; i++) {
            color[i] = 0;
        }
    
        // Second Half
        bfsQueue = new LinkedList<>();
        color[pivot] = 1;
        bfsQueue.add(pivot);
    
        while(!(bfsQueue.isEmpty())) {
            int self = bfsQueue.remove();
            for(int edgeVertex : graph[self]) {
                if(color[edgeVertex] == 0) {
                    color[edgeVertex] = 1;
                    bfsQueue.add(edgeVertex);
                    parent_2[edgeVertex] = self;
                }
                if(color[destination] == 1) {
                    break;
                }
            }
            if(color[destination] == 1) {
                break;
            }
        }
    
        ArrayList<Integer> path_2 = extractPath(graph, parent_2, pivot, destination);
        if(path_2.get(0) == -1 || path_1.get(0) == -1) {
            result = "-1";
        }
        else {
            ArrayList<Integer> path = new ArrayList<>();
            for(int i : path_1) {
                path.add(i);
            }
            for(int i = 1; i < path_2.size(); i++) {
                path.add(path_2.get(i));
            }
            for(int i : path) {
                sb.append(i);
                sb.append(" ");
            }
            result = sb.toString();
            System.out.println(path.size() - 1);
        }
        System.out.println(result);
    }

    public static ArrayList<Integer> extractPath(ArrayList<Integer>[] graph, int[] parent_1, int source, int destination) {
        ArrayList<Integer> result = new ArrayList<>();
        int runner = destination;
        while(runner != 0) {
            result.add(runner);
            if(runner == source) {
                Collections.reverse(result);
                return result;
            }
            runner = parent_1[runner];
        }
        return new ArrayList<>(Arrays.asList(-1));
    }
}

