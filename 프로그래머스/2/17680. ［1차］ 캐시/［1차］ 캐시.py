from collections import deque
def solution(cacheSize, cities):
    answer = 0
    storage = ["0"]*cacheSize
    storage = deque(storage)
    if cacheSize < 1:
        return len(cities)*5

    for i in range(len(cities)):
        compare = cities[i].lower()
        if compare in storage:
            answer += 1
            storage.remove(compare)
            storage.append(compare)
        else:
            answer += 5
            if "0" in storage:
                storage[storage.index("0")] = compare
            else:     
                storage.popleft()
                storage.append(compare)

    return answer