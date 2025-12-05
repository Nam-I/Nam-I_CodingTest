/** 백준 1931.G5.회의실 배정 - java 풀이**/
/**
 * ## 체감 난이도 ##
 * - greedy, 정렬 문제인걸 알았지만 어떤 식으로 정렬해야 할지 감이 안잡혔다.
 * - 문제나 풀이 이해가 엄청 어렵진 않아서 greedy 가 익숙한 사람은 Silver 수준으로 느껴졌을지도?
 * - 첫번째 요소가 아니라 두번째 요소를 기준으로 정렬하는거라 그 부분 구현 난이도가 반영된 것일 수도 있겠다.
 * ## 핵심 내용 ##
 * - Compartor, comparingInt, thenComparingInt 구현 기억하기
 * - list.sort(Comparator) 형태 기억하기
 * - 람다 표현식 사용법 잘 알아두기
 **/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    static int N;
    static ArrayList<int[]> meetings = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetings.add(new int[]{start, end});
        }

        meetings.sort( // 명시적 박싱 회피, 람다
                Comparator.comparingInt((int[] x) -> x[1]) // 종료 시간 기준 오름차순 정렬
                        .thenComparingInt(x -> x[0]) // 종료 시간이 동일할 경우 시작 시간 기준 오름차순 정렬
        );

        int count = 0;
        int currentEndTime = -1;

        for (int[] meeting : meetings) { // 차례로 배열 순회
            if (currentEndTime <= meeting[0]) { // 현재 회의 종료 시간 보다 시작 시간이 뒤인 경우
                ++count; // 해당 회의 일정 추가
                currentEndTime = meeting[1]; // 현재 종료 시간 업데이트
            }
        }

        bw.write(count + "\n");
        bw.flush();
        br.close();
    }
}