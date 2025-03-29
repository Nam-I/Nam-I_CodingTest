from collections import deque
 
def solution(queue1, queue2):
    answer = 0
    q1 = deque(queue1)
    q2 = deque(queue2)
    sum1 = sum(q1)
    sum2 = sum(q2)
    limit = len(q1) * 3
    
    while sum1 != sum2:
        if sum1 > sum2:
            num = q1.popleft()
            q2.append(num)
            sum1 -= num
            sum2 += num
        elif sum1 < sum2:
            num = q2.popleft()
            q1.append(num)
            sum1 += num
            sum2 -= num
        answer += 1
        
        # 두 큐를 다 왕복했는데도 반복문이 돈다면 같게 만들 수 없는 경우이다.
        if answer == limit: 
            return -1
        
    return answer