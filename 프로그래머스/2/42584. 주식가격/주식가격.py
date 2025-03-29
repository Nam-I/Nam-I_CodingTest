def solution(price):
    l_price = len(price)
    stack = []
    answer = [0]*l_price
    for i in range(l_price-1):
        if not stack or stack[-1][1] <= price[i]:
            stack.append((i, price[i]))
            continue
        elif stack[-1][1] > price[i]:
            while stack and stack[-1][1] > price[i]:
                idx, v = stack.pop()
                answer[idx] = i-idx
            stack.append((i, price[i]))
    answer[-2] = 1

    for j in stack:
        answer[j[0]] = l_price-1-j[0]

    return answer
