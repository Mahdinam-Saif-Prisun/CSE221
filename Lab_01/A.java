package Lab_01;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int num;
        for(int i = 0; i < count; i++) {
            num = sc.nextInt();
            if(num % 2 == 0) {
                System.out.printf("%d is an Even number.\n", num);
            }
            else {
                System.out.printf("%d is an Odd number.\n", num);
            }
        }
    }
}
