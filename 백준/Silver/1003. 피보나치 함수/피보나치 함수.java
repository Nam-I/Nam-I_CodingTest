/** 백준 1003.S3.피보나치 함수 - DP java 풀이 **/
/**
 * ## 체감 난이도 ##
 * - 피보나치 함수는 DP 문제로 너무 유명해서 그냥 보자마자 해결법이 떠올라서 쉽게 느껴졌다.
 * - 기본중의 기본 문제라 S3 난이도 정도인가? 라는 생각도 들었다.
 * ## 핵심 내용 ##
 * - DP 문제는 점화식을 세워보면서 직접 규칙을 찾아봐야지 해결 실마리가 보인다.**/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int T, num;
    static int[][] dp = new int[41][2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dp[0][0] = 1; dp[0][1] = 0;
        dp[1][0] = 0; dp[1][1] = 1;
        dp[2][0] = 1; dp[2][1] = 1;
        dp[3][0] = 1; dp[3][1] = 2;

        for (int i = 4; i < 41; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            num = Integer.parseInt(br.readLine());

            bw.write(dp[num][0] + " " + dp[num][1] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}