/**백준 3067(9084 동일).G5.동전 - java 풀이**/
/**
 * ## 체감 난이도 ##
 * - dp 문제를 많이 안풀어봐서 그런지 많이 어렵게 느껴졌다.
 * - 체감 G4? 정도 느낌
 * ## 핵심 내용 ##
 * - 0원을 만드는 경우의 수까지 생각해야 한다.
 * - 만약 {2, 3} 원이 있을 때 3원을 하나 이상 사용해 7원을 만드는 경우의 수를 구한다면
 * dp[7] += dp[7 - 3]
 * dp[7] += dp[4]
 * - 3원 하나를 사용한다 치고 3을 빼서 {2, 3} 으로 4원을 만드는 경우의 수를 구해 dp[7]에 누적
 **/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int N;
    static int M;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int[] dp;

        T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        while(T-- > 0) {
            N = Integer.parseInt(br.readLine()); // 동전 종류 수
            coins = new int[N];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine()); // 목표 금액
            dp = new int[M+1];
            // dp[x] : 주어진 동전들을 사용해서 x원을 만드는 경우의 수
            // 0원을 만드는 경우 추가 & x원을 인덱스로 바로 접근할 수 있어야 하므로
            // 배열 크기 선언은 + 1

            dp[0] = 1; // 0원을 만드는 경우의 수 1개

            for (int coin : coins) {
                // coin 이상부터  M(targetMoney) 까지 오름차순 순회
                for (int curMoney = coin; curMoney <= M; curMoney++) {
                    // money원을 만드는 방법에,
                    // "현재 동전 coin을 한 개 이상 사용하는 경우" 추가
                    // (money - coin)을 만드는 모든 방법 뒤에 coin 하나 붙이면 되므로
                    dp[curMoney] += dp[curMoney - coin];
                }
            }

            sb.append(dp[M]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
