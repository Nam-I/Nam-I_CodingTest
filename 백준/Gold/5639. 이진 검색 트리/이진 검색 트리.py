# 백준 5639.G4.이진 검색 트리
# 체감 난이도: 음 솔직히 풀이를 생각해내서 문제를 완벽히 풀어낼 자신은 없는 문제다. 그래도 어떤 식으로 풀어야 할지
# 감은 잡히는 정도? 그렇다면 G4 수준은 맞는거 같다
# 문제를 거의 다 풀어갈 때쯤 항상 뭔가 일이 생겨서 문제 풀이의 흐름이 끊겨
# 매번 다시 풀 때마다 시간도 오래 걸리고 감을 잃은거 같아서 슬프다.
# 문제 자체는 좋은 문제라고 생각한다.

'''
# 핵심 내용
- 왼쪽 쪽 트리와 오른쪽 트리의 시작 지점을 끊임 없이 찾아 나가는 방식으로 문제를 푼다.
- 노드 개수를 알려주지 않아서 이런 문제일 때 어떻게 값을 받아야하는지 알게 해주는 문제이다.
- 재귀 문제라서 재귀 깊이를 정해줘야한다.(안그럼 런타임 에러)
'''


import sys

sys.setrecursionlimit(10 ** 6)

tree = []

while True:
    try:
        tree.append(int(sys.stdin.readline()))
    except:
        break


def postorder(root_idx, last_idx):
    if root_idx > last_idx:
        return

    right_start_idx = root_idx + 1

    while right_start_idx <= last_idx:

        if tree[right_start_idx] > tree[root_idx]:
            break

        right_start_idx += 1

    postorder(root_idx + 1, right_start_idx - 1)
    postorder(right_start_idx, last_idx)

    sys.stdout.write(f"{tree[root_idx]}\n")


postorder(0, len(tree) - 1)