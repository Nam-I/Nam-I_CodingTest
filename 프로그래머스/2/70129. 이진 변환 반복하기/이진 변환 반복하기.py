def solution(s):
    answer = []
    zero = 0
    trans = 0
    while True:
        if s == "1":
            break;
        zero += s.count("0")
        s = s.replace("0", "")
        s = format(len(s), 'b')
        trans += 1
    answer = [trans, zero]
    return answer