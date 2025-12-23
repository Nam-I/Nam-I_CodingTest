/** 백준 11723.S5.집합 - HashSet java 풀이 **/
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
import java.util.HashSet;
public class Main {
    static int M, x;
    static String operation;
    static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            operation = st.nextToken();
            if (operation.equals("empty")) {
                set.clear();
                continue;
            }
            if (operation.equals("all")) {
                set.clear();
                for (int i = 1; i <= 20; i++) {
                    set.add(i);
                }
                continue;
            }
            x = Integer.parseInt(st.nextToken());

            if (operation.equals("add")) {
                set.add(x);
            }
            else if (operation.equals("remove")) {
                set.remove(x);
            }
            else {
                boolean exist = set.contains(x);
                if (operation.equals("toggle")) {
                    if (exist) {
                        set.remove(x);
                    }
                    else {
                        set.add(x);
                    }
                }
                else if (operation.equals("check")) {
                    if (exist) {
                        bw.write(1 + "\n");
                    }
                    else {
                        bw.write(0 + "\n");
                    }
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
