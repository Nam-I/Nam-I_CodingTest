import heapq as hq
def solution(operations):
    answer = []
    remove = []
    for s in operations:
        if s[0] == "I":
            hq.heappush(answer, int(s[2:]))
        elif s[0] == "D" and len(answer) != 0:
            if s[2:] == "-1":
                hq.heappop(answer)
            else:
                answer = hq.nlargest(len(answer), answer)[1:] #가장 큰 값을 제외한 리스트 요소 반환
                hq.heapify(answer)



    return [0,0] if len(answer) == 0 else [max(answer), min(answer)]