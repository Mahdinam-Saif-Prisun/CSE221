import java.util.ArrayList;
import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int chessBoardSize = sc.nextInt();
        ArrayList<int[]> positions = new ArrayList<>();
        int xPos = sc.nextInt();
        int yPos = sc.nextInt();
        for(int x = xPos - 1; x <= xPos + 1; x++) {
            if(x < 1 || x > chessBoardSize) {
                continue;
            }
            for(int y = yPos - 1; y <= yPos + 1; y++) {
                if(y < 1 || y > chessBoardSize || (x == xPos && y == yPos)) {
                    continue;
                }
                positions.add(new int[] {x, y});
            }
        }
        System.out.println(positions.size());
        for(int[] pair : positions) {
            System.out.printf("%d %d\n", pair[0], pair[1]);
        }
    }
}
