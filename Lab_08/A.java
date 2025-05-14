package Lab_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int people = tempValues[0], friendships = tempValues[1];
        Node[] village = new Node[people + 1];
        int[] sizeArr = new int[friendships];
        StringBuilder sb = new StringBuilder("");

        //Creating disjoint sets
        for(int i = 1; i <= people; i++) {
            village[i] = new Node(i);
        }

        //Calculating circle size
        for(int i = 0; i < friendships; i++) {
            tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
            Node a = village[tempValues[0]], b = village[tempValues[1]];
            union(a, b, village);
            sizeArr[i] = village[a.parent.self].group.size();
        }

        //Output formatting
        for(int circleSize : sizeArr) {
            sb.append(circleSize).append("\n");
        }

        //Output
        System.out.print(sb.toString());

    }
    
    //Creating Node Class
    public static class Node {
        int self;
        Node parent;
        int size = 1;
        ArrayList<Node> group = new ArrayList<>();
        
        Node(int self) {
            this.self = self;
            this.parent = this;
            group.add(this);
        }
    }
    
    //Union function
    public static void union(Node a, Node b, Node[] village) {
        if(a.parent == b.parent) {
            return;
        }
        Node A = a.parent;
        Node B = b.parent;
        if(A.group.size() > B.group.size()) {
            for(Node people : B.group) {
                people.parent = A.parent;
                A.group.add(people);
            }
            A.size = A.group.size();
        }
        else {
            for(Node people : A.group) {
                people.parent = B.parent;
                B.group.add(people);
            }
            B.size = B.group.size();
        }
    }
}
