# 프로그래머스 Lv.3_가장 먼 노드_파이썬 파이썬 bfs 풀이
# 체감 난이도: 굉장히 쉽게 풀릴거라고 생각했는데 예상 외로 시간이 조금 걸렸다
# 물론 난 어렵게 풀었지만 구현과 문제 이해 난이도 자체는 크게 어렵지 않아서
# 딱 Lv.3 정도 난이도? 혹은 조금 더 쉬운 느낌
# 그래프 탐색 문제 많이 풀어봐야겠다.
"""
# 핵심 내용
- 루트 노드의 높이에 해당하는 노드의 개수를 구하는 문제
- 노드의 높이: 높이를 구하고자 하는 노드에서 리프 노드까지 가장 긴 경로의 간선(edge)의 수
- 무조건 재귀로 풀지말고 bfs 를 잘 활용하자.
"""

from collections import deque


def solution(n, vertex):
    graph = [[] for i in range(n + 1)]
    edge = [-1] * (n + 1)

    for start, end in vertex:
        graph[start].append(end)
        graph[end].append(start)

    def bfs():
        queue = deque([1])
        edge[1] = 0

        while queue:
            now = queue.popleft()

            for node in graph[now]:
                if edge[node] == -1:
                    queue.append(node)
                    edge[node] = edge[now] + 1

        return edge.count(max(edge))

    return bfs()