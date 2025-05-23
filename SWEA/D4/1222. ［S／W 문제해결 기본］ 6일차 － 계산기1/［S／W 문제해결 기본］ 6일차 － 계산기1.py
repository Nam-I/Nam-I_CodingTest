for t in range(10) :
    n = int(input())
    nums = list(map(int, input().split('+')))

    print(f"#{t+1} {sum(nums)}")