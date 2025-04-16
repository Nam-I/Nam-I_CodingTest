#백준 2628.S5.- 종이자르기_파이썬 풀이
#체감 난이도: 파이썬으로 풀면 시간단축, 코드 단축 부분에서 고민을 많이하게 되지만
#직관적으로 문제를 풀어 나가는 데에는 훨씬 쉬운 것 같다. 하지만 자바 보다는 확실이 실행시간이 걸림.
#정렬 부분에서 자바보다 코드 길이를 확 줄일 수 있다.

w, h = map(int, input().split(" "))
n = int(input())

rowArr = [0, h]
columnArr = [0, w]

for i in range(n):
    style, idx = map(int, input().split(" "))
    if style == 0:
        rowArr.append(idx)
    else:
        columnArr.append(idx)

rowArr = sorted(rowArr)
columnArr = sorted(columnArr)

print(max([rowArr[r+1] - rowArr[r] for r in range(len(rowArr)-1)])
      *
      max([columnArr[c+1] - columnArr[c] for c in range(len(columnArr)-1)])
      )