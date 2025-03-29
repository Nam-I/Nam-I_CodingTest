def convert(number, n):
    if number == 0:
        return '0'
    NUMBERS = "0123456789ABCDEF"
    res = ""
    while number > 0:
        number, mod = divmod(number, n)
        res += NUMBERS[mod]
    return res[::-1]

def solution(n, t, m, p):
    answer = ''
    game = '0'
    for num in range(1, t * m+1):
        game += convert(num, n)
    while 1:
        if len(answer) == t:
            break
        answer += game[p-1]
        p += m
    return answer