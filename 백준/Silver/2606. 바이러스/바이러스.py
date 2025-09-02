# 백준 2606.S3.바이러스
# 체감 난이도: 딱 S3 정도로 문제 해결 방법이 어느 정도 눈에 보인다.
# 그런데 DFS/BFS 까지의 문제인지는 몰랐다.

'''
# 핵심 내용
- DFS/BFS 모두 가능한 문제(난 그렇게 풀진 않았지만 DFS 재귀 풀이도 있었음)
- stack 풀이 -> DFS
: 현재 노드와 연결된 모든 노드 stack.append(), pop() 하면 가장 최근에 탐색한 연결노드
여기서 다시 연결 노드 탐색 -> 연결 노드에서 점차 깊이 들어가게되는 구조
- queue or deque 풀이 -> BFS
: 현재 노드에서 연결된 모든 노드 deque.append(), popleft() 하면
결국 이전 노드에서 연결된 다음 레벨의 노드들을 모두 방문하고 그 다음 레벨로 넘어가는 구조

'''


import sys

N = int(sys.stdin.readline()) # 컴퓨터(노드) 개수
pair = int(sys.stdin.readline()) # 쌍 정보
network = [[] for _ in range(N+1)] # 쌍 정보와 연결되는 네트워크 graph
visited = [0] * (N+1) # 노드 방문 여부 체크
stack = [1] # 1과 연결된 컴퓨터(노드)만 확인
visited[1] = 1 # 1번과 연결된 컴퓨터만 확인하므로 1은 무조건 방문

for _ in range(pair):
    # 현재 노드, 연결 노드
    cur_node, linked_node = map(int, sys.stdin.readline().strip().split())

    # 노드 연결 정보가 오름 차순으로 제공된다는 보장이 없으므로
    # 한쪽에만 연결 정보를 넣으면 제대로 탐색이 안될 수 있음
    network[cur_node].append(linked_node)
    network[linked_node].append(cur_node)


while stack: # stack 에 값이 있으면 반복

    cur_node = stack.pop()

    for node in network[cur_node]:

        if visited[node] == 0: # 방문하지 않은 노드면서 1과 연결된 노드
            stack.append(node) # 탐색 목록에 추가
            visited[node] = 1 # 방문 표시


sys.stdout.write(f"{sum(visited)-1}") # 방문한 노드 개수에서 노드 1 제외


"""
#풀이에 도움이 된 반례
input:
7
7
7 6
7 1
5 1
2 6
4 3
7 3
3 1
output:
6
"""
