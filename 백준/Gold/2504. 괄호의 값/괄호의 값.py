# 백준 2504.G5.괄호의 값
# 체감 난이도: 어떤 방식으로 푸는지는 단번에 알아차렸으나 경우의 수를 세세하게 따져보고
# 조건문을 꼼꼼히 작성해야 하는 문제라서 어렵게 느껴졌다. 연산식을 문자열로 작성해서 eval()로 연산하려고
# 풀어봤는데 그렇게는 도저히 안되겠더라. 이 풀이 말고 다른 더 좋은 풀이가 있을까 싶다. 찾아봐도 다 똑같은 풀이..ㅎ
# tmp 를 1로 두고 괄호에 따라 수를 곱하고 이전 괄호가 짝이 맞는 괄호일 때만 tmp 값을 나누는 풀이
# 누가 처음 생각했는지 대단하다. 실전에서 이 문제가 나온다면 마음이 급해서 결국 틀릴 것 같은 문제.
"""
# 핵심내용
- stack 에 가장 최근 값을 확인하여 쌍을 맞추는 것이 아님에 주의.
: [[]] 이런 괄호 일 때,
1.현재 값 -> ] , 현재 값 바로 이전 값 -> [, stack[-1] 값 -> [ => 현재 값과 이전 값이 쌍이 맞으므로 stack.pop()
2. 현재 값 -> ], 현재 값 바로 이전 값 -> ], stack[-1] 값 -> [ => 스택의 가장 최근 값과 현재 값을 비교할 경우
괄호 쌍이 맞는 것으로 나오지만 연산도 같이 해야하므로 이를 기준으로 했을 경우 answer 에 tmp 가 또 더해져 값이 더 커져버린다.
현재 값과 바로 이전 값의 쌍이 맞을 때만 tmp 누적 연산을 answer 에 더해야 하므로, answer 에 tmp 누적 연산을 더하고 tmp 값을 괄호에 할당된
수만큼 나누는 과정을 수행하는 조건은 "현재 문자 값과 바로 이전 문자 값(스택에서가 아닌 문자열에서)을 비교해서 쌍이 맞을 때이다."
"""

import sys

S = sys.stdin.readline().strip()
stack = []

answer = 0
tmp = 1

for i in range(len(S)):
    if S[i] == '(':
        stack.append(S[i])
        tmp *= 2
    elif S[i] == '[':
        stack.append(S[i])
        tmp *= 3
    elif S[i] == ')':
        if not stack or stack[-1] == '[':
            sys.stdout.write("0")
            exit(0)
        elif S[i-1] == '(':
            answer += tmp
        tmp //= 2
        stack.pop()
    else:
        if not stack or stack[-1] == '(':
            sys.stdout.write("0")
            exit(0)
        elif S[i-1] == '[':
            answer += tmp
        tmp //= 3
        stack.pop()

if stack:
    sys.stdout.write("0")
else:
    sys.stdout.write(f"{answer}")