/** 백준 2178.S1.미로 탐색 - java 풀이 **/
/**
 * ## 체감 난이도##
 * - 아주 기본적인 bfs 비가중치 빨리는 못풀어도 막힘없이 풀 수 있었다.
 * - S1 인데 S2 보다 쉬운듯..? 정답 비율이 높은데는 이유가 있다.
 * ## 핵심 내용 ##
 * - bfs 기본기 탄탄히.
 * - 좌표 자료형 클래스 객체 선언해서 Queue 에 좌표쌍 추가하기.
 * - visited 배열로 방문 확인 & 누적 경로 저장 동시에 하는 아이디어는 괜찮은 듯(다른 코드 참고 안함)
 * **/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class Point { // 좌표쌍 저장할 클래스
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M;
    static int[][] maze;
    static Queue<Point> q = new LinkedList<>();
    static int[][] visited;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M]; // 미로 0으로 초기화
        visited = new int[N][M]; // 방문 배열 0으로 초기화

        for (int i = 0; i < N; i++) {
            String s = br.readLine(); // 미로는 공백 없이 입력되므로 문자열로 입력 받기

            for (int j = 0; j < M; j++) {
                maze[i][j] = s.charAt(j) - '0'; // 아스키코드를 사용해 문자를 정수로 바꾸기
            }
        }

        visited[0][0] = 1; // 시작 좌표 방문 표시 및 기본 거리 입력
        q.offer(new Point(0, 0)); // 시작 좌표쌍 큐에 넣기
        bfs();

        bw.write(visited[N-1][M-1] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }


    private static void bfs() {
        int[] dx = {1, -1, 0, 0}; // 상, 하, 좌, 우
        int[] dy = {0, 0, -1, 1};

        Point curPoint; // 현재 좌표쌍 저장 변수
        int nx, ny; // 이동할 좌표 저장 변수

        while (!q.isEmpty()) {

            curPoint = q.poll();

            for (int i = 0; i < 4; i++) {
                nx = curPoint.x + dx[i]; // 이동할 좌표 row
                ny = curPoint.y + dy[i]; // 이동할 좌표 col
                
                if (nx > -1 && ny > -1 && nx < N && ny < M) {
                    if (visited[nx][ny] == 0 && maze[nx][ny] == 1) {
                        q.offer(new Point(nx, ny)); // 배열 내부이면서 방문하지 않고 갈 수 있는 길이면 큐에 추가
                        visited[nx][ny] = visited[curPoint.x][curPoint.y] + 1; // 누적 칸 수 + 1

                        if (nx == N - 1 && ny == M - 1) {
                            return; // 출구인 가장 끝 좌표에 도착하면 반환
                        }
                    }

                }
            }
        }
    }
}