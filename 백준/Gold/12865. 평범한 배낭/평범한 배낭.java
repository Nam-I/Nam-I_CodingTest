/** 백준 12865.G4.평범한 배낭 - DP java 풀이
 * ## 체감 난이도 ##
 * - 아직 익숙하지 않은 유형의 문제라서 어렵게 느껴졌다.
 * - 그러나 문제 풀이 자체가 길거나 구현이 너무 힘들지는 않아서 G4 난이도를 받은거 같다.
 * ## 핵심 내용 ##
 * - 배낭 문제가 아주 유명한 알고리즘 문제라는데 더 알아봐야겠다.
 * - 두번째 반복문은 반드시 감소하는 형태여야한다. 안그러면 같은 물건이 두번 들어가는 걸로 책정될 수 있다.
 * - DP 문제는 아직 어렵게 느껴진다. 좀 더 많이 풀어봐야지..
 **/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, K, W, V;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];

        for (int i = 0; i < N; i++) { 
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            // 문제에서 물건의 입력 순서는 중요하지 않으므로 입력 들어온 대로 점화식 확인
            for (int curWeight = K; curWeight >= W; curWeight--) {
                dp[curWeight] = Math.max(dp[curWeight], dp[curWeight - W] + V);
                // 동일한 무게일 때 기존 물건들로 측정한 가치 VS 새로 입력된 물건을 추가했을 때 가치 비교
            }
        }

        bw.write(dp[K] + "\n");
        bw.flush();
        bw.close();
    }
}