//백준 10971 외판원 순회2
//dfs에 조건이 있다 => 백트래킹, dfs방문 후 다시 방문 false로 되돌리는 것이 백트래킹
//체감 난이도: dfs 역시 어렵게 느껴지지만 까다로운 조건이 없어 그나마 어떻게 설계해야하는지가
//조금은 그려졌던 문제. dfs 입문 문제로 괜찮은 듯.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;


public class Main {
    static int[][] cost; //도시 이동 간 비용을 저장할 배열
    static boolean[] visited; //방문한 도시 체크
    static int cityNum; //도시 개수 입력 받음
    static int answer = Integer.MAX_VALUE; //최종 결과를 담을 변수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cityNum = Integer.parseInt(br.readLine());
        cost = new int[cityNum][cityNum];
        visited = new boolean [cityNum];

        for(int i = 0; i < cityNum; i++) {
            cost[i]= Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            //입력 받은 문자열을 배열로 받을 것이므로 split 사용.
            //stream 사용으로 약간의 성능 향상 기대.
        }

        //순회는 어느 지점에서 돌던 결국 제자리로 돌아오기 때문에 시작 지점을 어디로 해도 결과는 같다.
        //어차피 모든 도시를 돌아야하기 때문에..
        visited[0] = true; //아무 도시든 시작 도시로 해도 상관없지만 편의상 0번 도시로 시작.
        back_dfs(0, 0, 1, 0); //백트래킹, 처음 시작 도시는 이미 방문한 상태이므로
        //depth를 1로 시작.

        System.out.println(answer);

    }

    public static void back_dfs(int start, int now, int depth, int sum) {
        if(cost[now][start] != 0 && depth == cityNum) { //갈 수 있는 도시이고 마지막 도시이면
            answer = Math.min(answer, sum + cost[now][start]); //비용이 최소인 값을 최종 저장
            return;
        }
        for(int next = 0; next < cityNum; next++) {
            if(cost[now][next] > 0 && !visited[next]) { //next가 다음 재귀의 now가 됨. start 감ㅅ은
                visited[next] = true;
                back_dfs(start, next, depth+1, sum + cost[now][next]);
                visited[next] = false;

            }
        }
    }
}