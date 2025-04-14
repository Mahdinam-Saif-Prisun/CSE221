package Lab_01;
import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int[] arr = new int[count];
        boolean best = true;
        for(int i = 0; i < count; i++) {
            arr[i] = sc.nextInt();
        }
        for(int i = 0; i < count - 1; i++) {
            if(arr[i] > arr[i + 1]) {
                best = false;
                break;
            }
        }
        if(best == false) {
            for(int i = 0; i < count - 1; i++) {
                for(int j = 0; j < count - i - 1; j++) {
                    if(arr[j] > arr[j + 1]) {
                        int temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
