def solution(n):
    f = [0,1]
    for i in range(2, n):
        f.append(f[i-1]+f[i-2])
    return (f[n-1]+f[n-2])%1234567