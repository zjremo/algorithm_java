class Solution {
    // 零钱兑换
    // dp[i] = 1 + dp[i - c] c 为面值
    // 方法一: 自己实现版本，比较复杂
    public int coinChange(int[] coins, int amount) {
        // 过滤掉超过amount的币
        List<Integer> fCoins = IntStream.of(coins).filter(v -> v <= amount).boxed().collect(Collectors.toCollection(ArrayList::new));
        if (fCoins.size() == 0){
            if (amount == 0)
                return 0; // amount为0, 不存在凑不凑的了
            else
                return -1; // amount为1, 就是凑不出来金额
        }

        int[] dp = new int[amount + 1];
        // 预填充
        dp[0] = 0;
        
        for (int i = 1; i <= amount; ++i){
            int min = Integer.MAX_VALUE;
            for(int coin : fCoins){
                if (i - coin < 0 || dp[i - coin] == -1){
                    continue;
                }
                min = Math.min(min, dp[i - coin]);
            }
            if (min == Integer.MAX_VALUE)
                dp[i] = -1;
            else
                dp[i] = 1 + min;
        }
        return dp[amount];
    }

    // 方法二: 记忆化搜索方法，向下递归并存储结果
    public int coinChange1(int[] coins, int amount){
        if (amount < 1)
            return 0;
        return dfs(coins, amount, new int[amount]); 
    }

    public int dfs(int[] coins, int cur, int[] count){
        // 终止条件
        if (cur < 0){
            return -1;
        }

        if (cur == 0)
            return 0;

        if (count[cur - 1] != 0) // 此时代表已经找到
            return count[cur - 1];

        int min = Integer.MAX_VALUE;
        for (int coin : coins){
            // 每次都去试一个硬币
            int res = dfs(coins, cur - coin, count);
            if (res >= 0 && res < min){
                min = 1 + res;
            }
        }

        count[cur - 1] = (min == Integer.MAX_VALUE)? -1 : min;
        return count[cur - 1];
    }

    // 方法三: 动态规划
    public int coinChange3(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        int[] dp = new int[amount + 1]; // 0 -> amount
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i <= amount; ++i){
            int min = Integer.MAX_VALUE;
            for (int coin: coins){
                if (coin <= i && dp[i - coin] != -1){
                    min = Math.min(min, dp[i - coin]);
                }
                dp[i] = min == Integer.MAX_VALUE ? -1 : min + 1;
            }
        }

        return dp[amount];
    }

}
