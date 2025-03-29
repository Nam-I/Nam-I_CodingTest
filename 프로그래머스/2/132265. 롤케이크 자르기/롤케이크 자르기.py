from collections import Counter
def solution(topping):
    #dic과 set을 케익 두 조각으로 생각
    dic = Counter(topping) 
    set_dic = set() #처음은 set에 아무것도 들어있지 않은, 즉 나눠지지 않은 홀케익 상태
    answer = 0
    for i in topping:
        dic[i] -= 1 #topping에서 조각을 나눌 때마다 dic에서는 요소가 사라지는 것임.
        set_dic.add(i) #dic에서 사라지면 set에 추가됨. 케익이 두조각으로 나눠지는 개념
        if dic[i] == 0: #dic에서 요소가 0이 되면 그 토핑은 사라짐.
            del dic[i]
        if len(dic) == len(set_dic): #dic에 남은 토핑 가지 수와 set에 추가된 토핑 가지수가 같으면 같은 토핑 가지수로 나눠진 것임.
            answer += 1
    return answer

