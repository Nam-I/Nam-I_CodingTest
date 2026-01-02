/** 백준 15663.S2.N과 M(9) - prev 같은 depth 이전 사용 값 저장 java 풀이
 * ## 체감 난이도 ##
 * - N과 M 시리즈를 꽤 풀어봤지만 이번에는 동일한 숫자가 존재하는 경우를 고려해야해서
 * 조금 더 생각할 거리가 있었다.
 * - LinkedHashSet 자료형의 특징을 알 수 있는 문제이다.
 * ## 핵심 내용 ##
 * - prev 변수를 두어 같은 depth 에서 이전에 사용한 수를 확인하는 방법이 시간/공간 측면에서 더 좋다.
 * - prev 변수로 같은 depth 에서 이전에 사용한 값을 저장해두면 중복 수열 생성 자체를 막을 수 있다.
 * - 풀이에서 자동 오름차순 정렬하는 TreeSet 사용하지 않고 LinkedHashSet을 쓴 이유
 * : 문자열 정렬이기 때문에 숫자 상 오름차순이 아니라 문자열 상 오름차순 정렬이 되어
 * 답이 틀려진다. -> LinkedHashSet 은 입력 순서대로 정렬, 중복 제거가 가능하므로 문제에 적절
 **/
import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] resultArr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        resultArr = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        backtracking(0);

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();

    }

    private static void backtracking(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(resultArr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            if (visited[i] || prev == arr[i]) continue;

            visited[i] = true;
            resultArr[depth] = arr[i];
            prev = arr[i];
            backtracking(depth + 1);
            visited[i] = false;
        }

    }
}
