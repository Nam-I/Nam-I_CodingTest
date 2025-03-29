def solution(order):
    stack = []
    cnt = sorted(order, reverse=True)

    answer = 0
    while cnt:
        if cnt[-1] == order[answer]:
            answer += 1
            cnt.pop()
            continue
        if stack and stack[-1] == order[answer]:
            answer += 1
            stack.pop()
            continue

        stack.append(cnt.pop())

    for i in stack[::-1]:
        if i == order[answer]:
            answer += 1
        else:
            break

    return answer