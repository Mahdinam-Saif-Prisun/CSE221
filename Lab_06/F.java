package Lab_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int numOfWords = Integer.parseInt(br.readLine());
        String[] dictionary = new String[numOfWords];
        for(int i = 0; i < numOfWords; i++) {
            dictionary[i] = br.readLine();
        }

        ArrayList<Character> letters = new ArrayList<>();
        ArrayList<Node> graph = new ArrayList<>();

        //Creating graph
        for (String word : dictionary) {
            for (char ch : word.toCharArray()) {
                if (letters.indexOf(ch) == -1) {
                    letters.add(ch);
                    graph.add(new Node());
                }
            }
        }
        

        for(int i = 0; i < dictionary.length - 1; i++) {
            int check = compareWords(letters, graph, dictionary[i], dictionary[i + 1], 0);
            if(check == -1) {
                System.out.println(-1);
                System.exit(0);
            }
        }

        //Topological Sorting work
        ArrayList<Character> sorted = new ArrayList<>();
        PriorityQueue<Character> processing = new PriorityQueue<>();;
        for(int i = 0; i < graph.size(); i++) {
            if(graph.get(i).inDegree == 0) {
                processing.add(letters.get(i));
            }
        }
        while(!processing.isEmpty()) {
            char currentChar = processing.remove();
            int currentIndex = letters.indexOf(currentChar);
            Node current = graph.get(currentIndex);
            for(char following : current.next) {
                int followingIndex = letters.indexOf(following);
                graph.get(followingIndex).inDegree--;
                if(graph.get(letters.indexOf(following)).inDegree == 0) {
                    processing.add(following);
                }
            }
            sorted.add(currentChar);
        }

        if (sorted.size() != letters.size()) {
            System.out.println(-1);
            System.exit(0);
        }

        //Output
        for(char letter : sorted) {
            sb.append(letter);
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    //Function for comparing words
    public static int compareWords(ArrayList<Character> letters, ArrayList<Node> graph, String word1, String word2, int index) {
        if(index >= word1.length()) {
            return 0;
        }
        else if(index >= word2.length()) {
            return -1;
        }
        else if(word1.charAt(index) == word2.charAt(index)) {
            return compareWords(letters, graph, word1, word2, index + 1);
        }

        int charIdx_1 = letters.indexOf(word1.charAt(index)), charIdx_2 = letters.indexOf(word2.charAt(index));
        
        Node node_1 = graph.get(charIdx_1), node_2 = graph.get(charIdx_2);
        if(node_2.next.indexOf(letters.get(charIdx_1)) != -1) {
            return -1;
        }
        if(node_1.next.indexOf(letters.get(charIdx_2)) == -1) {
            node_1.next.add(letters.get(charIdx_2));
            node_2.inDegree += 1;
        }
        return 0;
    }

    //Node class
    public static class Node {
        int inDegree = 0;
        ArrayList<Character> next = new ArrayList<>();
    }
}
