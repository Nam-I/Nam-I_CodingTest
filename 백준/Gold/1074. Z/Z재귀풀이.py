# 백준 1074.G5.-Z_파이썬 재귀 풀이
# 규칙을 찾아서 재귀로 풀어낸 풀이
# 2 * (r % 2) + (c % 2) 이 식은 2*2 상자 안에서 순서값을 찾는 식
# 4 * cal(N - 1, r // 2, c // 2) 이 식은 2*2 상자의 시작 위치 값은 항상
# 4의 배수라는 점을 이용한 식

import sys


def cal(N, r, c):
    if N == 0:
        return 0
    else:
        return 2 * (r % 2) + (c % 2) + 4 * cal(N - 1, r // 2, c // 2)
    # Z 탐색 시작점에 해당하는 값은 항상 4의 배수인 것을 알 수 있다.
    # 이 규칙에 따라서 2*2 최소 단위 상자에서


N, r, c = map(int, sys.stdin.readline().split())

print(cal(N, r, c))
