big = ["A","B","C","D","E","F"]
def solution(n, t, m, p):
    a="0"
    i=0
    #for i in range(t*m):
    while True:
        if len(a)>=t*m:
            break
        b=""
        j=i
        while (j):
            if j%n>9:
                b=big[j%n-10]+b
            else:
                b=str(j%n)+b
            j=j//n
        a=a+b
        i=i+1
    answer = a[p-1::m][:t] #나온 리스트에서 인원수 p의 차례 대답에 해당하는 인덱스부터
    #인원 수 단위로 자르고, 다시 구하고자 하는 숫자의 개수만큼 자른다.
    return answer