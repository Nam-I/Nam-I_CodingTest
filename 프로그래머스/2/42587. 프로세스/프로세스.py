from collections import deque
def solution(priorities, location):
    queue = deque([(i, p) for i, p in enumerate(priorities)]) #인덱스와 값을 튜플 형태로 묶어 큐에 저장
    answer = 0
    while True:
        cur = queue.popleft() #큐의 가장 앞(왼쪽) 원소 가져오기
        if any(cur[1] < q[1] for q in queue): #popleft() 한 원소의 value 보다 큰 수가 큐에 남아있는지 확인하기
            queue.append(cur) #큐에 더 큰 value가 있으면 popleft() 한 값을 제일 뒤(오른쪽)에 다시 넣음
        else:
            answer += 1 #popleft() 한 값이 가장 큰 값이면 다음으로 바로 실행되는 것이므로 순서 누적
            if cur[0] == location: #이때 popleft() 한 것의 index 값이 location 값과 같으면 목표로한 프로세스이므로 순서 return
                return answer