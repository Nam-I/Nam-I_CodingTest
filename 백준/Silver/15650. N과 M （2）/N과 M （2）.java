/** 백준 15650.S3.N과 M (2) - 완전 탐색, 백트래킹 java 풀이
 * ## 체감 난이도 ##
 * - 풀이의 전체적인 틀은 맞게 했는데 약간 생각에 착오가 있어서 푸는데 조금 걸렸다.
 * - for 문을 돌면서 모든 경우의 수를 다 체크를 하게 된다.
 * ## 핵심 내용 ##
 * - 이게 왜 백트래킹?
 * -> 가지를 제함하는 규칙(pruning)이 있기 때문
 * : 가능한 모든 수열을 만들고 걸러내는게 아니라, 시작 수가 큰 경우와 같은 애초에 불필요한 경로를 안가는 방식이기 때문
 * - 탐색 중에 조건으로 경로를 제한하고 조건에 맞지 않으면 되돌아오며 탐색 -> 백트래킹
 * - 가능한 모든 조합을 모두 찾아야함 -> 완전 탐색
 * - "백트래킹은 완전 탐색을 구현하는 대표적인 방법론"
 **/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main  {
    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        dfs(1, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int start, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i]).append(i == M - 1 ? "\n" : " ");
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            arr[depth] = i; // for문 + 재귀로 결과 배열의 각 위치에 가능한 모든 숫자를 넣게 된다.
            dfs(i + 1, depth + 1);
        }
    }
}