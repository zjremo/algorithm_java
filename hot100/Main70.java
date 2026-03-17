class Solution {
    // 爬楼梯
    public int climbStairs(int n) {
        // dp[0] = 1, dp[1] = 1, dp[2] = dp[0](2步) + dp[1](1步)
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        for (int i = 2; i <= n; ++i){
            int val = list.get(i - 1) + list.get(i - 2);
            list.add(val);
        }

        return list.get(n);
    }
}
