def groupCheck(s):
    check = ''
    for i in s:
        for j in i:
            if j not in check:
                check += j
            else:
                if check[-1] != j:
                    return 0
        check = ''
    return 1

T = int(input())

group = 0
for _ in range(T):
    s = input().split(" ")
    group += groupCheck(s)
print(group)