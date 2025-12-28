/**백준 15654.S3.N과 M (5)
 * ## 체감 난이도 ##
 * - 적절한 난이도 선정인거 같다.
 * ## 핵심 내용 ##
 * - 조건을 정해두고 조건에 맞지 않으면 돌아가기 -> 백트래킹
 * - 방문 체크를 할 경우 돌아갔을 때 방문 false 하여 원복시킴
 **/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
public class Main {
    static int N, M;
    static int[] arr;
    static int[] result;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        result = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backtracking(0, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    private static void backtracking(int idx, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(i == M - 1 ? "\n" : " ");
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            result[depth] = arr[i];
            visited[i] = true;
            backtracking(i, depth + 1); 
            visited[i] = false; // 탐색 완료 후 원복
        }
    }
}