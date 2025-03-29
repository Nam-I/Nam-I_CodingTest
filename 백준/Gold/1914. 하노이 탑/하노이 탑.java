import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigInteger;

public class Main {
    static StringBuilder sb = new StringBuilder("");
    static void hanoi(int n, int start, int end) {
        if(n == 0) return;
        
        hanoi(n-1, start, 6-start-end); //n-1개 원반을 시작 기둥에서 중간 기둥으로
        sb.append(start);
        sb.append(" ");
        sb.append(end);
        sb.append("\n");
        hanoi(n-1, 6-start-end, end); //n-1개의 원반을 중간 기둥에서 목표 기둥으로
    }
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BigInteger two = new BigInteger("2"); //BigInteger.TWO와 동일
        //하노이탑 이동 횟수 공식: 2^N-1
        sb.append(two.pow(N).subtract(new BigInteger("1")));
        
        if(N <= 20) {
            sb.append("\n");
            hanoi(N, 1, 3);
        }
        
        System.out.println(sb);
    }
}
