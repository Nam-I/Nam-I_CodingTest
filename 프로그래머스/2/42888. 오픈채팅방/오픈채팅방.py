def solution(record):
    state = {"Enter": "님이 들어왔습니다.", "Leave": "님이 나갔습니다."}
    uid = {}
    result = []
    for i in record:
        i = i.split(" ")
        if i[0] == "Enter":
            uid[i[1]] = i[2]
            result.append([i[1], i[0]])
        elif i[0] == "Leave":
            result.append([i[1], i[0]])
        elif i[0] == "Change":
            uid[i[1]] = i[2]

    for j in range(len(result)):
        result[j] = uid[result[j][0]]+state[result[j][1]]

    return result