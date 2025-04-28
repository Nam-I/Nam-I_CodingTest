import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // arr[0] 지수 N, arr[1] 행 r, arr[2], 열 c
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(cal(arr[0], arr[1], arr[2]));

    }
    
    public static int cal(int N, int r, int c) {
        if (N == 0) {
            return 0;
        }
        else {
            return 2 * (r % 2) + (c % 2) + 4 * cal(N-1, r / 2, c / 2);
        }
    }
}