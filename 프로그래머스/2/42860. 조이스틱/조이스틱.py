def solution(name):
    answer = 0
    l = len(name)
    move = l - 1

    for i in range(l):
        answer += min(ord(name[i]) - 65, ord('Z') - ord(name[i]) + 1)

        next_idx = i + 1
        while next_idx < l and name[next_idx] == 'A':
            next_idx += 1

        move = min(move, i * 2 + l - next_idx) #연속된 A문자열을 만나면 뒤로가기
        move = min(move, (l - next_idx) * 2 + i) #애초에 뒤로 갔다가 다시 처음으로 가기

    return answer + move