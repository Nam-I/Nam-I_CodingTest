# 백준 2630.S2.색종이 만들기_파이썬 dfs 재귀 풀이
# 체감 난이도: 골드 문제 중에 있었던 Z 문제랑 비슷한 결인거 같다.
# 처음엔 이게 뭐야 싶었는데 맘 먹고 풀면 어렵지 않게 풀리는 문제.
# 딱 S2 정도 난이도로 느껴졌다.
# 시간초과 안나겠..지?
"""
# 핵심 내용
- 함수 선언 전에 메인에서 변수를 선언해주면 함수 내부에서도 쓸 수 있는 줄 알았는데 리스트만 그런거였다...
- 아마도 변수가 주소 값으로 위치를 가르켜서 그런 듯 그래서 흰,파 저장하는 곳을 리스트 형식으로 급히 변경...
"""

import sys

N = int(sys.stdin.readline())

arr = []
answer = [0, 0]

for _ in range(N):
    arr.append(list(map(int, sys.stdin.readline().split())))
    

def dfs(n, r, c, answer):
    if n != 1:
        for i in range(r, r + n):
            for j in range(c, c + n):
                if arr[i][j] != arr[r][c]:
                    dfs(n//2, r, c, answer)
                    dfs(n//2, r, c+n//2, answer)
                    dfs(n//2, r+n//2, c, answer)
                    dfs(n//2, r+n//2, c+n//2, answer)
                    return

    if arr[r][c] == 1:
        answer[1] += 1
    else:
        answer[0] += 1


dfs(N, 0, 0, answer)

print(f"{answer[0]}\n{answer[1]}")