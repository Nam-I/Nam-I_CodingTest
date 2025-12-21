/**백준 14500.G4.테트로미노 - java 'ㅗ' 경우만 따로 체크 풀이**/
/**
 * ## 체감 난이도 ##
 * - 위치 탐색 문제라 어떤 방식으로 풀어야하는지는 바로 알아챌 수 있었다.
 * - 그러나 백트래킹 문제인 것은 알아채지 못해서 바로 정답을 풀어내지는 못했다.
 * - G4 문제 치고는 어렵지 않은 문제이나 백트래킹 개념을 바로 떠올로기 힘들어서 그런거 같다.
 * - 완전탐색 문제는 정말 전부를 탐색하는게 맞나? 싶은 두려움을 줘서 시도를 잘 안하게되는거 같다.
 * ## 핵심 내용 ##
 * - 문제 의도는 백트래킹으로 풀라는거 같은데 백트래킹으로 안풀고 dfs 재귀 + 'ㅗ' 체크용 함수 이 조합이
 * 더 빠르다.
 * - 백트래킹 풀이법 잘 기억하기(방문 체크하고 반환시 방문 취소)
 * - count == 2 일 때 바로 다음 위치로 이동하지 않고 현재 위치에서 한 번 더 탐색을 해서
 * 'ㅗ' 형태도 탐색할 수 있도로 한다.
 * - 기본적인 dfs, bfs 풀이 방식으로는 계속 다음칸으로 이동하면서 탐색하므로 현재 위치에서 다음 갈 수 있는
 * 한칸만 탐색한다.
 * - 이 문제는 한 경우의 수당 깊이가 4가 최대이므로 완전 탐색으로 풀이가 가능하다.
 **/
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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

        bw.write(max + "\n");
        bw.flush();
        bw.close();
        br.close();
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
