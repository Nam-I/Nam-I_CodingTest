def solution(arr):
    check = 0
    i = 0
    m = max(arr)
    while check != len(arr):
        i += 1
        for num in arr:
            if m*i % num == 0:
                check += 1
            else:
                check = 0
                break
    return m*i