import java.util.*;

class Solution {
    public int solution(int[][] triangle) {

        int arr_l = triangle.length;

        for (int i = 1; i < arr_l; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) { //왼쪽 끝 인덱스
                    triangle[i][j] += triangle[i - 1][j];
                } else if (j == triangle[i].length - 1) { //오른쪽 끝 인덱스
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else {
                    triangle[i][j] = Math.max(triangle[i][j] + triangle[i - 1][j], triangle[i][j] + triangle[i - 1][j - 1]);
                }
            }
        }

        return Arrays.stream(triangle[arr_l-1]).max().getAsInt();
    }
}