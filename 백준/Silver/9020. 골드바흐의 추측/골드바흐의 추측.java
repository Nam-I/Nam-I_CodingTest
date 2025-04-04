import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder st = new StringBuilder("");

        int repeat = Integer.parseInt(br.readLine());

        for(int i = 0; i < repeat; i++) {
            int n = Integer.parseInt(br.readLine());

            int v1 = n / 2;
            int v2 = 0;

            while(v2 == 0) {
                if(isPrime(v1)) {
                    if(isPrime(n-v1)) {
                        v2 = n-v1;
                        break;
                    }
                }

                v1 -= 1;
           }

            st.append(v1);
            st.append(" ");
            st.append(v2);
            st.append("\n");
        }

        System.out.println(st);
    }

    static public Boolean isPrime(int num) {
        for(int i = 2; i <= (int)Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }

        return true;
    }

}