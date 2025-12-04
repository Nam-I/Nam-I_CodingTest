/** 백준 11053.S2.가장 긴 증가하는 부분 수열 - java 풀이 **/
/**
 * ## 체감 난이도 ##
 * - Dynamic Programming(동적계획법) 문제를 많이 안풀어 봐서 좀 어려웠다.
 * - DP 많이 풀어본 사람한테는 쉬었을 듯. 딱 기본 개념 문제인 듯 했다.
 * - 처음에 DFS 로 풀려다가 시간 초과 남.. 정답이 나오긴 했겠지만
 * ## 핵심 내용 ##
 * - DP: 이전에 계산해둔 값을 활용해서 현재의 값을 찾는 퓰아법
 * - ex: 피보나치 수열
 **/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int A;
    static int[] arr;
    static int[] dp;
    static int max = 1;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        A = Integer.parseInt(br.readLine());
        arr = new int[A];
        dp = new int[A];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < A; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < A; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr [i] && dp[i] < dp[j]+1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        for (int i = 1; i < A; i++) {
            max = max < dp[i] ? dp[i] : max;
        }
        
        bw.write(max + "\n");
        bw.flush();
        bw.close();
        br.close();

    }

}