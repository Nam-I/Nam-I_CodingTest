/** 백준 9095.S3.1, 2, 3 더하기 - java 풀이 **/
/**
 * ## 체감 난이도 ##
 * - DP 문제라는건 풀다보니 감이왔다.
 * - 다들 점화식 규칙을 잘 찾았나보다 정답률이 높은걸 보니..
 * - DP가 아직 나에게 익숙하지는 않은거 같다.
 * ## 핵심 내용 ##
 * - DP 문제는 직접 경우의 수를 풀어 가면서 점화식 규칙을 찾아야 한다.
 * - DP 문제 나오면 귀찮아도 일단 손으로 써볼 것
 * - DP: 이전의 값을 활용해 현재의 값을 찾아내는 문제
 * - 큰 문제의 최적해가, 작은 부분 문제들의 최적해로부터 만들어진다.
 * - 작은 부분 문제가 반복해서 등장한다.(같은 상태를 여러 번 계산하게 된다.
 * - 큰 문제를 작은 부분 문제로 쪼개고, 그 부분 문제의 답을 저장해서 중복 계산을 제거하는 기법
 **/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int T;
    static int[] dp = new int[11];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dp[1] = 1; // 1을 만드는 경우의 수 => 1
        dp[2] = 2; // 2를 만든느 경우의 수 => 1+1, 2
        dp[3] = 4; // 3을 만드는 경우의 수 => 1+1+1, 1+2, 2+1, 3

        for (int i = 4; i < 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            // ex) i == 4
            // dp[3] 을 만드는 각 경우에 1을 더한 것 + dp[2]를 만드는 각 경우에 2를 더한 것 + dp[1]을 만드는 경우의 수에 3을 더한 것 
        }

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            bw.write(dp[Integer.parseInt(br.readLine())] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}