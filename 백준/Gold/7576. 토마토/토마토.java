/** 백준 7576.G5.토마토 **/
/**
 * ## 체감 난이도 ##
 * - 아주 기본적인 bfs 문제라서 어렵지 않게 풀었다.
 * - 동일한 이름의 다른 문제를 풀어봐서 어떻게 접근해야할지 정확히 알고있었다.
 * - 이 문제는 쉽게 풀었지만 문제 형식이 바뀌면 bfs 문제인지 쉽게 알 수 있을지는 의문
 * - bfs 문제를 어떤식으로 푸는지 감은 잡고 있다면 위, 아래, 상, 하, 좌, 우 다 고려해야 하는 문제인
 * 7569번 토마토를 푸는게 더 좋을거 같다.(더 조건이 많은 7569번과 난이도가 왜 같은지는 의문..)
 * ## 핵심 내용 ##
 * - 일수를 배열의 요소로 누적하고 있기 때문에 배열 값 갱신 시 day도 같이 갱신해주면
 * Math.max 로 매번 대소비교 하는 연산을 하지 않아도 된다.**/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
public class Main {
    static int M, N;
    static int[][] box;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> q = new LinkedList<>();
    static int day = 1; // 토마토 숙성 기간 결과 변수

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];

        for (int i = 0; i < N; i++) { // 토마토 초기 상태 입력 저장
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) { // 익은 토마토이면 큐에 넣어서 탐색 후보 지정
                    q.offer(new int[]{i, j});
                }
            }
        }

        bfs(); // bfs 수행하면서 토마토 숙성

        if (check()) { // 토마토가 모두 익었는지 확인
            --day; // 이미 모든 토마토가 익은 상태면 0일 이므로. 즉 숙성 시작일이 1일이 아니라 0일임.
            bw.write(day + "\n");
        }
        else {
            bw.write(-1 + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    /** bfs 수행하며 토마토 최대 숙성 기간 확인 **/
    private static void bfs() {
        int[] point;
        int nx, ny;
        while (!q.isEmpty()) {
            point = q.poll();

            for (int i = 0; i < 4; i++) {
                nx = point[0] + dx[i];
                ny = point[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && box[nx][ny] == 0) { // 배열 범위 내부이고 안익은 토마토이면
                    box[nx][ny] += box[point[0]][point[1]] + 1; // 이동 좌표에 현재 좌표 요소 + 1을 해서 숙성 기간 체크
                    q.offer(new int[]{nx, ny});
                    day = box[nx][ny]; // 숙성 기간 갱신(가장 최근에 갱신된 날짜가 최대 숙성 기간)
                }
            }
        }
    }

    /** 안익은 토마토가 있는지 확인 **/
    private static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}