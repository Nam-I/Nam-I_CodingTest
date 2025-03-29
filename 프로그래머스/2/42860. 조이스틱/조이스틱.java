class Solution {
    public int solution(String name) {
        int next_idx;
        int answer = 0;
        int l = name.length();
        int move = l - 1;
        
        for(int i = 0; i < l; i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            
            next_idx = i + 1;
            while(next_idx < l && name.charAt(next_idx) == 'A') {
                next_idx++; //연속된 A의 바로 다음 인덱스
            }
            
            //처음부터 연속된 A 바로 전까지 갔다가 뒤로 돌아가는 경우
            move = Math.min(move, i * 2 + l - next_idx);
            //처음부터 뒤에서 부터 시작했다가 연속된 A에서 돌아나와 다시 처음부터 가는 경우
            move = Math.min(move, (l - next_idx) * 2 + i);
                
        }
        
        return answer + move;
    }
}