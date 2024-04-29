n,m = map(int,input().split())

dp = [list(map(int,input().split())) for _ in range(n)]

coin_list = []
for i in range(n):
    for j in range(m):
        if dp[i][j] == 1:
            coin_list.append((i,j))

coin_list.sort(key=lambda x:x[0])
coin_list.sort(key=lambda x:x[1])
def get_lis_improved(lis_list):
    L = [lis_list[0]]

    for i in range(1, len(lis_list)):
        if L[-1][0] <= lis_list[i][0]:
            if L[-1][1] <= lis_list[i][1]:
                L.append(lis_list[i])
    return L

count = 0
while coin_list:
    del_list = get_lis_improved(coin_list)
    coin_list = list(set(coin_list) - set(del_list))
    coin_list.sort(key=lambda x:x[0])
    coin_list.sort(key=lambda x:x[1])
    count+=1

print(count)