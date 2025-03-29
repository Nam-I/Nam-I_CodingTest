import java.util.*;

class Solution {
    //인접리스트 선언
    ArrayList<Integer>[] graph;
    int answer;
    public int solution(int n, int[][] wires) {

        answer = n;
        graph = new ArrayList[n+1]; //인접리스트 크기지정(1부터 값을 입력하므로 노드수+1)
        //참고
        //Array 배열은 고정길이(속도가 더 빠를 수 있음)
        //ArrayList 는 가변길이

        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        //노드들 연결 정보값 저장
        for(int i = 0; i < wires.length; i++) {
            int start = wires[i][0];
            int end = wires[i][1];

            graph[start].add(end); //노드 마다 연결되어있는 노드 줄줄이 값 저장
            graph[end].add(start);
        }

        //엣지를 하나씩 끊어보며 연결노드 수 세어보기
        for(int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];

            boolean[] visited = new boolean[n + 1];

            graph[v1].remove(Integer.valueOf(v2)); //remove 입력 값이 인덱스가 아니라 숫자에 해당하는 값
            graph[v2].remove(Integer.valueOf(v1));//그대로를 삭제하므로 Integer.valueOf() 사용.

            int cnt = dfs(1, visited);

            answer = Math.min(answer, Math.abs(cnt*2 - n));

            graph[v1].add(v2);
            graph[v2].add(v1);

        }


        return answer;
    }

    private int dfs(int start, boolean[] visited) {
        visited[start] = true;
        int cnt = 1;

        for(int next : graph[start]) {
            if (visited[next]) continue;
            cnt += dfs(next, visited);
        }

        return cnt;
    }

}
