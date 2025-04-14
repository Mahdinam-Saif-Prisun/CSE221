package Lab_01;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        long sum, num;
        for(int i = 0; i < count; i++) {
            num = sc.nextLong();
            sum = (num * (num + 1)) / 2;
            System.out.println(sum);
        }
    }
}
