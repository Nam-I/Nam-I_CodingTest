n = int(input())

line = 0
end = 0

while n > end:
    line += 1
    end += line
    
diff = end - n #끝점과 n 인덱스 차

if line % 2 == 0:    #짝수일 때 끝점으로 갈수록 분자 커짐(분모는 반대)
    top = line - diff    #끝점 분자 값은 최대값 이때 n의 분자 값은 차이 만큼 작을 것임.
    bottom = 1 + diff    #끝점 분모 값은 최솟값 이때 n의 분모 값은 차이 만큼 클 것임.

else:    #홀수일 때 끝점으로 갈수록 분모 커짐(분자는 반대)
    top = 1 + diff
    bottom = line - diff
  
print(f"{top}/{bottom}")