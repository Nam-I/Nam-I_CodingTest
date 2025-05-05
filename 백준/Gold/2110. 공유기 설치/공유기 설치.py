# 백준 2110.G4.공유기 설치_파이썬 풀이
# 체감 난이도: 딱 G4 정도로 느껴졌다. 이진탐색 문제라는 것을 떠올리기 힘들었다.
# 풀이를 찾아보니 다른 사람들도 그렇게 느꼈던 것 같다.
# 좌표의 거리만 중요한 문제라서 리스트의 인덱스는 아무런 활용도가 없고 오로지 리스트 요소 값만 중요하다.
# 인덱스를 활용해서 뭔가를 풀어내려고 하다가 더 꼬인 것 같다.
"""
# 핵심 포인트
- 이진탐색이 어떻게 쓰였는지 기억하고 풀이 활용법 알아두기
- 인덱스를 이용해 분할하는 문제가 아니라 리스트 요소의 값과 거리가 중요한 문제.
"""

import sys


N, C = map(int, sys.stdin.readline().split())

arr = []

for _ in range(N):
    arr.append(int(sys.stdin.readline()))

arr.sort()

start = 1
end = arr[-1] - arr[0]
answer = 0

while start <= end:
    mid = (start + end) // 2
    current = arr[0]
    count = 1

    for i in range(1, N):
        if arr[i] >= current + mid:
            count += 1
            current = arr[i]

    if count >= C:
        start = mid + 1
        answer = mid
    else:
        end = mid - 1

sys.stdout.write(f"{answer}")