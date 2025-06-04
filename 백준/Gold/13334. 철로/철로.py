# 백준 13334.G2.철로_파이썬 우선순위 큐 풀이
# 체감 난이도: 어떤 식으로 풀어야하는지 대충 감은 잡히는데 은근히 디테일한 부분을 놓칠 수 있는 문제
# 항상 뭔가 조금이라도 번거로워지면 설마 이게 정답이겠어 싶은데, 진짜 그게 정답일 때가 있어서 헷갈릴 때가 많다.
"""
# 핵심 내용
- 두번 정렬하는게 정답일 때도 있다.
- 문제가 잘 안풀릴 때는 생각의 전환을 해보자
ex) 시작점 기준 X -> 끝 지점 기준 O
"""
import sys
import heapq as hq

n = int(sys.stdin.readline())

locations = []  # 집, 사무실 위치들 저장
start_points = []  # 시작 지점 저장
answer = 0

for _ in range(n):
    tmp = sorted(list(map(int, sys.stdin.readline().split())))  # 더 작은 좌표를 시작점으로 저장
    hq.heappush(locations, tmp)   # 최소힙에 의해 시작점 기준 오름차순 정렬

locations.sort(key=lambda x: x[1])  # 끝 지점 기준 오름차순으로 또 정렬
# 결과적으로 끝 지점이 가까운 것부터 정렬되고 끝지점이 같으면 시작지점이 가까운 곳이 앞쪽에 위치.

d = int(sys.stdin.readline())  # 선분 길이

for location in locations:
    start, end = location
    hq.heappush(start_points, start)  # 시작지점을 시작점 힙에 넣음
    line_start = end - d

    while start_points and start_points[0] < line_start:  # 선분 범위에 시작지점이 포함되지 않으면
        hq.heappop(start_points)  # 기준 미달 heappop()

    answer = max(answer, len(start_points))  # 좌표들을 돌면서 범위에 가장 많은 사람이 포함된 것을 저장

sys.stdout.write(f"{answer}")