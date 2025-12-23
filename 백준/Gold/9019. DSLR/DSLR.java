/** 백준 9019.G4.DSLR - java 풀이 **/
/**
 *  ## 체감 난이도 ##
 *  - 아직까지 완전탐색(브루트포스) 문제에 조금 약한거 같다.
 *  - 완전탐색만 나오면 진짜 모든 경우의 수를 다 알아봐야 하나? 더 빠른 방법이 분명해 있겠지?
 *  이런 생각이 들어서 오히려 정답 풀이를 빗겨나가는 경향이 있다.
 *  - 최대 크기로 배열을 선언하고 연산 숫자를 인덱스로 사용하는 이런 식의 아이디어가 아직 바로 떠올리긴 힘든거 같다.
 *  - 풀이 방향을 잘 잡으면 코드 난이도가 엄청 높은 편은 아니라서 G4 난이도가 적절한거 같다.
 *  ## 핵심 내용 ##
 *  - 백트래킹(역추적)을 사용하지 않아도 되지만 백트래킹을 사용한 풀이가 더 효율적이다.
 *  - 백트래킹 없이 문자열 누적으로 푼다면 매번 기존 문자열을 복사하고 거기에 문자 하나를 덧붙이는 연산이
 *  반복되므로 내부적으로 비효율적이다.
 *  - Queue를 사용할 때도 ArrayDeque를 사용하는게 더 빠르다. 그러나
 *  Deque<Integer> dq = new Deque<>() 이렇게 선언하는 이유는 Deque는 양쪽에서 삽입/삭제가 가능한데
 *  이 문제에서는 Queue로만 써도 충분하기 때문이다. 만약 Deque 로 선언했을 때 코드에서 lastPoll() 을 잘못 사용해도
 *  실행 시 오류가 나지 않는데 답은 틀려서 디버그가 쉽지 않을 수 있다. 그러므로 Queue 로 사용할 것을 명시하면
 *  lastPoll() 을 사용하면 오류라고 판단하므로 에러를 더 빨리 찾아낼 수 있다.**/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Queue;
 import java.util.ArrayDeque;
 import java.util.Arrays;
public class Main {
    static int T, A, B;
    static Queue<Integer> q;
    static boolean[] visited;
    static int[] prev; // 현재 숫자(인덱스)의 이전 값을 저장
    static char[] how; // 현재 숫자(인덱스)에 도달하기 위해 어떤 연산을 수행했는지 저장
    static StringBuilder sb;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        int D, S, L, R, curNum;

        while (T-- > 0) {
            q = new ArrayDeque<>(); //
            sb = new StringBuilder();
            visited = new boolean[10000];
            prev = new int[10000];
            Arrays.fill(prev, -1); // 0도 입력으로 들어올 수 있으므로 상태가 0으로 초기화 돼있으면 이전 값이 0이라는 오해가 생길 수 있음
            how = new char[10000];

            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            visited[A] =  true;
            q.offer(A);

            while (!q.isEmpty() && !visited[B]) {
                curNum = q.poll();

                D = 2 * curNum % 10000;
                S = curNum == 0 ? 9999 : curNum - 1;
                L = (curNum % 1000) * 10 + curNum / 1000; // 첫째 자리수를 뒤로 보내기
                R = (curNum % 10) * 1000 + curNum / 10; // 마지막 자리 수를 앞으로 가져오기

                if (!visited[D]) {
                    visited[D] = true;
                    q.offer(D);
                    prev[D] = curNum; // 연산 후 숫자 인덱스에 현재 값을 저장해서 이전 값을 알아낼 수 있도록 함.
                    how[D] = 'D'; // 연산 후 숫자 인덱스에 연산 문자를 넣어 어떤 연산으로 연산 값을 얻어낼 수 있었는지 알 수 있도록 함.
                }
                if (!visited[S]) {
                    visited[S] = true;
                    q.offer(S);
                    prev[S] = curNum;
                    how[S] = 'S';
                }
                if (!visited[L]) {
                    visited[L] = true;
                    q.offer(L);
                    prev[L] = curNum;
                    how[L] = 'L';
                }
                if (!visited[R]) {
                    visited[R] = true;
                    q.offer(R);
                    prev[R] = curNum;
                    how[R] = 'R';
                }
            }

            bw.write(backtracking() + "\n"); // 원래 sb는 bw 에서 toString() 한 후 출력해야 하지만 문자열을 더함으로써 자동으로 수행됨.
        }

        bw.flush();
        bw.close();
        br.close();
    }

    /** 백트래킹(역추적) 수행 **/
    private static StringBuilder backtracking() {
        int x = B;
        while (x != A) { // 실제라면 -1일 때 IllegalStateException("unreachable") 에러를 던지면 안전하지면 코테이므로 생략
            sb.append(how[x]); // 목표에서 부터 거꾸로 올라가면서 어떤 연산으로 그 값에 도달했는지 sb에 누적
            x = prev[x];
        }
        return sb.reverse(); // 거꾸로 누적했으므로 sb 뒤집기
    }
}