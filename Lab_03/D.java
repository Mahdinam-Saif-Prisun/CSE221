import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class D {

    public static long modExp(long num, long power, long mod) {
        long result = 1;
        num = num % mod;
        while(power > 0) {
            if(power % 2 != 0) {
                result = (result * num) % mod;
            }
            num = (num * num) % mod;
            power = power / 2;
        }
        return result;
    }

    public static long GeometricSumMod(long num, long power, long mod) {
        if(num == 1) {
            return power % mod;
        }
        else{
        long result = (num % (mod * (1 - num)) - modExp(num, power + 1, mod * (1 - num))) / (1 - num);
        return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner sj = new StringJoiner("\n");
        int cases = Integer.parseInt(br.readLine());
        for (int i = 0; i < cases; i++) {
            String[] tempInput = br.readLine().split(" ");
            long num = Long.parseLong(tempInput[0]);
            long power = Long.parseLong(tempInput[1]);
            long mod = Long.parseLong(tempInput[2]);
            sj.add(String.valueOf(GeometricSumMod(num, power, mod)));
        }
        System.out.println(sj.toString());
    }
}
