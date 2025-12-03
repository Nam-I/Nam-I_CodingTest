/** 백준 18352.S2.특정거리의 도시 찾기 - java 풀이 **/
/**
 * ## 체감 난이도 ##
 * - 접근은 할 수 있으나 완벽히 풀기엔 아직 부족한 실력 (dfs 문제인 줄 알았다..)
 * ## 핵심 내용 ##
 * - BFS 문제이다. 이전 거리 값을 누적하면서 거리 계산 But, 비가중치
 */

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N, M, K, X;
    static ArrayList<Integer> arr[];
    static Queue<Integer> q = new LinkedList<>();
    static int[] distance;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());


        arr = new ArrayList[N+1];
        distance = new int[N+1];
        Arrays.fill(distance, -1);
        distance[X] = 0;

        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i <M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[start].add(end);
        }


        q.offer(X);
        bfs();

        boolean found = false;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) {
                bw.write(i + "\n");
                found = true;
            }
        }

        if(!found) {
            bw.write(-1 + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    private static void bfs() {
        int curNode;

        while (!q.isEmpty()) {
            curNode = q.poll();

            for (int nextNode : arr[curNode]) {
                if (distance[nextNode] == -1) {
                    q.offer(nextNode);
                    distance[nextNode]  = distance[curNode] + 1;
                }
            }
        }


