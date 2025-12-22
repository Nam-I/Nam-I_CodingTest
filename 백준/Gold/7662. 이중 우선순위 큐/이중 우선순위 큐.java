/**백준 7662.G4.이중 우선순위 큐 - java 풀이**/
/**
 * ## 체감 난이도 ##
 * - TreeMap에 대해 잘 알고있는 사람이라면 어렵지 않게 풀었을거 같다.
 * - 나는 TreeMap 이라는 자료구조를 이 문제에서 처음 사용해 봐서 생소했다.
 * ## 핵심 내용 ##
 * - TreeMap 개념과 사용법
 * - TreeMap 은 키, 값을 같는 것은 일반적인 map 과 같은데 이 내용이 오름차순으로 정렬된다는게 다르다.(Red-Black Tree 기반)
 * - firstKey() 로 최소 키, lastKey() 로 최대 키에 접근할 수 있다.
 * - map.put() 연산은 map 에 키, 값을 추가하는 연산이지만 기존 value 값을 반환한다는 특징이 있다.
 * : 기존에 해당하는 key-value 가 없었을 경우 반환 값은 null
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static int T, K;
    static TreeMap<Integer, Integer> treeMap;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        char operation;
        int num;
        while(T-- > 0) {
            K = Integer.parseInt(br.readLine());
            treeMap = new TreeMap<>();

            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                operation = st.nextToken().charAt(0);
                num = Integer.parseInt(st.nextToken());

                if (operation == 'I') {
                    treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
                    continue;
                }

                if (treeMap.isEmpty()) continue;

                int targetNum = num == 1 ? treeMap.lastKey() : treeMap.firstKey(); // 현재 입력 숫자가 1이면 최대 값, 아니면 최소값
                if (treeMap.put(targetNum, treeMap.get(targetNum) - 1) == 1) { // put() 연산의 반환 값이 업데이트 이전의 기존 value 값인 것을 활용한 비교 연산
                    treeMap.remove(targetNum); // 기존 value 값이 1 이면 (기존 값 -1) 로 값을 업데이트한 현재는 해당 key의 value 값이 0일 것이므로 key 삭제 
                }
            }

            bw.write(treeMap.isEmpty() ? "EMPTY\n" : treeMap.lastKey() + " " + treeMap.firstKey() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}