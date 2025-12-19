/**백준 1764.S4.듣보잡 - java 풀이**/
/**
 * ## 체감 난이도 ##
 * - 오늘은 시간이 없어서 심심풀이로 실버 문제를 풀었다.
 * - 옛날에는 실버 정도도 꽤 많이 생각해서 풀었던거 같은데 너무 쉽게 풀렸다.
 * - 확실히 성장하긴 한걸까. 이렇게 조건 없이 출력만 하면 되는 문제라니..
 * ## 핵심 내용 ##
 * - HashMap 관련 함수 및 쓰임
 * - HashMap 은 한동안 사용을 안했어서 잊어버리지 않게 주의하자
 * - HashMap 순회 방법 알아두기
 **/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    static int N, M;
    static HashMap<String, Integer> map = new HashMap<>();
    static PriorityQueue<String> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            String name = br.readLine();

            map.put(name, map.getOrDefault(name, 0) + 1);

            if (map.get(name) == 2) { // 키가 두번 등장하면 pq 에 넣기(한 분류에서 같은 이름은 존재 X)
                pq.offer(name);
            }
        }

        bw.write(pq.size() + "\n");

        while (!pq.isEmpty()){
            bw.write(pq.poll() + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}