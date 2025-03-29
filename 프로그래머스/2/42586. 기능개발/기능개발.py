def solution(progresses, speeds):
    answer = []
    count = 1
    for i in range(len(progresses)):
        value, remain = divmod((100 - progresses[i]), speeds[i])
        if remain > 0:
            value += 1
        if i == 0:
            max = value
        elif i > 0 and value > max:
            answer.append(count)
            count = 1
            max = value
        elif i > 0 and value <= max:
            count += 1
        if i == len(progresses)-1:
            answer.append(count)

    return answer