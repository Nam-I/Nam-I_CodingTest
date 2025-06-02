# 백준 3190.G4.뱀_파이썬 풀이
# 체감 난이도: 이게 정녕 G4인가효.. G3처럼 느껴졌어효..
# 뱀의 방향 전환이 굉장히 어려웠다. 방향 전환을 모듈러 연산으로 구해낼 수 있는 거였다니!
# 머리 위치에 따라 방향 전환을 했을 때 좌표 이동이 달라졌기 때문에 이 부분을 어떻게 해야할지
# 한참 헤맸다. 이렇게 구하는거였다니.. 그거 외엔 단순 빡구현 문제
"""
# 핵심내용
- 방향 전환 문제에서 방향 전환을 어떤 방식으로 해야할지 감이 안잡힐 때
모듈러 연산으로 구할 수 있는 것은 아닌지 생각해보기
"""

import sys
from collections import deque

s_input = sys.stdin.readline  # 시스템 입력 편의 함수

N = int(s_input().strip())  # 게임판 크기

K = int(s_input().strip())  # 사과 개수

dx = [0, 1, 0, -1]  # → ↓ ← ↑ 이동 방향
dy = [1, 0, -1, 0]

board = [[0] * N for _ in range(N)]  # 게임판 생성 # 기본: 0, 사과: 1, 뱀의 몸: 2

for _ in range(K):  # 게임판에 사과 위치 저장
    x, y = map(int, s_input().split())
    board[x-1][y-1] = 1  # 사과의 값 => 1

L = int(s_input().strip())  # 방향 전환 횟수

L_arr = []  # 방향 전환 정보 저장 리스트

for _ in range(L):  # 방향 전환 정보 입력
    X, C = s_input().split()
    L_arr.append([int(X), C])

snake = deque([[0, 0]])  # 처음 뱀의 길이와 위치
board[0][0] = 2  # 뱀의 시작점 표시, 뱀의 몸: 2
direction = 0  # 초기 이동 방향 (머리가 오른쪽으로 향하는 방향)
answer = [0]  # 게임 시간 저장


def move():  # 뱀 위치 이동 함수
    x, y = snake[-1]  # 뱀의 머리 좌표 가져오기
    x, y = x + dx[direction], y + dy[direction]  # 뱀의 이동 좌표 저장

    if -1 < x < N and -1 < y < N and board[x][y] < 2:  # 뱀의 이동 좌표가 벽과 몸통에 부딪히는지 확인
        if board[x][y] != 1:  # 진출 가능한 기본칸이면
            board[x][y] = 2  # 뱀의 몸통으로 바꿈
            snake.append([x, y])  # 이동한 뱀의 머리 좌표 넣기
            x, y = snake.popleft()
            board[x][y] = 0  # 뱀 꼬리칸 0으로 바꿔 뱀 길이 줄이기
        else:  # 진출 가능한 사과칸이면
            board[x][y] = 2  # 뱀의 몸통으로 바꿈
            snake.append([x, y])  # 이동한 뱀의 머리 좌표 넣기
    else:  # 진출 불가능한 칸(벽 or 몸통)
        sys.stdout.write(f"{answer[0] + 1}")  # 게임 종료 시간 출력
        exit(0)  # 프로그램 종료

    return 1  # 프로그램이 종료되지 않으면 => 즉, 정상 이동 완료하면 게임 시간+1


for X, C in L_arr:
    time = answer[0]  # 현재 초 가져옴
    for _ in range(time, X):  # 현재 초로부터 방향 전환 시간 직전 까지 현재 방향 그대로 전진
        # 함수를 반환할 때 현재초+1 이므로 방향전환 초 직전까지 반복문을 돌아야
        # 방향전환에 해당하는 시간까지 움직이고 나서 방향전환을 할 수 있다. 선이동 후 방향전환이므로!
        answer[0] += move()

    if C == 'D':  # 우회전
        direction = (direction + 1) % 4
    else:  # C == 'L' 좌회전
        direction = (direction - 1) % 4

while True:  # 방향 전환을 모두 완료한 후에도 게임이 종료되지 않음.
    answer[0] += move()  # move() 에서 종료 조건을 만나면 알아서 종료됨.