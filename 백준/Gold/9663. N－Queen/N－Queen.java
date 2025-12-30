/** 백준 9663.G4.N-Queen - 백트래킹, O(1) java 풀이
 * ## 체감 난이도 ##
 * - 체스를 아예 모르기도 했고 내가 항상 겪는 문제인데 완전 탐색 문제일 때
 * 진짜로 모든 경우의 수를 다 확인하는게 맞나? 하는 의문이 들어서 맞는 방향인데도 자꾸 틀린 방향이라고 생각하는 문제가 있었다.
 * - possible() 로 말을 놓을 수 있는 자리인지 확인하는 방법과 배열 3개로 확인하는 방법이 있다.
 * 후자는 O(1) 의 시간 복잡도를 가진다.
 * - 성능상으로는 배열 3개를 두어 상수 시간 검사를 하는 방식이 더 좋지만
 * possible() 로 조건 검사를 하는 방식이 직관적이라서 이해는 더 잘된다.
 * ## 핵심 내용 ##
 * - 대각선 위치에 있는 점은 점 행/열 차의 절대 값이 같다.(ex: |(r1- r2)| == |(c1 - c2)|
 * - 좌->우 대각선과 우->좌 대각선의 각 개수는 2*N-1 개 이다.(1칸짜리 포함) (ex: N = 5, 2*5-1 = 9)
 * - 같은 대각선 줄에 해당하는 점은 우->좌 : r+c, 좌->우: r-c+(N-1) 연산 값이 같다.
 * - 좌->우에서 r-c+(N-1) 연산에서 (N-1) 을 하는 이유는 r-c 는 음수 값이 나올 수 있으므로 이를 방지 하기 위한 것이다.
 **/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int N;
    static boolean[] usedCol;
    static boolean[] usedRightLeft;
    static boolean[] usedLeftRight;
    static int result = 0;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        usedCol = new boolean[N]; // 해당 열 말 놓을 수 있는 여부 저장
        usedRightLeft = new boolean[2 * N -1]; // 우->좌 대각선 말 놓을 수 있는지 저장
        usedLeftRight = new boolean[2 * N -1]; // 좌-> 대각선 말 놓을 수 있는지 저장


        backtracking(0);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();

    }


    private static void backtracking(int row) {
        if (row == N) {
            result++;
            return;
        }

        for (int col = 0; col < N; col++) {
            int rightLeft = row + col; // 우->좌 대각선 같은 라인에 있는 좌표일 경우 결과 일치
            int leftRight = row - col + (N - 1); // 좌->우 대각선 같은 라인에 있는 좌표일 경우 결과 일치. 음수 방지를 위해 (N-1) 더함.

            if (usedCol[col] || usedRightLeft[rightLeft] || usedLeftRight[leftRight]) continue;

            usedCol[col] = true;
            usedRightLeft[rightLeft] = true;
            usedLeftRight[leftRight] = true;

            backtracking(row + 1);

            usedCol[col] = false;
            usedRightLeft[rightLeft] = false;
            usedLeftRight[leftRight] = false;
        }

    }

 }