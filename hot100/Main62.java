class Solution {
    // 不同路径
    // dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
    public int uniquePaths(int m, int n) {
        // 到达(i, j)的路径数
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (i == 0 && j == 0)
                    continue;
                int t1 = 0, t2 = 0;
                if (i - 1 >= 0){
                    t1 = dp[i - 1][j];
                }
                if (j - 1 >= 0){
                    t2 = dp[i][j - 1];
                }
                dp[i][j] = t1 + t2;
            }
        }
        return dp[m - 1][n - 1];

    }
}
