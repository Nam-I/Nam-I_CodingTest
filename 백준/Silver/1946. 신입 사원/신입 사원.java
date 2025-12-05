
/** 백준 1496.S1.신입사원 - java 풀이 **/
/**
 * ## 체감 난이도 ##
 * - 풀이를 보고서 풀었다. 막상 풀이를 보면 너무나 쉽게 이해되지만
 * - 그냥 접근 방향을 떠올리기는 아직 무리
 * - 서류 순의를 인덱스로 저장하고 면접 순위를 배열 요소로 저장하는 아이디어를 어떻게 떠올린거지..똑똑해 다들..
 * ## 핵심 내용 ##
 * - 백준 알고리즘 분류에는 따로 나와있지는 않지만 greedy(탐욕법) 알고리즘이라고 한다.
 * - greedy: 매 단계에서 그 순간 최선이라고 보이는 선택을 반복해 전체 최적해에 도달하려는 방법
 * 한 번 내린 선택을 되돌리지 않는 것이 핵심.
 * - greedy 가 통하지 않는 경우:
 * 1. 전역적 제약이난 상호  의존성이 강한 경우 -> DP/백트래킹 필요.
 * 2. 선택이 후속 선택의 가능성/조건을 크게 바꾸는 경우.
 **/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int N;
    static int[] rank;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) { // 비교 연산 수행 후 T값 감소

            N = Integer.parseInt(br.readLine());
            rank = new int[N];
            int document, interview;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                document = Integer.parseInt(st.nextToken());
                interview = Integer.parseInt(st.nextToken());

                rank[document - 1] = interview; // 서류 순위를 인덱스로 사용함으로써 서류 순위가 자동 정렬됨
            }

            int count = 1; // 서류 순위가 1등이면 다른 순위에 밀릴 일이 없음으로 기준이 되고 합격자로 처리(동석차 없음)
            int standard = rank[0];

            for (int i = 1; i < N; i++) {
                if (standard > rank[i]) { // 기준 지원자 보다 다른 지둰자의 면접 순위가 더 높으면 기준 교체
                    count++;
                    standard = rank[i];
                }
            }

            bw.write(count + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}