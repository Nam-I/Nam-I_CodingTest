# 프로그래머스 Lv.3_섬연결하기_파이썬 heapq(최소힙) 풀이
# 체감 난이도: 탐욕법(greedy) 문제를 많이 안풀어봐서 약간 겁먹은 것 같다. 확실히 알고리즘적으로 모르는 부분이 나오면
# 어떻게 접근해야할지 바로 안떠오르는 것 같다. 막상 이해하고 나면 Lv.3 치고 어렵지 않은 문제이다.
# 탐욕법(greedy) 개념 이해 문제로 괜찮은 문제인 것 같다.
"""
# 핵심 내용
- 문제에 최소힙을 이용하는 이유
: 다른 풀이는 오름차순 정렬을 한 번 해주지만 heap 은 값을 추가하면 자동으로 첫번째 요소를 기준으로
오름차순 정렬을 해주기 때문.
- 최소 힙(heaqp) 를 이용해서도 풀 수 있는 문제이다. 힙 활용 다시 한 번 익혀두기
- 탐욕법은 최소비용 문제이고, "오름차순정렬->리스트 순서대로 사이클을 이루지 않는 최소 비용 간선 선택->최소 신장 트리 형성" 순서로 진행
"""

import heapq as hq


def solution(n, costs):
    answer = 0
    arr = [[] for _ in range(n)]  # 노드 별로 끝 지점과 비용 쌍 형식으로 재정렬할 리스트
    visited = [False] * n
    priority = []

    for a, b, cost in costs:  # 섬(노드)별로 끝 지점과 비용 쌍 추가해주기
        arr[a].append((b, cost))
        arr[b].append((a, cost))

    hq.heappush(priority, (0, 0))  # 시작 섬(노드)와 비용 초기화
    
    while False in visited:
        cost, start = hq.heappop(priority)  # 연결할 수 있는 섬 중 최소 비용 pop
        
        if visited[start]:
            continue

        visited[start] = True
        answer += cost  # 방문하지 않은 곳이면 연결하고 비용 추가
        
        for end, cost in arr[start]:  # 섬(노드)와 연결된 길(edge)를 순회
            if visited[end]:  # 이미 연결된 곳이면 건너뛰기
                continue
            else:
                hq.heappush(priority, (cost, end))  # 연결할 수 있는 섬이면 힙에 추가
                # 힙(최소힙)에서 비용 최소 순 오름차순으로 알아서 정렬이 됨.

    return answer
