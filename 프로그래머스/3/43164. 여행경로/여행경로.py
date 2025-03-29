from collections import defaultdict
def solution(tickets):
    t_dict = defaultdict(list)    #list 형식으로 초기화 해준다는 것을 명시.
    
    for s, e in tickets:
        t_dict[s].append(e) #출발지는 key, 목적지는 value.
    
    for t_key in t_dict.keys():   #key에 해당하는 목적지 요소를 알파벳 내림 차순으로 정렬(스택으로 pop하기 위해)
        t_dict[t_key].sort(reverse=True)
    answer = []
    path = ["ICN"]  #항상 ICN에서 출발함.
    while path:
        now = path[-1]
        if now not in t_dict or len(t_dict[now]) == 0:   #공항이 목적지로만 존재할 경우 or 티켓이 모두 소진된 경우
            answer.append(path.pop())
        else:
            path.append(t_dict[now].pop())
    return answer[::-1] #리스트를 뒤집은 것이 최종 정답임.