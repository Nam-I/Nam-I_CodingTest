#백준 1065.S4 - 한수_파이썬 풀이
#체감 난이도: 사실 간단한 문제였는데 너무 복잡하게 생각해서 막혔었다. 이런 문제 정도는 쉽게 풀어야 하는데...
'''
#핵심 포인트
- n이 두자리일 때까지는 값을 그대로 반환, 99 < n < 111일 때는 무조건 99이다.
- 1000보다 같거나 작은 수가 입력으로 주어진다고 했으나 1000은 등차수열이 아니므로 세자리 수만 고려하면 되는 쉬운 문제였다.
- input()함수로 반복해서 입력을 받으면 실행시간이 길어질 수 있으므로 import sys 하고 sys.stdin.readline()으로 입력을 받는 것이 좋다.
- sys.stdin.readline()은 마지막에 개행 문자가 포함되므로 strip()을 붙여 개행 문자를 없애준다.(문제에서는 바로 정수형으로 바꿔줬기 때문에 안해줘도 됐음)
'''

import sys

n = int(sys.stdin.readline())

if n < 100:
    print(n)
elif n < 111:
    print(99)
else:
    answer = 99

    for i in range(111, n+1):
        hun = i // 100
        ten = i % 100 // 10
        one = i % 10

        if hun - ten == ten - one:
            answer += 1

    print(answer)
