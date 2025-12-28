/** 백준.1753.G4.최단경로
 * ## 체감 난이도 ##
 * - 문제가 뭔가 조건도 많고 쉽게 읽히지 않아서 푸는데 굉장히 돌아왔다.
 * - 모든 1부터 V까지 번호가 매겨져 있다길래 1부터 V 범위 내의 숫자를 가진 정점이 존재한다는 뜻인 줄 알고
 * - 연속된 숫자 정점인 줄 몰라서 더 어렵게 생각했다.
 * ## 핵심 내용 ##
 * - 리스트 배열 선언 형태 잘 기억해두기
 * - 클래스를 두개 두어서 각 노드별 가중치와 dist 누적 가중치를 혼용하지 않도록 구현함.
 * - visited 가 없는 다익스트라 구현 형태 기억해두기
 * - 다익스트라 : 음수 가중치가 없는 그래프에서 한 시작 정점으로부터 모든 정점까지의 최단거리를 구하는 알고리즘
 * - **/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
    static class Node {
        int end, cost;

        Node (int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    static class State implements Comparable<State> {
        int node, dist;

        State(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(dist, o.dist);
        }
    }

    static int V, E, K;
    static List<Node>[] graph; // 코드 유연성을 위해 List 로 선언
    static int[] dist;
    static final int INF = 1_000_000_000; //'_' 사용해도 정수로 인정
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        graph = new ArrayList[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i ++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, cost));
        }

        dist[K] = 0;
        dijkstra();

        for (int i = 1; i <= V; i++) {
            bw.write(dist[i] == INF ? "INF\n" : dist[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
    private static void dijkstra() {
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(K, 0));

        State curState;

        while (!pq.isEmpty()) {
            curState = pq.poll();

            if (curState.dist != dist[curState.node]) continue; // PQ 에서 꺼낸 정보가 최신이 아니면 버림

            for (Node node : graph[curState.node]) { // 현재 노드와 연결된 노드 탐색
                int newDist = curState.dist + node.cost; // 현재 누적 가중치 + 다음 노드로 가는 가중치

                if (newDist < dist[node.end]) { // 기록된 다음 노드 가중치 보다 newDist가 최단 거리이면 업데이트
                    dist[node.end] = newDist;
                    pq.offer(new State(node.end, newDist));
                }
            }
        }
    }
}