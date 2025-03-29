def solution(n, wires):
    graph = [[] * 1 for _ in range(n + 1)]
    answer = n

    # print(graph)

    # 인접 리스트 생성 및 노드 별 연결 노드 저장
    for i in range(len(wires)):
        start = wires[i][0]
        end = wires[i][1]

        graph[start].append(end)
        graph[end].append(start)

    for i in range(len(wires)):
        v1 = wires[i][0]
        v2 = wires[i][1]

        visited = [False] * (n + 1)

        graph[v1].remove(v2)
        graph[v2].remove(v1)

        cnt = dfs(1, graph, visited)

        answer = min(answer, abs(cnt * 2 - n))

        graph[v1].append(v2)
        graph[v2].append(v1)

    return answer


def dfs(start, graph, visited):
    visited[start] = True
    cnt = 1

    for v in graph[start]:
        if visited[v]: continue
        cnt += dfs(v, graph, visited)

    return cnt