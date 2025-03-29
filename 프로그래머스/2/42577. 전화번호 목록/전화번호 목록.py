def solution(p):
    answer = True
    p.sort()
    for i in range(len(p)-1):
        if p[i] == p[i+1][:len(p[i])]:
            answer = False
            return answer
    return answer