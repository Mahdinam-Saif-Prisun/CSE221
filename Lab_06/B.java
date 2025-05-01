package Lab_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class B {
    public static void main(String[] args) throws IOException {
        //Initialize
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tempArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        int numOfPlayers = tempArr[0];
        int numOfTackles = tempArr[1];
        int numOfRobots = 0;
        vertex[] playerData = new vertex[numOfPlayers];
        for(int i = 0; i < numOfPlayers; i++) {
            playerData[i] = new vertex();
        }
        
        //Creating graph
        for(int i = 0; i < numOfTackles; i++) {
            int[] playersInTackle = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
            int player_1 = playersInTackle[0] - 1, player_2 = playersInTackle[1] - 1;
            playerData[player_1].edges.add(player_2);
            playerData[player_2].edges.add(player_1);
        }
        
        //Iterative counter
        for(int i = 0; i < numOfPlayers; i++) {
            if(playerData[i].color == null) {
                numOfRobots += BFS_BP(numOfPlayers, playerData, i);
            }
        }

        //Output formatting
        numOfRobots = Math.max(numOfRobots, numOfPlayers - numOfRobots);
        System.out.println(numOfRobots);
    }

    //Creating vertex
    public static class vertex {
        Integer color = null;
        ArrayList<Integer> edges = new ArrayList<>();
    }

    //BFS function to create bipartite
    public static int BFS_BP(int numOfVertices, vertex[] graph, int source) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        int numOfZeros = 0, numOfOnes = 0;
        graph[source].color = 0;
        numOfZeros += 1;
        bfsQueue.add(source);

        while(!(bfsQueue.isEmpty())) {
            int self = bfsQueue.remove();
            for(int edgeVertex : graph[self].edges) {
                if(graph[edgeVertex].color == null) {
                    graph[edgeVertex].color = -(graph[self].color - 1);
                    if(graph[edgeVertex].color == 0) {
                        numOfZeros += 1;
                    }
                    else {
                        numOfOnes += 1;
                    }
                    bfsQueue.add(edgeVertex);
                }
            }
        }
        return Math.max(numOfOnes, numOfZeros);
    }
}
