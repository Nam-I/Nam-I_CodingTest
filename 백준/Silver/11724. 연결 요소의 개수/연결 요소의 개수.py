# 백준 11724.S2 연결 요소의 개수 : 파이썬 BFS 풀이
# 체감 난이도: 사실 어려운 문제는 아니었는데 반복문을 2개 이상 쓰는 풀이는 절대 아닐거라고 생각해서
#다른 풀이법 생각하느라 좀 오래 걸렸다. 근데 반복문 떡칠이 정답이라니.. 역시 어떤 방법이든 그냥 푸는게 더 중요한 듯
"""
#핵심 내용
- 저번에 풀었던 문제 처럼 DFS, BFS 풀이 모두 가능한 문제(stack - DFS, deque - BFS)
- 간선이 없는 자기 자신만 있는 노드도 연결 노드의 한 덩어리로 쳐서 count 해야한다.
"""

import sys
from collections import deque

N, M = map(int, sys.stdin.readline().strip().split())

graph = [[] for _ in range(N+1)]
visited = [0] * (N+1)
visited[0] = 1
dq = deque([])
answer = 0

for _ in range(M):

    start, end = map(int, sys.stdin.readline().strip().split())

    graph[start].append(end)
    graph[end].append(start)

while 0 in visited:
    idx = visited.index(0)

    if idx:
        visited[idx] = 1

        dq.append(idx)

        while dq:
            start = dq.popleft()

            for node in graph[start]:
                if visited[node] == 0:
                    dq.append(node)
                    visited[node] = 1

        answer +=  1

sys.stdout.write(f"{answer}")


"""
풀이에 도움되는 입력 예제

input:
2 0

output:
2
"""