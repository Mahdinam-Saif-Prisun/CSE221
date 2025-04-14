import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tempIn = br.readLine();
        int arrLength = Integer.parseInt(tempIn.split(" ")[0]);
        int reference = Integer.parseInt(tempIn.split(" ")[1]);
        int[] nums = new int[arrLength];
        tempIn = br.readLine();
        String[] tempStringNumsArray = tempIn.split(" ");
        for(int i = 0; i < arrLength; i++) {
            nums[i] = Integer.parseInt(tempStringNumsArray[i]);
        }


        int i = 0, j = arrLength - 1;
        boolean found = false;
        while(i < j) {
            if(nums[i] + nums[j] == reference) {
                System.out.printf("%d %d\n", i + 1, j + 1);
                found = true;
                break;
            }
            else if(nums[i] + nums[j] > reference) {
                j--;
            }
            else {
                i++;
            }
        }
        if(found == false) {
            System.out.println("-1");
        }
    }
}
