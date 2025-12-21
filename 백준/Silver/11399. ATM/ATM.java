/** 백준 11399.S4.ATM **/
/**
 * ## 체감 난이도 ##
 * - 실버 4인데 그래서 문제가 쉬울거라고 방심해서 그런지
 * - 문제 자체는 어렵지 않은거 같았는데 작업시간 누적이랑 총 대기 시간 누적이 다르다는게
 * 잘 이해가 안됐다.
 * ## 핵심 내용 ##
 * - Greedy 알고리즘 이라는데 잘 체감하지는 못했다.
 * - Greedy 알고리즘 개념 알기
 * - 작업 시간이 짧은 사람 부터 처리해야 총 대기 시간 누적이 작아진다.
 * - Counting sort 방식의 정렬법 알아두기
 * : Counting sort 방식은 Arrays.sort() 보다 시간이 적게 걸릴 때가 많은데,
 * - N이 아주 작을 때, 인덱스가 되는 값의 범위가 매우 클 때는 이 방식이 부적합하다.**/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] waiting = new int[1001]; // 문제에서 최대 1000 까지 주어짐
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        // Counting Sort 방식 - 조건에 따라 Arrays.sort() 보다 시간이 적게 걸림
        while (N-- > 0) {
            waiting[Integer.parseInt(st.nextToken())]++; // 작업 시간을 idx 로 두고 등장 횟수를 세기
        }

        int totalWaiting = 0; // 현재 총 인원 누적 대기 시간
        int prevWaiting = 0; // 이전까지 작업시간 누적합

        for (int w = 0; w < 1001; w++) {
            while (waiting[w]-- > 0) {
                prevWaiting += w ; // 이전까지 작업시간 누적합에 현재 사람의 작업 시간 누적
                totalWaiting += prevWaiting; // 총 누적 대기 시간에 현재 사람의 대기 시간 추가
            }
        }

        bw.write(totalWaiting + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}