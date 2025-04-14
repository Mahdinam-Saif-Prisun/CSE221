import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        String[] tempInp = br.readLine().split(" ");
        int arrLength = Integer.parseInt(tempInp[0]);
        int refSum = Integer.parseInt(tempInp[1]);
        int[] numsArr = new int[arrLength];
        String[] tempStringNums = br.readLine().split(" ");
        for(int i = 0; i < arrLength; i++) {
            numsArr[i] = Integer.parseInt(tempStringNums[i]);
        }


        int i = 0, j = 0;
        while(j < arrLength) {
            if(j == 0) {
                sum = numsArr[0];
            }
            else {
                sum += numsArr[j];
            }
            if(sum <= refSum) {
                j++;
            }
            else {
                sum -= numsArr[i];
                i++;
                j++;
            }
        }

        System.out.println(j - i);
    }
}
