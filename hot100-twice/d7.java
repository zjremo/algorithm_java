class Solution{
    public List<Integer> partitionLabels(String s) {
        // 记录每个字符最后一次出现的索引
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();

        for (int i = 0; i < n; ++i){
            map.put(s.charAt(i), i);
        }

        // 开始划分
        // 记录当前最大的索引，然后最大的和当前索引重合就可以划分
        List<Integer> list = new ArrayList<>();
        int maxId = -1, prevSplitIdx = 0;
        for (int i = 0; i < n; ++i){
            int curLastId = map.get(s.charAt(i));
            maxId = Math.max(curLastId, maxId);

            if (maxId == i){
                list.add(i - prevSplitIdx + 1);
                prevSplitIdx = i + 1;
            }
        }

        // 还可能剩下一段
        if (prevSplitIdx != n){
            list.add(n - 1 - prevSplitIdx + 1);
        }
        return list;
    }

    public int climbStairs(int n) {
        if (n == 1){
            return 1;
        }

        if (n == 2){
            return 2;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public List<List<Integer>> generate(int numRows) {
        if (numRows == 1){
            return new ArrayList<>(){
            {
                    add(Arrays.asList(1));
                }
            };
        }else if (numRows == 2){
            return new ArrayList<>(){
            {
                    add(Arrays.asList(1));
                    add(Arrays.asList(1, 1));
                }
            };
        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(1));
        res.add(Arrays.asList(1, 1));
        

        for (int i = 2; i < numRows; ++i){
            List<Integer> prevList = res.get(i - 1);

            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 0; j + 1 < i; ++j){
                list.add(prevList.get(j) + prevList.get(j + 1));
            }
            list.add(1);
            res.add(list);
        }
        return res;
    }

    public int rob(int[] nums) {
        // 不能偷相邻的
        // dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        
        int n = nums.length;
        if (n == 1){
            return nums[0];
        } else if (n == 2){
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < n; ++i){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; ++i){
            int tmp_min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j){
                tmp_min = Math.min(tmp_min, dp[i - j * j]);
            }
            dp[i] = tmp_min + 1;
        }
        return dp[n];
    }

    public int coinChange(int[] coins, int amount) {
        // min(dp[i -1], dp[i - 2], dp[i - 5]) + 1
        
        // 只能使用比amount面值小的硬币
        int[] candidates = IntStream.of(coins).filter(v -> v <= amount).toArray(); 
        if (candidates.length == 0){
            return amount == 0 ? 0 : -1;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; ++i){
            int tmp = Integer.MAX_VALUE;
            for (int coin : candidates){
                // 必须要是能够凑出来的
                if (i - coin >= 0 && dp[i - coin] != -1){
                    tmp = Math.min(tmp, dp[i - coin]);
                }
            }
            if (tmp != Integer.MAX_VALUE){
                dp[i] = tmp + 1;
            } else{
                dp[i] = -1; // 凑不出来
            }
        }
        return dp[amount];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        // 不成功用false表示
        // dp[i] = dp[i - len(c1)] || ... || dp[i - len(c2)] 0 -> i 这个字符串是否可以拼接
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = false;
        for (int i = 0; i < n; ++i){
            for (String candidate : wordDict){
                int useLength = i + 1 - candidate.length();
                if (useLength >= 0){
                    // 检查这个条件
                    if (s.substring(useLength, i + 1).equals(candidate)){
                        dp[i] = useLength == 0 ? true : (true && dp[useLength - 1]);
                        if (dp[i])
                            break;
                    }
                }
            }
        }
        return dp[n - 1];
    }
}
