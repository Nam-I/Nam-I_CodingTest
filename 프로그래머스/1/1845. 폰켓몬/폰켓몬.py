def solution(nums):
    mon = {}
    for i in nums:
        if i not in mon:
            mon[i] = 1
        else:
            mon[i] += 1

    return len(mon) if len(nums)//2 >= len(mon) else len(nums)//2

