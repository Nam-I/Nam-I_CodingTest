/** 백준 3055.G5.탈출 - java 풀이 **/
/**
 * ## 체감 난이도 ##
 * - 딱 G5 정도로 느껴졌다.
 * - 구현의 큰 틀은 떠올릴 수 있었지만, size() 를 구해서 이번 타임에 갈 수 있는 곳만 확인하는 구현은 못 떠올렸다.
 * - 디테일한 구현을 고민할 수 있는 문제다.
 * ## 핵심 내용 ##
 * - Queue 를 두개 사용해서 푸는 문제
 * - 문제 조건 잘 생각해서 디테일한 구현
 * - 나는 시작,끝 좌표쌍을 배열로 묶어서 저장하려고 했는데 코테에서는 불변 객체로 저장하는게 좋다고 하여 startR, startC 이런 식으로 저장함
 * - 현재 큐 크기만큼만 탐색하기
 * - 큐 중복 좌표 저장 예측해 미리 방문 표시하기
 **/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static int startR, startC;
    static int endR, endC;
    static Queue<int[]> q = new LinkedList<>();
    static Queue<int[]> water = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    startR = i; startC = j;
                    q.offer(new int[]{i, j});
                    map[i][j] = '.'; // 고슴도치 출발하므로 시작 지점 빈칸으로
                } 
                else if (map[i][j] == 'D') {
                    endR = i; endC = j; // 종료 지점 좌표 저장
                } 
                else if (map[i][j] == '*') {
                    water.offer(new int[]{i, j}); // 물 영역 water 큐 저장
                }
            }
        }

        boolean[][] visited = new boolean[R][C];
        visited[startR][startC] = true; // 고슴도치 시작 지점 방문 true

        int time = 0;
        while (!q.isEmpty()) {
            
            // 물 확장 먼저
            int wSize = water.size(); // 이번 타임에 확장할 수 있는 영역만 계산
            for (int i = 0; i < wSize; i++) {
                int[] waterArea = water.poll();
                
                for (int d = 0; d < 4; d++) {
                    int nx = waterArea[0] + dx[d];
                    int ny = waterArea[1] + dy[d];
                    
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue; // 배열 범위 밖이면 건너뜀
                    
                    if (map[nx][ny] == '.') { // 빈칸이면 물 범위 확장
                        map[nx][ny] = '*';
                        water.offer(new int[]{nx, ny});
                    }
                }
            }

            
            // 현재 큐 크기만큼 고슴도치 이동(현재 이동 가능한 영역에서 다음 타임 이동 가능 영역 계산)
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int[] curLocation = q.poll();
                int curX = curLocation[0], curY = curLocation[1];

                for (int d = 0; d < 4; d++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue; // 배열 범위 밖이면 건너뛰기
                    if (visited[nx][ny]) continue; // 방문한 위치면 건너뛰기

                    // 도착지로 이동 가능
                    if (map[nx][ny] == 'D') {
                        ++time; // 이 이동은 다음 분에 도달하므로 time+1
                        bw.write(time + "\n");
                        bufferedClose(); // Buffered br, bw 닫기
                        return;
                    }
                    // 빈칸이면 이동 가능(이미 물로 바뀌어 있으면 불가)
                    if (map[nx][ny] == '.') {
                        visited[nx][ny] = true; // 큐에 중복으로 추가되는 것을 방지하기 위해 미리 다음 타임 방문 표시
                        q.offer(new int[]{nx, ny});
                    }
                }
            }

            time++;
        }

        bw.write("KAKTUS\n");
        bufferedClose();
    }

    /**Buffered br, bw 닫는 메서드**/
    private static void bufferedClose() throws Exception{
        bw.flush();
        bw.close();
        br.close();
    }
}