def solution(n):
    dp = [0] * (n+1)
    
    #피보나치 수열 응용
    dp[0] = 1
    dp[1] = 2
    

    for i in range(2,n):
        dp[i] = dp[i-2] + dp[i-1]
	
   # 정답은 실제 개수를 1234567로 나눈 나머지 값이므로
    return dp[n-1] % 1234567

