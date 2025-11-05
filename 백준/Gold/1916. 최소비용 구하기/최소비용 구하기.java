// 백준 1916.G5.최소비용 구하기 - java 풀이
/**
 * ## 체감 난이도: 적절한 난이도 인거 같다. 근데 아직 새로운 클래스를 만들어서 푸는데
 * 익숙하지 않아서 당분간 빡세게 연습해야겠다.
 * ## 핵심 내용
 * - 다익스트라 알고리즘 잘 알기
 * - 우선순위큐
 * - 클래스 객체 생성해서 풀기
 * **/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    int end;
    int weight;

    Node(int end, int weight){
        this.end = end;
        this.weight = weight;
    }

    @Override
            public int compareTo(Node o){
        return weight - o.weight;
    }
}

public class Main {

    static int N, M;
    static ArrayList<ArrayList<Node>> arr;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();
        dist = new int[N+1];
        visited = new boolean[N+1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr.get(start).add(new Node(end, weight));
        }

        st = new StringTokenizer(br.readLine());
        int targetStart = Integer.parseInt(st.nextToken());
        int targetEnd = Integer.parseInt(st.nextToken());

        bw.write(dijkstra(targetStart, targetEnd) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    /** 다익스트라 알고리즘 **/
    public static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new boolean[N+1];
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int curNode = cur.end;

            if(!visited[curNode]){
                visited[curNode] = true;

                for(Node nextNode : arr.get(curNode)){
                    if(!visited[nextNode.end] && dist[nextNode.end] > dist[curNode] + nextNode.weight){
                        dist[nextNode.end] = dist[curNode] + nextNode.weight;
                        pq.offer(new Node(nextNode.end, dist[nextNode.end]));
                    }
                }
            }
        }

        return dist[end];
    }
}
