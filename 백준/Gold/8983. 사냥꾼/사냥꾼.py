from bisect import bisect_left
input = __import__('sys').stdin.readline
m,n,l = map(int,input().split())
people = sorted(list(map(int,input().split())))
cnt = 0
for _ in range(n):
    x,y = map(int,input().split())
    if y>l:continue
    nx = bisect_left(people,x)
    for idx in (nx,nx-1):
        if 0<=idx<m:
            if abs(people[idx]-x)+y<=l:
                cnt+=1
                break
print(cnt)