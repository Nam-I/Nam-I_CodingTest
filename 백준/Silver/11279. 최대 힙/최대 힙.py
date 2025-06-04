# 백준 11279.S2.최대 힙_파이썬 풀이
# 체감 난이도: 사실 이미 아는 내용이라 너무 쉬웠다. 역시 문제를 많이 푸는게 중요하구나
# 다시 한 번 느끼게 된다.
"""
# 핵심내용
- 힙은 기본적으로 최소 힙으로 동작하고, 이를 요소의 첫번째를 기준으로 정렬하는데,
이를 이용해 요소의 첫번째에 원래 요소의 음수 값을 저장하면 최대 힙으로 활용 가능하다.
"""

import sys
import heapq as hq

N = int(sys.stdin.readline())
heap = []

for _ in range(N):
    x = int(sys.stdin.readline())
    if x == 0 and not heap:
        sys.stdout.write("0\n")
    elif x == 0:
        sys.stdout.write(f"{hq.heappop(heap)[1]}\n")
    else:
        hq.heappush(heap, [x*-1, x])