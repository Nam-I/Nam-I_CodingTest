import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 99;

        int n = Integer.parseInt(br.readLine());

        if(n < 100) {
            answer = n;
        }
        else if(n < 111) {
            answer = 99;
        }
        else {
            for(int i = 111; i <= n; i++) {
                int f = i / 100;
                int s = i / 10 % 10;
                int t = i % 10;

                if(f-s == s-t) {
                    answer += 1;
                }
            }
        }

        System.out.println(answer);
    }

}