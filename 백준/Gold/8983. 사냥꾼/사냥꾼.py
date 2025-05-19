# 백준 8983.G4.사냥꾼_파이썬 이진 탐색 풀이
# 체감 난이도: 그냥 문제만 보고 바로 풀었을 때는 어려움 없이 40점을 받았다.
# 100점을 받기 어려운 문제인 것 같다. 이진 탐색 문제인 것을 떠올리기가 어렵다.
"""
# 핵심 내용
- 이진 탐색으로 반환한 값의 바로 오른쪽 값이 동물과 더 가까운 사대일 수 있는 예외사항을 잘 파악해야 한다.
- bisect 모듈을 이용한 풀이가 시간은 더 적게 걸린다.(실전에서 써먹기 어려울 것 같아서 기억만 해둘 예정)
- 예외 사항에 해당하는 입력 예제(start 와 end 의 위치 역전, 이진탐색 반환 값보다 오른쪽 값이 더 동물과 가까운 경우)
4 1 2
1 2 3 10
9 0
"""

import sys
from bisect import bisect_left

M, N, L = map(int,sys.stdin.readline().split())
x_arr = sorted(list(map(int, sys.stdin.readline().split())))
cnt = 0

for _ in range(N):
    a, b = map(int, sys.stdin.readline().split())
    if b > L:
        continue
    idx = bisect_left(x_arr, a)

    print(f"idx: {idx}")
    print(f"x_arr[{idx}]: {x_arr[idx]}")
    if abs(x_arr[idx]-a)+b <= L:
        cnt += 1
    elif idx > 0 and abs(x_arr[idx-1]-a)+b <= L:
        cnt += 1

sys.stdout.write(f"cnt: {cnt}")
