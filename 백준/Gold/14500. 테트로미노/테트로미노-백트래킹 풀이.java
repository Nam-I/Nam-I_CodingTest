/**백준 14500.G4.테트로미노 - java 백트래킹 풀이**/
/**
 * ## 체감 난이도 ##
 * - 위치 탐색 문제라 어떤 방식으로 풀어야하는지는 바로 알아챌 수 있었다.
 * - 그러나 백트래킹 문제인 것은 알아채지 못해서 바로 정답을 풀어내지는 못했다.
 * - G4 문제 치고는 어렵지 않은 문제이나 백트래킹 개념을 바로 떠올로기 힘들어서 그런거 같다.
 * - 완전탐색 문제는 정말 전부를 탐색하는게 맞나? 싶은 두려움을 줘서 시도를 잘 안하게되는거 같다.
 * ## 핵심 내용 ##
 * - 백트래킹 풀이법 잘 기억하기(방문 체크하고 반환시 방문 취소)
 * - count == 2 일 때 바로 다음 위치로 이동하지 않고 현재 위치에서 한 번 더 탐색을 해서
 * 'ㅗ' 형태도 탐색할 수 있도로 한다.
 * - 기본적인 dfs, bfs 풀이 방식으로는 계속 다음칸으로 이동하면서 탐색하므로 현재 위치에서 다음 갈 수 있는
 * 한칸만 탐색한다.
 * - 이 문제는 한 경우의 수당 깊이가 4가 최대이므로 완전 탐색으로 풀이가 가능하다.
 **/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxValue = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
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
                visited[i][j] = false; // 다음 경우의 수를 확인을 위해 현재 위치에서 모든 경우를 탐색하면
                // 방문 false 로 바꿔 다음 위치에서 모든 경우의 수 탐색
            }
        }

        bw.write(maxValue + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int total, int count) {
        if (count == 4) {
            maxValue = Math.max(maxValue, total);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                if (count == 2) { // 'ㅗ' 형태를 탐색하기 위해, 현재 위치에서 두 칸을 탐색하기 위해
                    visited[nx][ny] = true;
                    dfs(x, y, total + board[nx][ny], count + 1);
                    visited[nx][ny] = false;
                }

                visited[nx][ny] = true;
                dfs(nx, ny, total + board[nx][ny], count + 1);
                visited[nx][ny] = false;
            }
        }
    }
}
