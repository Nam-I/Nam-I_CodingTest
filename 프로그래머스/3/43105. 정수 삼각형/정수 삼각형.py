def solution(triangle):
    
    arr_l = len(triangle)
    
    for i in range(1, arr_l):
        for j in range(len(triangle[i])):
            if(j == 0): #가장 왼쪽 인덱스
                triangle[i][j] += triangle[i-1][j]
            elif(j == len(triangle[i]) - 1): #가장 오른쪽 인덱스
                triangle[i][j] += triangle[i-1][j-1]
            else:
                triangle[i][j] = max(triangle[i][j] + triangle[i-1][j-1], triangle[i][j] + triangle[i-1][j])
    return max(triangle[arr_l-1])