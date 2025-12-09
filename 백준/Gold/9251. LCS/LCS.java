/**백준 9251.G5.LCS - java 풀이**/
/**
 * ## 체감 난이도 ##
 * - LCS 라는 개념을 잘 몰라서 문제가 이해가 잘 안됐다.
 * - 구현 난이도는 어렵지 않으나 개념 난이도가 어려웠다. DP 문제인걸 못떠올렸고 알아도 못풀었을거 같다.
 * ## 핵심 내용 ##
 * - SubString : 연속된 부분 문자열
 * - LCS : 연속, 비연속 상관없는 최장 공통 부분 문자열
 * - length: 배열 길이 계산 VS length(): 문자열 길이 계산
 * - 인접 행렬을 그려서 규칙을 찾아야 하는걸 떠올린다면 풀 수 있을지도..
 **/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

 public class Main {
     static char[] s1, s2;
     static int s1Len, s2Len;
     static int[][] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        s1 = br.readLine().toCharArray(); // 문자열을 문자 배열로 바꿈
        s2 = br.readLine().toCharArray();

        s1Len =  s1.length;
        s2Len = s2.length;

        // 공집합 표현을 위해 인덱스 한 줄씩 추가
        // 만약 최초 문자가 같을 경우 (i-1), (j-1) 이 -1 이 되어 범위를 벗어나는 것 방지
        arr = new int[s1Len + 1][s2Len + 1];

        LCS();

        bw.write(arr[s1Len][s2Len] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    /** LCS(Longest Common Subsequence, 최장 공통 부분 수열) 계산 **/
    private static void LCS() {
        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                if (s1[i-1] == s2[j-1]) { // 문자 같을 경우 arr[i-1][j-1] 값 +1
                    arr[i][j] = arr[i-1][j-1] + 1;
                }
                else { // 문자가 다르면 arr[i-1][j], arr[i][j-1] 중 더 큰 값 그대로 넣기
                    arr[i][j] = Math.max(arr[i][j-1], arr[i-1][j]);
                }
            }
        }
    }
 }