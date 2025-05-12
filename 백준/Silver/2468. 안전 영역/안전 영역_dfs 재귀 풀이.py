# 백준 2468.S1.안전 영역_dfs 재귀 풀이
# 체감난이도: dfs 를 떠올리기는 쉬웠지만 조건에 맞춰 여러번 계산을 해줘야하고
# 구현에서 실수하기 쉬운 요소들이 있어서 S1 보다 어렵게 느껴진다. 저번에 풀었던 G4가 더 쉬웠던 것 같은..
"""
#핵심 내용
- dfs/bfs 두가지 풀이가 가능한 문제
- 개인적으로 bfs 로 푸는 것이 더 좋은 것 같다. dfs 풀이는 재귀 깊이 제한을 해제해 주어야 하기 때문에 더 번거로울 수 있다.
- 상하좌우 탐색과 경계 지점 잘 설정해서 탐색하기
- 안전영역을 구하는 것에서 끝이 아니라 가장 안전영역이 많을 때의 개수 구하기라는 것.
- 땅이 모두 잠기지 않는 경우도 고려해 안전 영역의 최대 개수는 항상 1보다 크거나 같다.
"""

import sys
sys.setrecursionlimit(10 ** 4)  # 재귀 깊이 늘리기 sys.setrecurtionlimit(10**6) 을 많이 설정해주는 것 같은데,
# 이때 메모리 초과가 난다면 지수 수를 더 작게 해보자.

N = int(sys.stdin.readline())  # 높이 정보 N*N 개수

arr = []  # 높이 정보를 저장할 땅
height = set()  # 땅의 높이를 중복 없이 저장
answer = 1  # 어떤 땅도 잠기지 않는 경우가 있으므로 정답 최소 값은 1

dx = [-1, 1, 0, 0]  # 상하좌우
dy = [0, 0, -1, 1]  # 상하좌우


def dfs(r, c, h, visited): # 행, 열, 현재 설정 높이, 방문리스트
    for k in range(4):
        if 0 <= r+dx[k] < N and 0 <= c+dy[k] < N:  # 이동 좌표가 리스트 범위 내부인지 확인.
            if arr[r + dx[k]][c + dy[k]] > h and visited[r + dx[k]][c + dy[k]] is False:
                # 이동한 리스트 요소 값이 현재 설정 높이보다 높고 방문하지 않은 곳인 경우
                visited[r + dx[k]][c + dy[k]] = True  # 방문 true
                dfs(r+dx[k], c+dy[k], h, visited)  # 이동 좌표를 현재 좌표로 설정 재귀 탐색.


for _ in range(N):
    tmp_list = list(map(int, sys.stdin.readline().split()))
    arr.append(tmp_list)
    height.update(tmp_list)

height = sorted(list(height))

for h in range(len(height)-1):  # 전부 물에 잠기는 경우는 셀 필요가 없으므로 마지막 요소는 고려할 필요가 앖다.
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
