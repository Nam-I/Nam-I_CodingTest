# 백준 9020.S2.-골드바흐의 추측_파이썬 풀이
#체감 난이도: 파이썬으로 풀 때는 코드를 줄일 수 있다는 생각에 더 간단하게 풀고 싶다는 욕심이 생겨서
#코드를 짜는 시간이 괜히 더 오래걸리는 것 같다.
#에라토스테네스의 체를 알고 있다면 어렵지 않게 풀리는 문제이다.
'''
#핵심 포인트
- 에라토스테네스의 체 기억할 것! 소수 문제는 단골로 출제되는 주제 중 하나이다.
'''

import sys


def isPrime(checkNum):
    for divNum in range(2, int(checkNum ** 0.5) + 1):
        if checkNum % divNum == 0:
            return False
    return True


repeat = int(sys.stdin.readline())
answer = ""

for _ in range(repeat):
    n = int(sys.stdin.readline())

    v1 = n // 2
    v2 = 0

    while v2 == 0:

        tmp = n - v1

        if isPrime(v1) and isPrime(tmp):
            v2 = tmp
            answer += str(v1) + " " + str(v2) + "\n"
            break

        v1 -= 1

print(answer)