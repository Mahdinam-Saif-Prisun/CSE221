import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;

public class A {
    public static String[] merge(String[] part1, String[] part2) {
        String[] mergedArray = new String[part1.length + part2.length];
        int i = 0, j = 0;
        while(i < part1.length && j < part2.length) {
            if(Integer.parseInt(part1[i]) > Integer.parseInt(part2[j])) {
                Global.inversionCounter += part1.length - i;
                mergedArray[i + j] = part2[j];
                j++;
            }
            else {
                mergedArray[i + j] = part1[i];
                i++;
            }
        }

        while(i < part1.length) {
            mergedArray[i + j] = part1[i];
            i++;
        }

        while(j < part2.length) {
            mergedArray[i + j] = part2[j];
            j++;
        }
        return mergedArray;
    }

    public static String[] mergeSort(String[] arr) {
        if(arr.length == 1) {
            return arr;
        }
        int mid = arr.length / 2;
        String[] part1 = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        String[] part2 = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(part1, part2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner sj = new StringJoiner(" ");
        int arrLength = Integer.parseInt(br.readLine());
        String[] numStringArr = br.readLine().split(" ");
        String[] sortedArray = mergeSort(numStringArr);
        for(int i = 0; i < sortedArray.length; i++) {
            sj.add(sortedArray[i]);
        }
        System.out.println(Global.inversionCounter);
        System.out.println(sj.toString());
    }
}

class Global{
    static int inversionCounter = 0;
}
