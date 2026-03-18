class Solution {
    // 最长公共子序列
    // dp[i][j] = dp[i - 1][j - 1] + 1   
    // dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1]; 

        for (int i = 0; i <= m; ++i){
            dp[i][0] = 0;
        }

        for (int i = 0; i <= n; ++i){
            dp[0][i] = 0;
        }

        for (int i = 1; i <= m; ++i){
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; ++j){
                char c2 = text2.charAt(j - 1);
                if (c1 == c2){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
