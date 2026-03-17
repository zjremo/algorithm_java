class Solution {
    // 完全平方数
    public int numSquares(int n) {
        int[] dp = new int[n + 1]; // 0 -> n
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
}
