import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E {

    public static int lowerBound(String[] arr, int query) {
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while(left <= right) {
            mid = (left + right) / 2;
            if(Integer.parseInt(arr[mid]) < query) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static int upperBound(String[] arr, int query) {
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while(left <= right) {
            mid = (left + right) / 2;
            if(Integer.parseInt(arr[mid]) <= query) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tempIn = br.readLine().split(" ");
        int arrSize = Integer.parseInt(tempIn[0]), count = Integer.parseInt(tempIn[1]);
        String[] numArr = br.readLine().split(" ");
        for(int i = 0; i < count; i++) {
            tempIn = br.readLine().split(" ");
            int lowerBoundIndex = lowerBound(numArr, Integer.parseInt(tempIn[0]));
            int upperBoundIndex = upperBound(numArr, Integer.parseInt(tempIn[1]));
            System.out.println(upperBoundIndex - lowerBoundIndex);
        }
    }
}
