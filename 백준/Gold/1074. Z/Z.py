# 백준 1074.G5.-Z_파이썬 풀이
# 체감 난이도: 개수 count 가 0부터라는 것을 테스트케이스를 하나하나 보지 않으면 모를 수 있다. 그래서 테스트케이스를 보면서
# "왜 개수가 하나씩 부족하지..? 내가 잘못 이해했나?" 했는데 count 시작이 0 이었다... 이런 함정을..?
# 풀이 자체는 사실 구현에 집중한 문제라 어렵지는 않았다. count 를 0부터 하는 것이 의외의 복병...ㅋㅋ
'''
#핵심 내용
- 풀릴 것 같은 실마리라도 보이면 포기하지 말고 일단 코드를 짜보면서 조금씩 길을 찾아나가자.
- 끈기 있게 계속 찍어 보면서 테스트케이스에 맞게 수정하면 된다.
- 끈기 있게 풀어나가는게 제일 중요한 문제인 듯.
'''

import sys


def countNum(row, col):
    if row == 0 and col == 0:
        return 1
    elif row == 0 and col == 1:
        return 2
    elif row == 1 and col == 0:
        return 3
    elif row == 1 and col == 1:
        return 4


N, r, c = map(int, sys.stdin.readline().split())

if N == 1:
    print(countNum(r, c)-1)
else:

    div_half = 2 ** N
    answer = -1  # 순서 시작이 0부터이므로 (0,0)위치가 일반적인 순서의 1이 아니라 0을 반환해야 한다. 그러므로 초기값을 -1로 지정
    location = 0
    while div_half > 2:  # 최소 박스크기 까지 나눠준다.＼＼

        div_half = div_half // 2

        if r < div_half and c < div_half:  # Z의 탐색 순서에 따라 현재 박스에서 4등분 했을 때 위치(location)가 어디인지 확인한다.
            location = 1
        elif r < div_half <= c:
            location = 2
        elif c < div_half <= r:
            location = 3
        elif r >= div_half and c >= div_half:
            location = 4

        answer += div_half ** 2 * (location - 1)  # 찾으려는 위치가 있는 박스 앞에 박스들에 있는 요소 개수를 세어준다.

        if c >= div_half:  # 좌표 평행이동.
            c -= div_half
        if r >= div_half:
            r -= div_half
            
    answer += countNum(r, c)
    print(answer)