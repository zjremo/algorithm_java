class Solution {
    // 最小路径和
    // dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1)
            return grid[0][0];
        
        if (m == 1)
            return IntStream.of(grid[0]).sum();

        if (n == 1){
            return IntStream.range(0, m).map(v -> grid[v][0]).sum();
        } 

        int[][] dp = new int[m][n]; // 1开始
        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; ++i){
            dp[i][0] = dp[i - 1][0] + grid[i][0]; 
        }

        for (int i = 1; i < n; ++i){
            dp[0][i] = dp[0][i - 1] + grid[0][i]; 
        }

        for (int i = 1; i < m; ++i){
            for (int j = 1; j < n; ++j){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
