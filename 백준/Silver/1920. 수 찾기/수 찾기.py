# 백준 S4.수 찾기

import sys

N = int(sys.stdin.readline())

origin = set(sys.stdin.readline().split())

sys.stdin.readline()

for checkNum in sys.stdin.readline().split():
    if checkNum in origin:
        print(1)
    else:
        print(0)