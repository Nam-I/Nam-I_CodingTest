/** 백준 1541.S2.잃어버린 괄호 **/
/**
 * ## 체감 난이도 ##
 * - 음 어떤 사람은 너무 쉬워서 설명할게 없다는데 음..
 * - 이렇다 할 알고리즘 문제는 아니지만 문제 자체가 잘 이해가 안됐음
 * - 예제 출력에서 덧셈, 뺄셈 밖에 없는데 어떻게 -35가 나오는거지..? 하면서 이해가 안됨
 * - 알고보니 가로를 쳐서 50-(50+40) 이렇게 돼서 부호가 곱해지는건 상관 없는 거였음...
 * - 예제 입출력이 이해가 되면 풀이가 가능
 * ## 핵심 내용 ##
 * - 연속된 덧셈을 괄호로 묶어 뺄셈하면 최소가 됨.
 ***/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer minus = new StringTokenizer(br.readLine(), "-"); // 뺄셈을 기준으로 토큰 분리

        int sum = Integer.MAX_VALUE;

        while(minus.hasMoreTokens()) { // 반환할 토큰이 있는지 여부 확인
            int temp = 0;

            StringTokenizer plus = new StringTokenizer(minus.nextToken(), "+"); // 뺄셈 기준으로 분리된 토큰을 다시 덧셈 기준으로 분리

            while (plus.hasMoreTokens()) {
                temp += Integer.parseInt(plus.nextToken()); // 연속된 덧셈 계산
            }

            if (sum == Integer.MAX_VALUE) { // sum에 처음으로 할당되는 값이면 그대로 값 할당
                sum = temp;
            }
            else {
                sum -= temp; // 이후 부터는 연속된 덧셈 계산 값을 할당된 값에서 빼줌
            }
        }

        bw.write(sum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}