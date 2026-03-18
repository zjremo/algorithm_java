class Solution {
    // 编辑距离
    // 插入、删除和替换 三步
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        // DP数组
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n + 1; ++i){
            dp[i][0] = i;
        }

        for (int j = 0; j < m + 1; ++j){
            dp[0][j] = j;
        }

        // 计算所有dp值
        for (int i = 1; i < n + 1; ++i){
            for (int j = 1; j < m + 1; ++j){
                int insert = dp[i - 1][j] + 1;
                int delete = dp[i][j - 1] + 1;
                char c1 = word1.charAt(i - 1), c2 = word2.charAt(j - 1);
                int update = c1 == c2? dp[i - 1][j - 1]:dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(insert, Math.min(delete, update));
            }
        }
        return dp[n][m];
    }
}
