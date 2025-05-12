# 백준 2470.G5.두 용액_파이썬 투 포인터 풀이
# 체감난이도: 풀이 방향을 잘 잡으면 쉽게 풀리는 문제이다. 투 포인터만 적용하면 되는 문제라서
# G5 문제 맞나..? 싶기도. 루프 종료 지점을 어떻게 설정해야하나 조금 고민했다.
# 이진탐색 처럼 mid 값을 이용해서 탐색 길이를 줄여 반복 회수를 줄이는 방식이 아니라서
# 시간 초과가 나는건 아닌가 걱정해다.(어떤 조건을 추가해서 리스트 길이만큼 반복하지 않는 방법이 있는걸까?)
"""
# 핵심 내용
- 투 포인터에서 while 조건문은 문제에 따라 투 포인터가 만나기까지 혹은 만나기 직전까지로 설정하면
무한 루프에 안빠지고 잘 종료된다.
"""

import sys

N = int(sys.stdin.readline())

arr = sorted(list(map(int, sys.stdin.readline().split())))

start = 0
end = N-1
answer = [arr[start], arr[end]]
mix = arr[start] + arr[end]
tmp = arr[start] + arr[end]

while start < end:

    if tmp == 0:
        sys.stdout.write(f"{arr[start]} {arr[end]}")
        exit(0)

    if abs(0 - mix) > abs(0 - tmp):
        mix = tmp
        answer = [arr[start], arr[end]]

    if tmp < 0:
        start += 1

    else:
        end -= 1

    tmp = arr[start] + arr[end]

sys.stdout.write(f"{answer[0]} {answer[1]}")