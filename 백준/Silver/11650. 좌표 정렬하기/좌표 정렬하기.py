import sys
import heapq

N = int(input())
num = []
for _ in range(N):
    a, b = map(int, sys.stdin.readline().split(" "))
    heapq.heappush(num, (a, b))

for n in sorted(num):
    print(f"{n[0]} {n[1]}")


