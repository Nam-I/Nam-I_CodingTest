# 백준 10830.G4.행렬 제곱_파이썬 거듭제곱 분할정복, 모듈러 분배법칙 풀이
# 체감 난이도: 수학적 지식이 부족하면 좀 많이 어려운 문제
# 행렬의 제곱까지는 어떻게 구현하겠는데 거듭제곱을 분할하는 것과 모듈러 연산 분배법칙까지 적용해서 풀려고 하면
# 머릿 속이 복잡해진다...
"""
# 핵심내용
- 거듭 제곱 분할정복으로 푸는 것 잘 기억해두기.(언젠가 쓰일 수 있을 것 같다.)
- 거듭 제곱 분할 정복
: 2^32 -> (2^16)^2 -> ((2^8)^2)^2) -> (((2^4)^2)^2)^2) 이런식
: 2^32 -> 2^(16+16)
- 모듈러 분배법칙
: (A + B) % C = (A % C + B % C) % C
: (A - B) % C = (A % C - B % C) % C
: (A X B) % C = (A % C X B % C) % C
: (A / B) % C = (A % C X B^-1 % C) % C
"""

import sys

N, B = map(int, sys.stdin.readline().split())
A = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]


def matrix_product(arr1, arr2):
    result_arr = [[0]*N for _ in range(N)]

    for row in range(N):
        for col in range(N):
          result_arr[row][col] = sum(arr1[row][i]*arr2[i][col] for i in range(N)) % 1000

    return result_arr


def divide(b):
    if b == 1:
        return A

    div = divide(b//2)

    if b % 2 == 0:
        return matrix_product(div, div)
    else:
        return matrix_product(matrix_product(div, div), A)


for r in divide(B):
    print(*[c % 1000 for c in r])
