/** 백준 1149.S1.RGB거리 - Bottom up 반복문 DP java 풀이
 * ## 체감 난이도 ##
 * - 대충 어떤 식을 점화식을 세워야하는지 어렴풋이 떠오르긴 해서 S1 난이도는 적절한 듯하다.
 * - 하지만 아직 DP 문제는 쉽게 풀리진 않는거 같다.
 * ## 핵심 내용 ##
 * - DP 문제는 인접 리스트 그려보면서 점화식 세워보기
 * - 재귀로도 풀 수 있으나 일반적으로 반복문으로 풀 수 있으면 반복문이 더 빠르기 때문에 
 * 반목문으로만 풀었다.
 **/
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main  {
    static int N;
    static long[][] dp;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(st.nextToken()); //빨
            int green = Integer.parseInt(st.nextToken()); //초
            int blue = Integer.parseInt(st.nextToken()); //파

            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + red; // i 번째 집을 빨강으로 칠하면 인접한 이전 집은 초록 or 파랑 그 중 최소 선택
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + green;
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + blue;
        }

        bw.write(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}