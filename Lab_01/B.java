package Lab_01;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count;
        count = sc.nextInt();
        char operator;
        float num1, num2, result = 0;
        String trash;
        for(int i = 0; i < count; i++) {
            trash = sc.next();
            num1 = sc.nextFloat();
            operator = sc.next().charAt(0);
            num2 = sc.nextFloat();

            if(operator == '+') {
                result = num1 + num2;
            }
            else if(operator == '-') {
                result = num1 - num2;
            }
            else if(operator == '*') {
                result = num1 * num2;
            }
            else if(operator == '/') {
                result = num1 / num2;
            }
            System.out.println(result);
        }
    }
}
