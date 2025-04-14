import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static class Node {
        int[] pair = new int[2];
        Node next;

        Node (int adjacentVertex, int weight) {
            this.pair[0] = adjacentVertex;
            this.pair[1] = weight;
            this.next = null;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        String[] tempIn = br.readLine().split(" ");
        int vertices = Integer.parseInt(tempIn[0]), edges = Integer.parseInt(tempIn[1]);

        String[] mainVertexArr, adjacentVertexArr, weightArr;
        mainVertexArr = br.readLine().split(" ");
        adjacentVertexArr = br.readLine().split(" ");
        weightArr = br.readLine().split(" ");
        Node[] adjacencyList = new Node[vertices];

        for(int i = 0; i < edges; i++) {
            int mainNode = Integer.parseInt(mainVertexArr[i]);
            int adjacentNode = Integer.parseInt(adjacentVertexArr[i]);
            int weight = Integer.parseInt(weightArr[i]);
            if(adjacencyList[mainNode - 1] == null) {
                adjacencyList[mainNode - 1] = new Node(adjacentNode, weight);
            }
            else {
                Node runner = adjacencyList[mainNode - 1];
                while(runner.next != null) {
                    runner = runner.next;
                }
                runner.next = new Node(adjacentNode, weight);
            }
        }

        for (int i = 0; i < adjacencyList.length; i++) {
            sb.append(i + 1);
            sb.append(":");
            Node runner = adjacencyList[i];
            while(runner != null) {
                sb.append(String.format(" (%d, %d)", runner.pair[0], runner.pair[1]));
                runner = runner.next;
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
