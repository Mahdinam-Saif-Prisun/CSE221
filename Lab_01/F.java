package Lab_01;
import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count, maxim, swap = 0;
        count = sc.nextInt();
        int[] ids = new int[count];
        int[] marks = new int[count];

        for(int i = 0; i < count; i++) {
            ids[i] = sc.nextInt();
        }
        for(int i = 0; i < count; i++) {
            marks[i] = sc.nextInt();
        }

        for(int i = 0; i < count - 1; i++) {
            maxim = i;
            for(int j = i + 1; j < count; j++) {
                if(marks[maxim] < marks[j]) {
                    maxim = j;
                }
                else if(marks[maxim] == marks[j]) {
                    if(ids[maxim] > ids[j]) {
                        maxim = j;
                    }
                }
            }
            if(maxim != i) {
                int temp = ids[maxim];
                ids[maxim] = ids[i];
                ids[i] = temp;
                temp = marks[maxim];
                marks[maxim] = marks[i];
                marks[i] = temp;
                swap++;
            }
        }

        System.out.printf("Minimum swaps: %d\n", swap);
        for(int i = 0; i < count; i++) {
            System.out.printf("ID: %d Mark: %d\n", ids[i], marks[i]);
        }
    }
}
