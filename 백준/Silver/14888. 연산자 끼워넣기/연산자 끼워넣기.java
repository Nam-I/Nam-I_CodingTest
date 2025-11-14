// 백준 14888.S1.연산자 끼워넣기 - java 풀이
/**
 * ## 체감 난이도
 * - 이해하면 그리 어려운 문제는 아닌거 같으나 아직 나에게는 조금 어렵게 느껴졌다.
 * - 모든 경우의 수를 구해야 하는걸 인지 했으나 dfs 구현, 백트래킹과 잘 연관이 안됐다.
 *## 핵심 내용
 * - dfs 재귀를 사용해서 모든 경우의 수를 구할 수 있다.
 * - 백트래킹으로 기존 값을 되들리므로써 다음 경우의 수 연산의 지장이 없도록 한다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main{
    private static int N;
    private static int[] arr;
    private static final int[] operator = new int[4]; //덧셈, 뺄셈, 곱샙, 나눗셈
    private static int maxValue = Integer.MIN_VALUE;
    private static int minValue = Integer.MAX_VALUE;

    public static void main (String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 4; i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(arr[0], 1);

        bw.write(maxValue + "\n" + minValue + "\n");
        bw.flush();
        bw.close();

        br.close();

    }

    public static void dfs(int num, int idx){
        if ( idx == N) {
            maxValue = Math.max(maxValue, num);
            minValue = Math.min(minValue, num);
            return;
        }

        for (int i = 0; i < 4; i++){

            if (operator[i] > 0) {

                operator[i]--; // 배열 요소에 해당하는 연산자 사용

                switch(i){
                    case 0: dfs(num+arr[idx], idx+1); break; // 덧셈을 가장 먼저 하는 경우의 수(이후 경우는 dfs 재귀를 통해 구해짐)
                    case 1: dfs(num-arr[idx], idx+1); break; // 뺄셈 ..
                    case 2: dfs(num*arr[idx], idx+1); break; // 곱셈 ..
                    case 3: dfs(num/arr[idx], idx+1); break; // 나눗셈 ..
                }

                operator[i]++; // 재귀호출 종료 시 이전 값으로 복구하므로 "백트래킹"
            }

        }
    }
}
