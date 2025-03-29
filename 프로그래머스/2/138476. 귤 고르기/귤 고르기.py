def solution(k, tangerine):
    a = {} #딕셔너리 정의
    answer = 0
    for i in tangerine:
        if i in a:
            a[i] += 1
        else:
            a[i] = 1
    
    a = dict(sorted(a.items(), key = lambda x : x[1], reverse=True))
    
    for j in a:
        if k <= 0:
            return answer
        k -= a[j]
        answer += 1
    return answer