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
 * - 우선순위큐로 구현 가능: 삽입/삭제 시 그냥 큐는 compareTo()를 호출하지 않음
 **/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

class Meeting implements Comparable<Meeting> {
    int start, end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public int compareTo(Meeting o) { // offer()/poll() 시 자동으로 정렬 실행
        int result = Integer.compare(this.end, o.end); // 빼기 방식(this.end - o.end)는 드물게 오버플로우가 발생할 수 있다고 해서 이 방식 채택

        if (result != 0) return result;
        else return Integer.compare(this.start, o.start);
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Meeting> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.offer(new Meeting(start, end));
        }

        int prevEndTime = pq.poll().end; // 회의는 무조건 한개 이상이고 정렬된 가장 앞 순서 회의는 반드시 추가됨
        int count = 1; // 회의 일정 추가
        while(!pq.isEmpty()) {
            Meeting m = pq.poll();

            if (prevEndTime <= m.start) { // 이전 회의의 끝나는 시간이 현재 회의의 시작 시간 보다 뒤이거나 같으면(끝남과 동시에 시작 가능)
                ++count; // 회의 일정 추가
                prevEndTime = m.end; // 현재 회의의 종료 시간으로 교체
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}