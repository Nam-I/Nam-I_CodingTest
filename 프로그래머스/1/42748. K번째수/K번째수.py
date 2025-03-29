def solution(array, commands):
    r = []
    for i in range(len(commands)):
        r.append(sorted(array[commands[i][0]-1:commands[i][1]])[commands[i][2]-1])
    return r