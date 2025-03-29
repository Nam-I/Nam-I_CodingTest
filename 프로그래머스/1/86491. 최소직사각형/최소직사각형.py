def solution(sizes):
    w = []
    h = []
    for i in range(len(sizes)):
        if sizes[i][0] >= sizes[i][1]:#sizes를 돌면서 가로가 세로보다 크거나 같으면 가로는 w에 저장 세로는 h에 저장
            w.append(sizes[i][0])
            h.append(sizes[i][1])
        else:#만약 가로 보다 세로가 더 크면 가로와 세로를 바꿔서 각각 h와 w애 저장
            h.append(sizes[i][0])
            w.append(sizes[i][1])

    return max(w) * max(h)#각각의 리스트 중 가장 큰 값 두개의 곱을 반환