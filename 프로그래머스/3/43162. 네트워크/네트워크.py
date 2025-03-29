def dfs(n, visited, com, computers):
    visited[com] = True
    for connect in range(n):
        if com != connect and computers[com][connect] == 1:
            if visited[connect] == False:
                dfs(n, visited, connect, computers)

def solution(n, computers):
    answer = 0
    visited = [False for i in range(n)]
    for com in range(n):
        if visited[com] == False:
            dfs(n, visited, com, computers)
            answer += 1
            
    return answer