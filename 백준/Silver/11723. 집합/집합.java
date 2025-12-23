/** 백준 11723.S5.집합 - 비트마스크 java 풀이 **/
/**
 * ## 체감 난이도 ##
 * - 나는 처음에 set을 활용해서 풀었다. 그런데 문제의 의도는 비트마스크라는 것을 알게됐다.
 * - 비트 마스크로 문제를 많이 풀어 본 적이 없어서 이해하는데 시간이 걸렸다.
 * - 백준에서 자체적으로 선적한 문제들은 좋은 문제들이 많다고 하던데 맞는거 같다.
 * ## 핵심 내용 ##
 * - 이 문제 템플릿 그냥 외우기
 * bit = 1 << (x-1)
 * add: mask |= bit
 * remove: mask &= ~bit
 * check: (mask & bit) != 0
 * toggle: mask ^= bit
 * all: mask = (1<<20) - 1
 * empty: mask = 0
 * **/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class Main {
    static int M, x, mask = 0;
    static String operation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        M = Integer.parseInt(br.readLine());
        int bit;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            operation = st.nextToken();
            if (operation.equals("empty")) {
                mask = 0;
                continue;
            }
            if (operation.equals("all")) {
                mask = (1 << 20) - 1; // 10000..00 에서 -1 하면 01111..11 이렇게 됨.
                // 수를 저장하는게 아니라 해당 숫자의 자리를 1로 표시해서 존재 여부를 표시하는 방식
                continue;
            }
            x = Integer.parseInt(st.nextToken());
            bit = 1 << (x-1); // 해당하는 수의 위치를 표시 만약 x == 4 이면 1000 네번째를 1로 표시

            switch (operation) {
                case "add":
                    mask |= bit; // '|' 둘 중 하나만 1이어도 1로 표시되므로 기존에서 해당 자리수 0이면 1로 변환, 1이면 그대로
                    // 즉, 해당 수가 없으면 추가 있으면 무시
                    break;

                case "remove":
                    mask &= ~bit; // '~'를 하면 비트를 반대로 바꾸므로 해당 자리수만 0으로 바뀜 여기서 '&' 연산하면 해당 자리수는 무조건 0이 됨.
                    // 즉, 기존에 해당 자리수 숫자가 있으나 없으나 제거
                    break;

                case "toggle":
                    mask ^= bit; // '^' 두 수가 같으면 0 다르면 1을 반환. bit는 해당 숫자의 자리수만 1이고 나머지는 0으로 채워져 있음.
                    // 즉, 이미 존재하면 제거. (mask 에서 해당 자리가 1, bit 에서 1이면 같으므로 0 반환 -> 제거됨) 
                    // 없으면 추가 (mask 에서 해당 자리가 0, bit 에서는 1 다르므로 1 반환 -> 추가됨)
                    break;

                case "check":
                    sb.append((mask & bit) != 0 ? 1 : 0).append("\n"); // mask에 해당 자리수가 0이면 즉 해당 수가 없으면 '&' 연산하면 모두 0으로 채워짐.
                    break;

            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}