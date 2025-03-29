import sys
import functools

def compare(a,b):
    if len(a) < len(b):
        return -1
    if len(a) == len(b):
        return 0
    if len(a) > len(b):
        return 1


T = int(input())

words = []

for i in range(T):
    s = sys.stdin.readline().strip()
    if s not in words:
        words.append(s)

# words = sorted(words, key=functools.cmp_to_key(compare))
for i in sorted(sorted(words), key=functools.cmp_to_key(compare)):
    print(i)
