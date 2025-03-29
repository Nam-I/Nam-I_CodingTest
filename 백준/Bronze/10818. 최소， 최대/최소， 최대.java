import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int maxNum = -1000000;
    static int minNum = 1000000;
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            maxNum = Math.max(maxNum, num);
            minNum = Math.min(minNum, num);
        }
        System.out.print(minNum + " ");
        System.out.print(maxNum);
    }
}