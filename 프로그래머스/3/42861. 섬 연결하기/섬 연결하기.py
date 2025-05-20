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