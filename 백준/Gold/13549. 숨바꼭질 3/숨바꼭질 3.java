/** 백준 13549.G5.숨바꼭질 3
 * ## 체감난이도 ##
 * - 역시나 늘 방향을 잘못 잡은 것처럼 현재 위치로 나눠서 어떻게 하면 더 빠른가?
 * 싶어서 푸는데 더 헤맸다.
 * - 큐에 넣는 순서가 중요하다는건 파악했지만 늘 2를 곱하는 경우를 먼저 큐에 넣는게 더 빠른 길을 찾는게 맞나?
 * 의문이 들어서 그렇게 푸는건 아닐거다. 라고 생각했는데 그냥 그게 정답이었다.
 * ## 핵심 내용 ##
 * - 비가중치 최단거리 -> bfs
 * - 한칸 앞으로 가는 것보다 목적지를 넘어선 경우 한칸 뒤로 가는 것을 우선 고려해야힌다.**/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayDeque;
public class Main {
    static int N, K, result;
    static boolean[] visited = new boolean[100001];
    static Queue<int[]> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        q.offer(new int[]{N, 0});
        visited[N] = true;
        bfs();

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();

    }

    private static void bfs() {
        int[] curState;
        int next, time;
        while(!q.isEmpty()) {
            curState = q.poll();

            if (curState[0] == K) {
                result = curState[1];
                return;
            }

            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    next = 2 * curState[0];
                    time = curState[1];
                }
                else if (i == 1) { // 목적지를 넘어선 경우 한칸 뒤로 가는 것을 앞으로 가는 것보다 먼저 고려
                    next = curState[0] - 1;
                    time  = curState[1] + 1;
                }
                else {
                    next = curState[0] + 1;
                    time = curState[1] + 1;
                }

                if (next >= 0 && next <= 100000 && !visited[next]) {
                    q.offer(new int[] {next, time});
                    visited[next] = true;
                }
            }
        }
    }
}