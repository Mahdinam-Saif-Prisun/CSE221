package Lab_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int[] tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int numOfNodes = tempValues[0], root = tempValues[1];
        Node[] tree = new Node[numOfNodes + 1];
        for(int i = 0; i <= numOfNodes; i++) {
            tree[i] = new Node();
        }

        //Creating tree
        for(int i = 1; i < numOfNodes; i++) {
            tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
            int Node_1 = tempValues[0];
            int Node_2 = tempValues[1];
            tree[Node_1].edges.add(Node_2);
            tree[Node_2].edges.add(Node_1);
        }
        tree[root].parent = -1;

        //Finding size
        int garbage = sizer(tree, root);

        //Queries
        int queries = Integer.parseInt(br.readLine());
        for(int i = 0; i < queries; i++) {
            int node = Integer.parseInt(br.readLine());
            sb.append(tree[node].subTreeSize);
            sb.append("\n");
        }
        
        //Output
        System.out.println(sb.toString());
    }

    //Creating node class
    public static class Node {
        int subTreeSize = 1;
        int parent = 0;
        ArrayList<Integer> edges = new ArrayList<>();
    }

    //Size assignment function
    public static int sizer(Node[] tree, int root) {
        if(tree[root].edges.isEmpty()) {
            return 1;
        }
        for(int node : tree[root].edges) {
            if(tree[node].parent == 0) {
                tree[node].parent = root;
                tree[root].subTreeSize += sizer(tree, node);
            }
        }
        return tree[root].subTreeSize;
    }
}
