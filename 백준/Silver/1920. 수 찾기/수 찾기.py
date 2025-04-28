# 백준 S4.수 찾기
# 체감 난이도: 아무리 4 난이도라지만 실버 치고는 너무 쉬운 문제 아닌가? 라고 생각했다.
# 시간 제한이 빡빡헸다고 해도 문제 이해 부터가 쉬워서 이것 저것 시도하면 금방 풀릴 문제.
"""
# 핵심내용
- 배열을 탐색하는 것보다 map 과 for 문을 더 사용하더라도 set 집합이나 dict 해시를
탐색하는 것이 더 빠르다.
- 이진탐색(이분탐색) 풀이를 더 눈여겨 보고 기억해둘 것.
"""

import sys

N = int(sys.stdin.readline())

origin = set(sys.stdin.readline().split())

sys.stdin.readline()

for checkNum in sys.stdin.readline().split():
    if checkNum in origin:
        sys.stdout.write(f"1\n")
    else:
        sys.stdout.write(f"0\n")