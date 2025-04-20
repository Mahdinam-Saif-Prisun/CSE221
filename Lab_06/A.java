package Lab_06;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
        StringBuilder sb = new StringBuilder("");
        
        // Graph building
        int numOfCourses = tempValues[0];
        int numOfRequirements = tempValues[1];
        course[] reqGraph = new course[numOfCourses + 1];
        for(int i = 1; i <= numOfCourses; i++) {
            reqGraph[i] = new course();
        }
        for (int i = 0; i < numOfRequirements; i++) {
            tempValues = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer :: parseInt).toArray();
            course uNode = reqGraph[tempValues[0]];
            course vNode = reqGraph[tempValues[1]];
            uNode.next.add(tempValues[1]);
            vNode.inDegree++;
        }
        
        // Topological Sorting work
        ArrayList<Integer> sorted = new ArrayList<>();
        Queue<Integer> processing = new LinkedList<>();
        for(int i = 1; i <= numOfCourses; i++) {
            if(reqGraph[i].inDegree == 0) {
                processing.add(i);
            }
        }
        while(!processing.isEmpty()) {
            int currentIndex = processing.remove();
            course currentCourse = reqGraph[currentIndex];
            for(int unlocked : currentCourse.next) {
                reqGraph[unlocked].inDegree--;
                if(reqGraph[unlocked].inDegree == 0) {
                    processing.add(unlocked);
                }
            }
            sorted.add(currentIndex);
        }

        //Formatting output
        if(sorted.size() == numOfCourses) {
            for(int x : sorted) {
                sb.append(x);
                sb.append(" ");
            }
    
            System.out.println(sb.toString());
        }
        else {
            System.out.println(-1);
        }
    }


    // Course as node
    public static class course {
        int inDegree = 0;
        ArrayList<Integer> next = new ArrayList<>();
    }
}