# 백준 2812.G3.크게 만들기_파이썬 스택 풀이
# 체감 난이도: 코드 고민 시간을 너무 짧게 가진 이유 때문일까? 생각보다 너무 쉬운 문제일거 같아
# 술술 풀다가 반례에 많이 걸리게 되었고 반례를 어떻게 잡을까 한참 고민했다.
# 나는 고민을 엄청 했는데 생각보다 풀이가 너무 짧아서 살짝 현타..
# 내가 문제를 너무 직관적으로 풀지 못하는걸까나
"""
# 핵심 내용
- 그냥 접근을 살짝 잘못한거 같음. 중요한 점 딱히 없음. 누군가는 엄청 쉽게 풀었을 문제
"""

import sys

K = int(sys.stdin.readline().split()[-1])

S = sys.stdin.readline().strip()

stack = []

for num in S:
    while stack and stack[-1] < num and K > 0:
        stack.pop()
        K -= 1

    stack.append(num)

if K > 0:
    sys.stdout.write(''.join(stack[:-K]))
else:
    sys.stdout.write(''.join(stack))