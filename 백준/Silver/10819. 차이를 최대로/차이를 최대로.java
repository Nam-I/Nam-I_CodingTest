//백준 10819 차이를 최대로
//체감 남이도: 문제 포맷은 간단해 보였으나 dfs 구현이 생각보다 어렵게 느껴졌다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int n;
    static int answer;
    static boolean[] visited;
    static int[] newArr;
    static int[] originArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        visited = new boolean[n];
        newArr = new int[n];

        originArr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        dfs(0);

        System.out.println(answer);

        br.close();

    }

    public static void dfs(int depth) {

        if(depth == n) {
            answer = Math.max(cal(), answer);
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                newArr[depth] = originArr[i]; //원배열의 값을 할당하고 이미 할당된 값을 중복 할당하지 않도록 방문 표시.
                dfs(depth+1); //모든 배열 조합을 생성하고 연산
                visited[i] = false;
            }
        }
    }

    public static int cal() {
        int result = 0;
        for(int i = 0; i < n-1; i++) {
            result += Math.abs(newArr[i+1] - newArr[i]);
        }
        return result;
    }
}