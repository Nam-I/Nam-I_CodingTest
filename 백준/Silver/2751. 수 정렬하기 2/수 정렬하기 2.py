import sys

T = int(input())

num = []
for i in range(T):
    num.append(int(sys.stdin.readline().strip()))
for i in sorted(num):
    print(i)