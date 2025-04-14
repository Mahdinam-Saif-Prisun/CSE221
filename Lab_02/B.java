import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int len1, len2;
        int[] arr1, arr2;
        len1 = Integer.parseInt(br.readLine());
        arr1 = new int[len1];
        String[] tempArr = br.readLine().split(" ");
        for(int i = 0; i < len1; i++) {
            arr1[i] = Integer.parseInt(tempArr[i]);
        }
        len2 = Integer.parseInt(br.readLine());
        arr2 = new int[len2];
        tempArr = br.readLine().split(" ");
        for(int i = 0; i < len2; i++) {
            arr2[i] = Integer.parseInt(tempArr[i]);
        }


        int i = 0, j = 0;
        while(i < len1 && j < len2) {
            if(arr1[i] < arr2[j]) {
                sb.append(arr1[i]);
                sb.append(" ");
                i++;
            }
            else {
                sb.append(arr2[j]);
                sb.append(" ");
                j++;
            }
        }
        while(i < len1) {
            sb.append(arr1[i]);
            sb.append(" ");
            i++;
        }
        while(j < len2) {
            sb.append(arr2[j]);
            sb.append(" ");
            j++;
        }
        System.out.println(sb.toString());
    }
}
