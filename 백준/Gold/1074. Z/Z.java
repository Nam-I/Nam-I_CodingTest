//백준 G5.1074 - Z_자바 풀이
// 체감 난이도: 실버 수준 정도로 느껴졌다. 구현에 집중한 문제로 포기하지 않고 매달리면 어떻게든 풀리는 문제다.
// 그런데 좌표를 당겨 오는 기준을 제대로 잘 못찾아서 반례를 찾으면서 기준을 확실이 아는데에 시간이 많이 걸렸다.
/*
* 핵심 내용
* - 실마리가 보인다면 그냥 무작정 풀기 시작해 볼 것.
* - 값을 계속 찍어보면서 풀이의 정교함을 갖추어 나가면 된다.
*/

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

        int div_half = (int)Math.pow(2, arr[0]);
        int answer = -1; //순서가 0부터 시작. 따라서 (0, 0)이 일반적인 순서 1이 아니라 순서 0임.
        int location = 0;

        while(div_half > 1) {

            div_half = div_half / 2; //div_half 가 int 형 변수이므로 값이 자동으로 int 형변환
            

            if(arr[1] < div_half && arr[2] < div_half){ // Z 순서로 찾으려는 위치가 현재 박스 크기를 4등분 했을 때 위치(location)이 어디인지 확인.
                location = 1;
            }
            else if(arr[1] < div_half && arr[2] >= div_half) {
                location = 2;
            }
            else if(arr[1] >= div_half && arr[2] < div_half) {
                location = 3;
            }
            else if(arr[1] >= div_half && arr[2] >= div_half){
                location = 4;
            }

            answer += div_half * div_half * (location - 1); // 찾으려는 위치가 있는 박스를 제외하고 그 앞에 위치한 박스들의 요소 개수를 세어준다.

            if(arr[1] >= div_half) { // 앞에 박스들을 다 세어주었으므로 현재 위치가 속한 박스를 중심으로 계산하기 위해 좌표를 평행이동 한다.
                arr[1] -= div_half; //박스를 수평, 수직으로 중심을 나누었을 때 중앙보다 뒤에 속하면 r, c 좌표를 땡겨 온다. 
            }
            if(arr[2] >= div_half) { // c 좌표만 중앙보다 뒤에 있으면 c를(location 이 2인 경우), r 좌표만 뒤면 r을 (location = 3),
                arr[2] -= div_half; // 모든 좌표가 중앙보다 뒤이면 모두 좌표를 당겨 온다.(location = 4)
            } // 최종적으로 최소 박스 크기인 2*2 범위 내에 좌표가 들어오도록 조정하는 과정이다. 최종 좌표범위 r: 0~1, c: 0~1
        }

        answer += 1; // 최종 위치 바로 앞의 박스들의 개수를 모두 세어주었으므로 마지막으로 +1을 해주면 찾으려는 박스 위치로 온다.

        System.out.println(answer);

    }
}
