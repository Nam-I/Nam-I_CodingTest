def solution(want, number, discount):
    answer = 0
    dict = {}
    for i in range(len(want)):
        dict[want[i]] = number[i]

    for j in range(len(discount)-sum(number)+1):
        count = 0
        for k, v in dict.items():
            if discount[j:10+j].count(k) < v:
                break
            elif discount[j:11+j].count(k) >= v:
                count += 1
                if count == len(dict):
                    answer += 1
    return answer