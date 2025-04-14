package Lab_05;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfVertices = sc.nextInt();
        int numOfEdges = sc.nextInt();
        ArrayList<String> order = new ArrayList<>();
        vertex[] graph = new vertex[numOfVertices + 1];
        for (int i = 1; i <= numOfVertices; i++) {
            graph[i] = new vertex();
        }
        for(int i = 0; i < numOfEdges; i++) {
            int parent = sc.nextInt();
            int destination = sc.nextInt();
            graph[parent].edges.add(destination);
            graph[destination].edges.add(parent);
        }

        Queue<Integer> bfsQueue = new LinkedList<>();
        graph[1].color = 1;
        bfsQueue.add(1);

        while(!(bfsQueue.isEmpty())) {
            int self = bfsQueue.remove();
            order.add(Integer.toString(self));
            for(int edgeVertex : graph[self].edges) {
                if(graph[edgeVertex].color == 0) {
                    graph[edgeVertex].color = 1;
                    bfsQueue.add(edgeVertex);
                }
            }
        }


        String result = String.join(" ", order);
        System.out.println(result);
    }

    public static class vertex {
        int color = 0;
        ArrayList<Integer> edges = new ArrayList<>();
    }
}
