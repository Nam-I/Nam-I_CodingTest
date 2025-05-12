# 백준 2468.S1.안전 영역_bfs deque 풀이
# 체감난이도: dfs 를 떠올리기는 쉬웠지만 조건에 맞춰 여러번 계산을 해줘야하고
# 구현에서 실수하기 쉬운 요소들이 있어서 S1 보다 어렵게 느껴진다. 저번에 풀었던 G4가 더 쉬웠던 것 같은..
"""
#핵심 내용
- dfs/bfs 두가지 풀이가 가능한 문제
- 개인적으로 bfs 로 푸는 것이 더 좋은 것 같다. dfs 풀이는 재귀 깊이 제한을 해제해 주어야 하기 때문에 더 번거로울 수 있다.
- dfs 풀이: 이동 가능한 좌표를 찾으면 그 지점으로 재귀 이동하여 다시 탐색
- bfs 풀이: 현재 지점에서 이동 가능한 좌표를 모두 탐색하여 deque 에 넣고 그 다음 deque 를
- popleft() 하며 이동.
- 상하좌우 탐색과 경계 지점 잘 설정해서 탐색하기
- 안전영역을 구하는 것에서 끝이 아니라 가장 안전영역이 많을 때의 개수 구하기라는 것.
- 땅이 모두 잠기지 않는 경우도 고려해 안전 영역의 최대 개수는 항상 1보다 크거나 같다.
"""

import sys
from collections import deque

N = int(sys.stdin.readline())  # 높이 정보 N*N 개수

arr = []  # 높이 정보를 저장할 땅
height = set()  # 땅의 높이를 중복 없이 저장
answer = 1  # 어떤 땅도 잠기지 않는 경우가 있으므로 정답 최소 값은 1

dx = [-1, 1, 0, 0]  # 상하좌우
dy = [0, 0, -1, 1]  # 상하좌우


def bfs(r, c, h, visited):  # 행, 열, 현재 설정 높이, 방문리스트
    queue = deque([(r, c)])  # 조건에 맞는 시작 위치를 큐에 넣는다.

    while queue:
        r, c = queue.popleft()
            
        for k in range(4):
            nx = r + dx[k]
            ny = c + dy[k]

            if 0 <= nx < N and 0 <= ny < N:  # 이동 좌표가 리스트 범위 내부에 있는지 확인.
                if arr[nx][ny] > h and visited[nx][ny] == 0:
                    # 이동한 곳의 리스트 요소 값이 현재 설정 높이 보다 높고, 방문하지 않은 곳이면
                    visited[nx][ny] = 1
                    queue.append((nx, ny))  # 연결된 안전 영역 추가


for _ in range(N):
    tmp_list = list(map(int, sys.stdin.readline().split()))
    arr.append(tmp_list)
    height.update(tmp_list)

height = sorted(list(height))

for h in range(len(height) - 1):  # 전부 물에 잠기는 경우는 셀 필요가 없으므로 마지막 요소는 고려할 필요가 앖다.
    visited = [[0] * N for _ in range(N)]
    cnt = 0
    for i in range(N):
        for j in range(N):
            if visited[i][j] == 1 or arr[i][j] <= height[h]:
                continue
            visited[i][j] = 1
            bfs(i, j, height[h], visited)
            cnt += 1

    answer = max(answer, cnt)

sys.stdout.write(f"{answer}")
