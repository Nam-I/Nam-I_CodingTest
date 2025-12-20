import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, board[i][j], 1);
                visited[i][j] = false;

                checkT(i, j); // ㅗ 모양 처리
            }
        }

        System.out.println(max);
    }

    static void dfs(int x, int y, int sum, int depth) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, sum + board[nx][ny], depth + 1);
            visited[nx][ny] = false;
        }
    }

    // 중심 (x,y)에서 4방향 중 3개를 선택하는 최대값 = ㅗ 모양
    static void checkT(int x, int y) {
        int cnt = 0;
        int sum = board[x][y];
        int minNeighbor = Integer.MAX_VALUE;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            cnt++;
            int v = board[nx][ny];
            sum += v;
            minNeighbor = Math.min(minNeighbor, v);
        }

        // 이웃이 3개 미만이면 T자 불가
        if (cnt < 3) return;

        // 이웃이 4개면 그중 하나를 빼서(가장 작은 값) 3개를 선택
        if (cnt == 4) sum -= minNeighbor;

        max = Math.max(max, sum);
    }
}