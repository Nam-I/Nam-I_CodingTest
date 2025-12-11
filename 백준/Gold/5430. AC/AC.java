/** 백준 5430.G5.AC - java 풀이 **/
/**
 * ## 체감 난이도 ##
 * - 딱 G5 정도로 느껴졌다
 * - 구현에만 신경쓰는 문제면 되는 줄 알았으나 시간 단축, 출력에 대해 깊이 고민해야 하는 문제였다.
 * - Deque 를 마음껏 활용해 볼 수 있는 문제
 * ## 핵심 내용 ##
 * - 자료구조 전체를 뒤집을 생각하지 말고 출력만 앞, 뒤로 바꿔주면 된다.
 * - 정답 출력이 공백을 허용하지 않으므로 출력 포맷을 직접 재구성해야한다.
 * - 코드 고민 + 시간 단축 + 구현 디테일 모두 신경 써야하는 문제
 **/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    static int T;
    static char[] p;
    static Deque<Integer> dq;
    static boolean isReversed, error;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            p = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), ", []");
            dq = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
              dq.offer(Integer.parseInt(st.nextToken()));
            }

            isReversed = false;
            error = false;


            for (char c : p) {
                if (c == 'R') {
                    isReversed = !isReversed;
                }
                else if (!D()) {
                    sb.append("error\n");
                    error = true;
                    break;
                }
            }

            if (!error) {
                sb.append("[");

                if (!dq.isEmpty()) {
                    int dqSize = dq.size();

                    if(!isReversed) {
                        while(dqSize-- > 1) {
                            sb.append(dq.poll()).append(",");
                        }

                        sb.append(dq.poll());
                    }
                    else {
                        while(dqSize-- > 1) {
                            sb.append(dq.pollLast()).append(",");
                        }

                        sb.append(dq.pollLast());
                    }
                }

                sb.append("]\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean D() {
        if(dq.isEmpty()) return false;

        if (!isReversed) {
            dq.poll();
        }
        else {
            dq.pollLast();
        }
        return true;
    }
}