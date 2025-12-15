/**백준 10026.G5.적록색약**/
/**
 * ## 체감 난이도 ##
 * - 열심히 하니까 풀 수 있는 정도였다.
 * - G5 난이도 정도로 적절하게 느껴졌다.
 * - 이제 bfs 문제는 어느 정도 감이 잡히는거 같다.
 * - 문제가 풀릴 듯 말듯 해서 끝까지 잡고 있었더니 결국 정답 참고 없이 풀었다.
 * ## 핵심 내용 ##
 * - 색약자가 일반인 보다 구역을 더 넓게 쓰므로 색약자 방문 여부 부터 체크(그러나 동시에 일반인도 같이 체크됨)
 * - 만약 일반인 먼저 방문 여부를 체크한다면 색약자 구역이 정답보다 더 많게 체크될 수 있다.(일반인은 더 많은 범위를 보기 때문)
 * - 큐를 두개 두어 일반 범위가 아니면서 색약자 구역이면 specialQ 에 추가하여 색약자 구역 추가 체크
 * - 색약자 구역을 먼저 방문 체크해서 일반인 구역은 방문하지 않은 요소가 있을 수 있으므로 마지막에 방문 체크
 * - 다른 풀이를 아예 참고하지 않아서 다른 더 좋은 풀이가 있는지 확인해봐야겠다.
 **/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
public class Main {
    static int N;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    static char[][] picture;
    static Queue<int[]> normalQ = new LinkedList<>(); // 일반인 큐
    static Queue<int[]> specialQ = new LinkedList<>(); // 색약자 큐
    static boolean[][] normalVisited; // 일반인 방문
    static boolean[][] specialVisited; // 색약자 방문
    static int normal = 0, special = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        picture = new char[N][N];
        normalVisited = new boolean[N][N]; 
        specialVisited = new boolean[N][N];

        char[] line;
        for (int i = 0; i < N; i++) {
            line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                picture[i][j] = line[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (specialVisited[i][j]) continue; // 색약자 먼저 방문 체크

                // 사실상 방문하지 않은 구역일 때만 큐에 추가하고 bfs 로 탐색하는 구조
                normalQ.offer(new int[]{i, j}); // 색약자가 방문하지 않은 곳이면 일반인은 당연히 방문하지 않은 구역임
                normalVisited[i][j] = true; 
                specialVisited[i][j] = true;
                bfs(picture[i][j]); // 사실상 bfs 는 방문 체크 용도
                ++normal; // 일반인 구역 추가. 일반인은 더 많은 구역을 볼 수 있으므로 색약자가 볼 수 있는 구역은 당연히 일반인도 볼 수 있는 구역.
                ++special; // 색약자 구역 추가.
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (normalVisited[i][j]) continue;

                // 일반인 방문 체크 안된 곳 탐색
                // bfs() 코드에서 일반인은 R, G 컬러를 다른 컬러로 보기 때문에 방문 체크 안돼있음
                normalQ.offer(new int[]{i, j});
                normalVisited[i][j] = true;

                bfs(picture[i][j]);
                ++normal; // 일반인 구역 추가
            }
        }

        bw.write(normal + " " + special + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    /** bfs 수행 **/
    private static void bfs(char mainColor) {
        int[] curPoint;
        int nx, ny;
        while (!normalQ.isEmpty()) { // 일반인 bfs But, 조건에 맞는 경우 색약자 방문 체크도 같이함.
            curPoint = normalQ.poll();

            for (int i = 0; i < 4; i++) {
                nx = curPoint[0] + dx[i];
                ny = curPoint[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !normalVisited[nx][ny]) { // 배열 범위 내부이고 방문하지 않은 곳인 경우
                    if (mainColor == picture[nx][ny]) { // 기준 색과 이동 가능한 곳의 색이 같으면 
                        normalQ.offer(new int[]{nx, ny});
                        normalVisited[nx][ny] = true;
                        specialVisited[nx][ny] = true; // 색이 동일한 경우 일반인과 색약자 모두 같은 구역이 맞음.
                    }
                    else if (mainColor != 'B' &&  picture[nx][ny] != 'B' && !specialVisited[nx][ny]) { // 기준 색과 이동 가능한 곳의 색이 다르면서 두 색 모두 'B'가 아닌 경우
                        specialQ.offer(new int[]{nx, ny}); // 적록색약자는 R,G 는 같은 구역이므로 색약자 큐에만 추가 및 방문 체크
                        specialVisited[nx][ny] = true;
                    }
                }
            }
        }

        while (!specialQ.isEmpty()) { // 색약자 구역만 다시 체크
            curPoint = specialQ.poll();

            for (int i = 0; i < 4; i++) {
                nx = curPoint[0] + dx[i];
                ny = curPoint[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !specialVisited[nx][ny]) {
                    if (picture[nx][ny] != 'B') { // 기준 색이 'B' 가 아닌 경우만 색약자 큐에 추가되므로 이동한 곳 색이 'B'가 아닌지만 체크하면 됨.
                        specialQ.offer(new int[]{nx, ny});
                        specialVisited[nx][ny] = true;
                    }
                }
            }
        }

    }
}