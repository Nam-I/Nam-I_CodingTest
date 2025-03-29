def solution(n):
    m = n+1
    for i in range(n+1, 1000001):
        if bin(n).count("1") == bin(m).count("1"):
            return m
        
        m += 1