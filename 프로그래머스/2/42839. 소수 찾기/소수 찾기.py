from itertools import permutations


def solution(n):
    answer = 0
    a = set()
    for i in range(len(n)):
        a |= set(map(int, map("".join, permutations(list(n), i + 1))))

    for n in a:
        if isPrime(n):
            answer += 1

    return answer


def isPrime(n):
    if n < 2:
        return False
    for i in range(2, n):
        if n % i == 0:
            return False

    return True