# 백준 8983.G4.사냥꾼_파이썬 이진 탐색 풀이
# 체감 난이도: 그냥 문제만 보고 바로 풀었을 때는 어려움 없이 40점을 받았다.
# 100점을 받기 어려운 문제인 것 같다. 이진 탐색 문제인 것을 떠올리기가 어렵다.
"""
# 핵심 내용
- 이진 탐색으로 반환한 값의 바로 오른쪽 값이 동물과 더 가까운 사대일 수 있는 예외사항을 잘 파악해야 한다.
- bisect 모듈을 이용한 풀이가 시간은 더 적게 걸린다.(실전에서 써먹기 어려울 것 같아서 기억만 해둘 예정)
- 예외 사항에 해당하는 입력 예제(start 와 end 의 위치 역전, 이진탐색 반환 값보다 오른쪽 값이 더 동물과 가까운 경우)
4 1 2
1 2 3 10
9 0
"""

import sys

M, N, L = map(int, sys.stdin.readline().split())

x_arr = sorted(list(map(int, sys.stdin.readline().split())))
answer = 0


def binary_search(a, b):
    start, end = 0, M - 1

    while start <= end:
        mid = (start + end) // 2
        
        if x_arr[mid] == a:
            return mid  # 사대의 x 좌표와 동물 a 좌표가 같으면 해당 사대 인덱스 반환
        elif x_arr[mid] < a:
            start = mid + 1  # 사대 x 좌표가 작으면 start 값을 mid+1 로 오른쪽으로 당겨옴.
        else:
            end = mid - 1  # 사대 x 좌표가 크면 end 값을 mid-1 로 왼쪽으로 당겨옴.

    return end  # 조건에 맞는 값이 없어 start 와 end 의 위치가 역전될 경우 end 가
    # left point(왼쪽 값)이 되므로 end 를 반환


for _ in range(N):
    a, b = map(int, sys.stdin.readline().split())

    if b > L:  # 동물의 b 좌표가 사정거리를 벗어나면 맞출 확률이 없음. 사대의 y 좌표는 0으로 고정이므로.
        continue

    idx = binary_search(a, b)

    # 가장 가까운 사대 좌표 값을 구했지만 그 값의 바로 오른쪽에 위치한 사대 좌표 값이 
    # 동물과 더 가까운 사대의 위치가 될 때가 있음을 고려해야 한다.
    if abs(x_arr[idx]-a)+b <= L:  # 동물이 x 좌표가 가장 가까운 사대의 사정거리에 들어오는지 확인.
        answer += 1  # 사냥할 수 있는지만 보면 되므로 사정거리 이내면 카운트하고 더 볼 필요 없음.
    elif idx+1 < M and abs(x_arr[idx+1]-a)+b <= L:  # 첫번째 조건에서 사정거리에 들어오지 않는다면
        answer += 1  # 이진탐색을 통해 구한 사대 위치의 바로 오른쪽 사대에서는 동물이 사정거리에 들어오는지 확인.

sys.stdout.write(f"{answer}")
