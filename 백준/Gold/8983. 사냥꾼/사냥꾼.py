# 백준 8983.G4.사냥꾼_파이썬 풀이

import sys

M, N, L = map(int, sys.stdin.readline().split())

# print(f"M: {M}, N: {N}, L: {L}")

x_arr = list(map(int, sys.stdin.readline().split()))

answer = []

for _ in range(N):
    a, b = map(int, sys.stdin.readline().split())
    for i in range(M):
        if abs(x_arr[i] - a) + b <= L:
            if [a, b] not in answer:
                answer.append([a,b])


sys.stdout.write(f"{len(answer)}")