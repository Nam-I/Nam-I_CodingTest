# 프로그래머스 Lv.3_섬연결하기_파이썬 deque 풀이
# 탐욕법의 개념을 사용한 풀이이지만 직관적으로 이해하기 쉬운 풀이
# 체감 난이도: 탐욕법(greedy) 문제를 많이 안풀어봐서 약간 겁먹은 것 같다. 확실히 알고리즘적으로 모르는 부분이 나오면
# 어떻게 접근해야할지 바로 안떠오르는 것 같다. 막상 이해하고 나면 Lv.3 치고 어렵지 않은 문제이다.
# 탐욕법(greedy) 개념 이해 문제로 괜찮은 문제인 것 같다.
"""
# 핵심 내용
- 최소 힙(heaqp) 를 이용해서도 풀 수 있는 문제이다. 힙 활용 다시 한 번 익혀두기
- 탐욕법은 최소비용 문제이고, "오름차순정렬->리스트 순서대로 사이클을 이루지 않는 최소 비용 간선 선택->최소 신장 트리 형성" 순서로 진행
"""

from collections import deque


def solution(n, costs):
    costs = deque(sorted(costs, key=lambda x: x[2]))  # 비용 기준 오름차순 정렬
    answer = costs[0][2]  # 첫번째 요소 비용 추가, 첫 요소는 무조건 추가될 것이므로
    link = set(costs.popleft()[:2])  # 처음 요소 노드는 중복 값이 있을 수 없으므로 무조건 추가
    n -= 2  # 노드가 두개 추가됐으므로 섬 개수 -2

    while n > 0:
        for cost in costs:
            if cost[0] in link and cost[1] in link:  # 이미 연결 된 경로면 continue
                continue
            elif cost[0] in link or cost[1] in link:  # 연결된 link 에서 연장할 수 있으면
                link.update([cost[0], cost[1]])  # link 연장
                answer += cost[2]  # 최소 비용 추가
                n -= 1  # 섬 개수 -1
                break  # 최소 비용 순으로 다시 훑으면서 link 에 연장할 수 있는지 탐색

    return answer