import heapq
def solution(jobs):
    heap = []
    start = -1 #마지막으로 완료한 작업의 시작시간
    now, cnt, total = 0, 0, 0 #현재 시점, 처리 개수, 누적 시간
    while cnt < len(jobs):
        for j in jobs:
            if start < j[0] <= now:
                heapq.heappush(heap, [j[1], j[0]]) #현재 처리 가능한 작업일 경우 소요시간, 요청시간 순으로 push (소요시간 최소힙을 구성하기 위해)
        if heap:#현재 처리할 수 있는 작업이 있는 경우
            current_task = heapq.heappop(heap)
            start = now #현재 시점으로 부터 작업이 시작되므로
            now += current_task[0] #현재 시점 + 작업 소요시간
            total += now - current_task[1] #요청에서 종료까지 시간
            cnt += 1
        else:#현재 처리할 수 있는 시간이 없는 경우 현재 시점 +1
            now += 1
            
    return total // len(jobs)