/**백준 16928.G5.뱁과 사다리 게임 - java 풀이**/
/** ## 체감 난이도 ##
 * - 그냥 시작점 부터 계속 주사위 굴려가면서 찾으면 되는 문제였는데 너무 어렵게 생각했다.
 * - 생각보다 여러 경우의 수가 있다보니 조금 어렵게 느껴졌다.
 * ## 핵심 내용 ##
 * - 이동 수단이 있는 곳은 시작점을 배열의 인덱스, 도착점을 배열의 요소로 저장한다.
 * - 이동 수단의 시작점은 다르면서 도착점은 같을 수 있으므로 시작점이 방문하지 않은 곳이면서 
 * 이동수단이 있는 곳이면 도착점은 바로 큐에 추가하고 방문 체크를 한다.
 **/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
public class Main {
    static int move;
    static int[] board = new int[101]; // 1 부터 시작 끝은 무조건 100임
    static boolean[] visited = new boolean[101];
    static Queue<int[]> q = new LinkedList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        move = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

        for (int i = 0; i < move; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            board[start] = end;
        }

        q.offer(new int[]{1, 0}); // 시작지점, 주사위 굴린 횟수
        visited[1] = true;

        bfs();
        bw.flush();
        bw.close();
        br.close();
    }

    /** bfs 수행 **/
    private static void bfs() throws IOException {
        int[] current; // 현재 위치, 현재 주사위 굴린 횟수
        int next;
        while (!q.isEmpty()) {
            current = q.poll();

            if (current[0] == 100) { // 현재 위치 100이면 주사위 굴린 횟수 출력 후 종료
                bw.write(current[1] + "\n");
                return;
            }

            for (int i = 1; i < 7; i++) { // 주사위 1~6 까지 굴려보기
                next = current[0] + i;

                if (next <= 100 && !visited[next]) { // next가 범위 내부이고 방문하지 않은 곳이면
                    if (board[next] > 0) { // 만약 이동 수단이 있는 칸이면 바로 그 칸으로 이동
                        visited[next] = true; // 현재 위치는 방문 처리
                        next = board[next]; // board[next] 가 next 가 됨. 즉 바로 이동한 칸이 next로 바뀜.
                    }

                    // 범위 내부이고 방문하지 않은 위치면 큐에 추가
                    // 이동 수단 이용 시 도착 지점이 같더라도 시작 지점이 다르면 다른 경우의 수이다.
                    // 따라서 도착지점은 방문 여부 상관 없이 큐에 추가된다.
                    q.offer(new int[]{next, current[1] + 1});
                    visited[next] = true;

                }
            }
        }
    }
}