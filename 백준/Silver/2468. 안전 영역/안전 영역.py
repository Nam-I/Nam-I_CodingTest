# 백준 2468.S1.안전 영역
# 체감난이도: dfs 를 떠올리기는 쉬웠지만 조건에 맞춰 여러번 계산을 해줘야하고
# 구현에서 실수하기 쉬운 요소들이 있어서 S1 보다 어렵게 느껴진다. 저번에 풀었던 G4가 더 쉬웠던 것 같은..
"""
#핵심 내용
- 상하좌우 탐색과 경계 지점 잘 설정해서 탐색하기
- 안전영역을 구하는 것에서 끝이 아니라 가장 안전영역이 많을 때의 개수 구하기라는 것.
"""

import sys
sys.setrecursionlimit(10 ** 4)

N = int(sys.stdin.readline())

arr = []
height = set()
answer = 1

dx = [-1, 1, 0, 0]  # 상하좌우
dy = [0, 0, -1, 1]  # 상하좌우


def dfs(r, c, h, visited):
    for k in range(4):
        if 0 <= r+dx[k] < N and 0 <= c+dy[k] < N:
            if arr[r + dx[k]][c + dy[k]] > h and visited[r + dx[k]][c + dy[k]] is False:
                visited[r + dx[k]][c + dy[k]] = True
                dfs(r+dx[k], c+dy[k], h, visited)


for _ in range(N):
    tmp_list = list(map(int, sys.stdin.readline().split()))
    arr.append(tmp_list)
    height.update(tmp_list)

height = sorted(list(height))

for h in range(len(height)-1):
    visited = [[False] * N for _ in range(N)]
    cnt = 0
    for i in range(N):
        for j in range(N):
            if visited[i][j] is True or arr[i][j] <= height[h]:
                continue
            visited[i][j] = True
            dfs(i, j, height[h], visited)
            cnt += 1

    answer = max(answer, cnt)

sys.stdout.write(f"{answer}")