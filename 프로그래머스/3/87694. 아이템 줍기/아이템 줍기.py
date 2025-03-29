from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    answer = 0
    limit = 102
    field = [[5]*limit for _ in range(limit)]
    
    for r in rectangle:
        x1, y1, x2, y2 = map(lambda x: x*2, r)
        for i in range(x1, x2+1):
            for j in range(y1, y2+1):
                if x1 < i < x2 and y1 < j < y2:
                    field[i][j] = 0
                elif field[i][j] != 0:
                    field[i][j] = 1
    q = deque()
    q.append([characterX*2, characterY*2])
    visited = [[0]*limit for _ in range(limit)]
    visited[characterX*2][characterY*2] = 1
    dx = [0, 0, -1, 1]  #상하좌우 이동 값 설정
    dy = [1, -1, 0, 0]
    
    while q:
        x, y = q.popleft()
        if x == itemX*2 and y == itemY*2:   #아이템이 있는 곳에 도착한 경우 결과 반환
            answer = (visited[x][y] - 1) // 2   #아마 시작 지점 부터 1을 해줘서 빼주는 듯?
            break
        #아이템이 있는 지점에 도착하지 않은 경우
        for k in range(4):
            nx = x + dx[k]
            ny = y + dy[k]
            
            if visited[nx][ny] == 0 and field[nx][ny] == 1:
                q.append([nx, ny])
                visited[nx][ny] = visited[x][y] + 1
        
    return answer