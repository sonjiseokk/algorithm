n = int(input())
nums = list(map(int,input().split()))

sorted_nums = sorted(nums)

cnt_dic = dict()
for i in range(n):
    if sorted_nums[i] not in cnt_dic:
        cnt_dic[sorted_nums[i]] = len(cnt_dic)
for num in nums:
    print(cnt_dic.get(num),end=" ")
