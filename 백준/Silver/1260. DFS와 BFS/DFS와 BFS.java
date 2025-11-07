// 백준 1260.S2.DFS와 BFS - java 풀이
/**
 * ## 체감 난이도: S2 난이도 정도로 느껴졌다. 근데 아직 자바 문법이 익숙치 않아서 더 많은 연습 해야할 듯
 * ## 핵심 내용
 * - DFS 는 보통 재귀, stack
 * - BFS 는 queue
 * - 이 문제에서 DFS 부분을 stack 으로도 풀 수 있다.
 */

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Queue;

public class Main{

    static int N, M, V;
    static int[][] arr;
    static boolean[] visited;
    static Queue<Integer> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main (String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[start][end] = arr[end][start] = 1;

        }

        dfs(V);
        sb.append("\n");

        visited = new boolean[N + 1];

        bfs();

        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int start){
        visited[start] = true;
        sb.append(start + " ");

        for (int i = 1; i <= N; i++) {
            if (arr[start][i] == 1 && !visited[i]){
                dfs(i);
            }
        }
    }

    public static void bfs(){
        q.offer(V);
        visited[V] = true;

        int curNode;

        while(!q.isEmpty()){

            curNode = q.poll();
            sb.append(curNode + " ");

            for (int i = 1; i <= N; i++) {
                if(arr[curNode][i] == 1 && !visited[i]){
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

}
