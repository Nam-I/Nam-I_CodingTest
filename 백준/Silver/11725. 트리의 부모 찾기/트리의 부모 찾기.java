//백준: 11725번.S2.트리의 부모 찾기
/**
 * ## 체감 난이도
 * - 표기 그대로의 난이도로 느껴졌다.
 * - 어떤 식으로 접근하는지는 떠오르는데 세세한 부분을 아직 잘 못하는거 같다.
 * ## 핵심 내용
 * - 배열에 ArrayList 를 초기화 하는 방식 기억해두기
 * - 사실 구현 자체는 어려운 문제가 아니었는데.. 이 정도는 좀 쉽게 풀 수 있어야 할거 같다.
 * - 루트 노드에서 내려가면 쉽게 각 노드의 루트를 얻을 수 있다.
 * - DFS, BFS 두 방식 모두 풀 수 있다.(나는 BFS 로 풀 수 있는 문제는 웬만하면 BFS 로 푸는 편)**/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
    private static int N;
    private static ArrayList<Integer> tree[];
    private static Queue<Integer> q = new LinkedList();
    private static int[] result;
    private static boolean[] visited;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        result = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            tree[start].add(end); // 상호 연결 기록
            tree[end].add(start);
        }

        bfs(1); // 루트 노드 부터 내려가기

        for(int i = 2; i <= N; i++) {
            bw.write(result[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    public static void bfs(int root) {
        int curNode;
        q.add(root);
        visited[root] = true;

        while (!q.isEmpty()) {
            curNode = q.poll();

           for (int next : tree[curNode]) {
               if (!visited[next]) { // 루트 부터 타고 내려오므로 처음 방문 했을 시가 현재 노드의 부모 노드임
                   q.offer(next);
                   result[next] = curNode;
                   visited[next] = true;
               }
           }
        }
    }
}