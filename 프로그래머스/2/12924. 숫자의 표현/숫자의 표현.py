def solution(n):
    result = 1
    add = 0
    for i in range(1, n):
        for j in range(i, n):
            add += j
            if add == n:
                result += 1
                add = 0
                break;
            elif add > n:
                add = 0
                break;
                
    return result