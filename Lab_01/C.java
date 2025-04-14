package Lab_01;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int n, k;
        String temp = br.readLine();
        n = Integer.parseInt(temp.split(" ")[0]);
        k = Integer.parseInt(temp.split(" ")[1]);
        temp = br.readLine();
        String[] nums = temp.split(" ");
        for(int i = k - 1; i >= 0; i--) {
            sb.append(nums[i]);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}