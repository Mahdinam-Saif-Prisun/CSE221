package Lab_01;
import java.util.Scanner;

public class G {
    public static String compare(String n1, String n2) {
        if(n1.equals(n2)) {
            return "Same";
        }
        for(int i = 0;; i++) {
            if(i == n1.length() || i == n2.length()) {
                if(n1.length() > n2.length()) {
                    return "Big";
                }
                return "Small";
            }
            else if(n1.charAt(i) > n2.charAt(i)) {
                return "Big";
            }
            else if(n1.charAt(i) < n2.charAt(i)) {
                return "Small";
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        String[][] info = new String[count][3];
        String op = "";
        for(int i = 0; i < count; i++) {
            info[i][0] = sc.next();
            sc.next(); sc.next(); sc.next();
            info[i][1] = sc.next();
            sc.next();
            info[i][2] = sc.next();
        }
        
        for(int i = count - 1; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                op = "Same";
                if(G.compare(info[j][0], info[j + 1][0]).equals("Same")) {
                    op = G.compare(info[j][2], info[j + 1][2]);
                    if(op.equals("Big")) {
                        op = "Small";
                    }
                    else if(op.equals("Small")) {
                        op = "Big";
                    }
                }
                else {
                    op = G.compare(info[j][0], info[j + 1][0]); 
                }
                if(op.equals("Big")) {
                    String[] temp = info[j];
                    info[j] = info[j + 1];
                    info[j + 1] = temp;
                }
            }
        }
        for(int i = 0; i < count; i++) {
            System.out.printf("%s will departure for %s at %s\n", info[i][0], info[i][1], info[i][2]);
        }
    }
}
