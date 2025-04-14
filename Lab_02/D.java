import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int left, right, mid;
        for(int i = 0 ; i < count; i++) {
            String bitStream = br.readLine();
            left = 0;
            right = bitStream.length() - 1;
            mid = 0;
            if(bitStream.charAt(right) == '1') {
                while(left <= right) {
                    mid = (left + right) / 2;
                    if(bitStream.charAt(mid) == '0') {
                        left = mid + 1;
                    }
                    else {
                        right = mid - 1;
                    }
                }
                if(bitStream.charAt(mid) == '0') {
                    System.out.println(mid + 2);
                }
                else {
                    System.out.println(mid + 1);
                }
            }
            else {
                System.out.println("-1");
            }            
        }
    }
}
