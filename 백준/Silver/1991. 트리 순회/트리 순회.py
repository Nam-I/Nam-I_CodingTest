# 백준 1991.S1.트리 순회
# 체감 난이도: 난이도에 비해 천천히 풀어서 시간은 좀 걸렸지만 조금만 생각하면 풀 수 있는 문제.
# 구현은 크게 어렵지 않으나 전위, 중위, 후위를 다 구현해야 하기 때문에 S1 난이도를 받은 것 같다.
# 트리는 어디서든 자주 등장하는 부분이니까 코테 문제를 풀면서 트리 관련 용어도 알아가는 좋은 문제리고 생각한다.
"""
# 핵심 내용
- 전위, 중위, 후위 순서 잘 알아두기(루트를 기준으로 순서가 나뉨)
루트를 가장 먼저 탐색 => 전위
루트를 중간에 탐색 => 중위
루트를 마지막에 탐색 => 후위
**왼쪽 오른쪽 순서는 변하지 않음. 언제나 왼쪽 탐색이 먼저.
"""

import sys
from collections import deque

N = int(sys.stdin.readline())  # 노드 개수 입력 받기

tree = [[] for _ in range(N)]  # 트리 생성

for _ in range(N):
    # 문자 자료형으로 들어온 입력을 아스키 코드 숫자 값으로 변환. A가 루트 노드이므로 A 값을 기준으로
    # 0으로 맞춤.
    arr = list(map(lambda x: ord(x) - 65, sys.stdin.readline().split()))

    for v in arr[1:]:
        if v > -1:  # '.'은 숫자로 변환하여 -65를 해주었을 때 음수 값이 나옴으로 양수일 때만 트리에 저장
            tree[arr[0]].append(v)
        else:  # 자식 노드가 없는 곳은 음수 저장
            tree[arr[0]].append(-1)


def preorder():  # 전위순회: 루트 -> 왼 -> 오
    visited = [0]*N
    stack = deque([0])
    s = ""

    while stack:
        node = stack.popleft()  # 루트부터 방문하고 입력
        visited[node] = 1
        s += chr(node+65)

        if tree[node][1] > 0 and visited[tree[node][1]] == 0:  # 오른쪽 자식이 나중에 방문이므로
            # 나중에 popleft() 될 수 있도록 오히려 먼저 왼쪽에 값을 넣음.
            stack.appendleft(tree[node][1])

        if tree[node][0] > 0 and visited[tree[node][0]] == 0:
            # 오른쪽을 먼저 탐색하고 stack 앞쪽에 값을 넣어줬으므로 왼쪽 자식을 stack 앞쪽에 넣으면
            # 다음 반복문 시작에 가장 먼저 popleft()
            stack.appendleft(tree[node][0])

    sys.stdout.write(s + "\n")


def inorder():  # 중위순회: 왼 -> 루트 -> 오
    visited = [0]*N
    stack = deque([0])
    s = ""

    while stack:
        node = stack[0]  # stack 의 맨 앞 요소 가져옴.

        if tree[node][0] > 0 and visited[tree[node][0]] == 0:  # 왼쪽 자식이 있으면 stack 에 넣고 다음 반복
            stack.appendleft(tree[node][0])
            continue

        s += chr(stack.popleft()+65)  # 왼쪽 자식이 더이상 없으면 가장 왼쪽에 위치한 값 popleft()
        visited[node] = 1

        if tree[node][1] > 0 and visited[tree[node][1]] == 0:  # popleft()한 노드에 오른쪽 자식이 있으면
            stack.appendleft(tree[node][1]) # 다음 탐색에 쓰기 위해 stack 가장 앞쪽에 넣기

    sys.stdout.write(s + "\n")


def postorder():  # 후위순회: 왼 -> 오 -> 루트
    visited = [0]*N
    visited[0] = 1
    stack = deque([0])
    s = "A"  # 루트 노드는 항상 가장 마지막

    while 0 in visited: # 모든 노드를 방문할 때까지 반복
        node = stack[0]

        if tree[node][1] > 0 and visited[tree[node][1]] == 0:  # 먼저 넣은 것이 나중에 출력
            stack.appendleft(tree[node][1])  # 따라서 오른쪽을 먼저 탐색하여 모두 넣기
            visited[tree[node][1]] = 1
            s = chr(tree[node][1]+65) + s
            continue

        if tree[node][0] > 0 and visited[tree[node][0]] == 0:  # 오른쪽 모두 탐색 후 왼쪽 탐색
            stack.appendleft(tree[node][0])
            visited[tree[node][0]] = 1
            s = chr(tree[node][0]+65) + s
            continue

        stack.popleft()  # 현재 노드에서 더이상 갈 곳이 없으면 popleft()

    sys.stdout.write(s + "\n")


preorder()  # 전위 순회
inorder()  # 중위 순회
postorder()  # 후위 순회