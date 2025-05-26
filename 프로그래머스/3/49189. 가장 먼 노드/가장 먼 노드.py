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
    edge = [-1] * (n + 1)  # 방문 여부 확인과 지나온 간선 수를 기록

    for start, end in vertex:
        graph[start].append(end)
        graph[end].append(start)

    def bfs():
        queue = deque([1])  # 루트 노드에서 시작
        edge[1] = 0  # 시작 노드의 간선 수 0

        while queue:
            now = queue.popleft()

            for node in graph[now]:  # 현재 노드와 연결되 노드 확인
                if edge[node] == -1:  # 연결된 노드가 방문하지 않은 노드인지 확인
                    queue.append(node)  # 이동 가능한 노드이면 큐에 넣는다.
                    edge[node] = edge[now] + 1  # 현재 노드에서 이동 가능한 노드로 이동하면
                    # 간선을 하나 지나온 것이므로 현재 노드에 오기까지 지나온 간선 개수 + 1

        return edge.count(max(edge))  # 가장 큰 간선 수에 해당하는 수를 edge 리스트에서 세어서 반환.

    return bfs()