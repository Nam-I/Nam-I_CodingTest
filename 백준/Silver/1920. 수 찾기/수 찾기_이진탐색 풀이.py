# 백준 S4.수 찾기_파이썬 이진탐색(이분탐색) 풀이
# 체감 난이도: 아무리 4 난이도라지만 실버 치고는 너무 쉬운 문제 아닌가? 라고 생각했다.
# 시간 제한이 빡빡헸다고 해도 문제 이해 부터가 쉬워서 이것 저것 시도하면 금방 풀릴 문제.
"""
# 핵심내용
- 배열을 탐색하는 것보다 map 과 for 문을 더 사용하더라도 set 집합이나 dict 해시를
탐색하는 것이 더 빠르다.
- 이진탐색(이분탐색) 풀이를 더 눈여겨 보고 기억해둘 것.
"""

import sys

N = int(sys.stdin.readline())

origin_arr = sorted(sys.stdin.readline().split())

sys.stdin.readline()

check_arr = sys.stdin.readline().split()

for check_num in check_arr:
    lt, rt = 0,  N-1
    isExist = False

    while lt <= rt:
        mid = (lt + rt) // 2

        if check_num == origin_arr[mid]:
            isExist = True
            sys.stdout.write("1\n")
            break
        elif check_num > origin_arr[mid]: # origin[mid]가 check_num 보다 작다면
            lt = mid + 1  # origin_arr[mid]를 포함한 더 작은 값은 비교할 필요가 없으므로
            # 왼쪽 포인트의 인덱스를 중간 인덱스 보다 큰 값으로 줄인다.
        else:  # origin[mid]가 check_num 보다 큰 경우
            rt = mid - 1  # origin_arr[mid]를 포함한 더 큰 값은 비교할 필요가 없으므로
            # 오른쪽 포인트의 인덱스를 중간 인덱스 보다 작은 값으로 줄인다.

    if not isExist:
        sys.stdout.write("0\n")
