/**백준 1967.G4.트리의 지름 - BFS java 풀이
 * ## 체감 난이도 ##
 * - 처음에 BFS 라고 생각했는데 알고리즘 분류가 DFS 여서 DFS 로만 풀리는 줄 알고
 * 오히려 헤맸다.
 * - 결국 트리의 끝과 끝 즉 리프 노드들 중 가장 가중치가 높은 거리를 찾는 문제이다.
 * ## 핵심 내용 ##
 * - 임의의 노드를 시작점으로 잡아도 결국 가중치가 높은 리프 노드 하나는 동일하게 구해진다.
 * - 구한 리프 노드 하나에서 가장 가중치가 높은 리프 노드를 다시 탐색하면 최장 거리가 된다.
 * - 재귀는 스택 오버플로우 위험이 있으므로 가능하면 BFS 로 풀기 선호
 * - 무방향 즉 상호 연결 그래프는 입력시 양쪽에 값을 다 넣어주어야 한다.
 * - 합계/누적 을 구해야할 때는 long 자료형으로 선언하는 것이 안전하다. -> 여기서는 dist, maxDist
 **/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main  {
    static class Node {
        int next;
        int cost;

        Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }
    static class Distance{
        int node;
        long dist;

        Distance(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static int N;
    static List<Node>[] tree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            tree[start].add(new Node(next, cost));
            tree[next].add(new Node(start, cost));
        }

        if (N == 1) {
            bw.write(0 + "\n");
        }
        else {
            Distance firstPoint = bfs(1);
            Distance secondPoint = bfs(firstPoint.node);

            bw.write(secondPoint.dist + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static Distance bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        long[] dist = new long[N + 1];
        boolean[] visited = new boolean[N + 1];

        visited[start] = true;
        q.offer(start);
        int curNode;
        int farNode = start;
        long maxDist = 0;
        while(!q.isEmpty()) {
            curNode = q.poll();

            for (Node node : tree[curNode]) {
                if (visited[node.next]) continue;

                visited[node.next] = true;
                dist[node.next] = dist[curNode] +  node.cost;
                q.offer(node.next);

                if (dist[node.next] > maxDist) {
                    maxDist = dist[node.next];
                    farNode = node.next;
                }
            }
        }

        return new Distance(farNode, maxDist);
    }
}