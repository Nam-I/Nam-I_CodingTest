# 백준 1715.G4.카드 정렬하기_파이썬 우선순위 큐(힙) 풀이
# 체감 난이도: G4 정도 난이도가 맞는 것 같다.
# 작은 묶음 부터 합치는건가..? 라는 생각은 들었지만 확실하다는 생각이 안들어서 조금 헤맸다.
# 처음 부터 풀이의 맥락을 잘못 짚으면 시간 낭비를 하게되니까 이거에 대한 두려움이 너무 큰거 같다.
"""
# 핵심 내용
- 언제나 가장 작은 묶음과 합치는 것이 유리 => 우선 순위 큐
즉, 합쳐진 카드 묶음이 아직 합치지 않은 카드 묶음 보다 작을 수 있다.
- 나의 풀이를 의심하지 말고 틀리더라도 빨리 다른 풀이로 넘어갈 수 있게
빨리 코드 짜보고 빨리 다른 풀이로 넘어가기
"""

import sys
import heapq as hq

N = int(sys.stdin.readline())
card = [int(sys.stdin.readline()) for _ in range(N)]
hq.heapify(card)  # 리스트를 자동으로 최소힙으로 만들어줌

compare_cnt = 0  # N이 1일 때 반복문 거치지 않고 바로 0 출력

while len(card) > 1:  # 카드 리스트 길이가 1이면 더이상 합칠 카드 덱이 없고 모두 합쳐진 하나의 덱만 있음.
    merge = hq.heappop(card) + hq.heappop(card)  # 최소 묶음끼리 합치기
    compare_cnt += merge  # 비교 횟수 누적, 단순히 카드 개수를 누적하는 것과
    # 비교 횟수만 누적하는 것의 결과가 다름.
    hq.heappush(card, merge)  # 합친 카드 더미를 다시 카드 힙에 넣음(합친 카드덱이 더 작을 수 있음)

sys.stdout.write(f"{compare_cnt}")