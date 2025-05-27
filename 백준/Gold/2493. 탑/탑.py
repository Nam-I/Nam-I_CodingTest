# 백준 2493.G5.탑_파이썬 최소힙 풀이
# 체감 난이도: 골드라기엔 좀 쉬운데..? 실버 4? 정도로 느껴졌다.
# 문제를 보자마자 풀이 감이 대충 잡히는 정도. 문제에서도 딱히 생각할 부분 없음.
# 스택, 해쉬 문제는 대체로 크게 어렵지 않은 느낌
"""
# 핵심 내용
- 최소힙은 첫번째 요소로 오름차순 정렬
- 최대힙으로 바꾸고 싶다면 첫번째 요소로 원래 수의 음수 갑을 저장하고,
두번째 요소로 원래 값을 저장하는 식으로 구현하면 된다.
"""
import sys
import heapq as hq

N = int(sys.stdin.readline())

tower = list(map(int, sys.stdin.readline().split()))
signal = [0]*N
heap = []

hq.heappush(heap, (tower[N-1], N-1))

for i in range(N-2, -1, -1):
    while heap:
        if tower[i] >= heap[0][0]:
            signal[heap[0][1]] = i+1
            hq.heappop(heap)
        else:
            break
    hq.heappush(heap, (tower[i], i))

print(*signal)