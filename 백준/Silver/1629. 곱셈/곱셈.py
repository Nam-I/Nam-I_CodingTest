# 백준 1629.S1.곱셉_파이썬 거듭제곱 분할정복, 모듈러 분배법칙, 재귀 풀이
# 체감 난이도: 수학적인 지식이 없으면 못 푸는 문제인 것 같다. 지수를 분할한다는 힌트가 있었다면
# 고민이라도 해봤을 수도 있는데 어떻게 접근해야할지 안떠올라서 그냥 쌩으로 틀렸다.
# 풀이를 참고하니 이해가 된 것 같은데 다음에 이런 문제가 나온다면 못맞출 듯
"""
# 핵심내용
- 분할 정복을 이용한 거듭제곱 풀이
- 모듈러 연산의 분배법칙
- pow() 내장 함수가 거듭제곱 연산만 해주는 것이 아니라 세번째 인자 값을 주면
거듭 제곱 후 세번째 인자 값으로 나눈 나머지를 반환한다는 사실.
"""

import sys

A, B, C = map(int, sys.stdin.readline().split())


def product(a, b, c):
    if b == 1:
        return a % c

    tmp = product(a, b//2, c)

    if b % 2 == 0:
        return tmp * tmp % c
    else:
        return tmp * tmp * a % c


sys.stdout.write(f"{product(A, B, C)}")