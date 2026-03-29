class Solution{
    public int uniquePaths(int m, int n) {
        // dp[i][j] = dp[i - 1][j] || dp[i][j - 1] 
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; ++i){
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; ++i){
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; ++i){
            for (int j = 1; j < n; ++j){
                int c1 = isTrue(i - 1, j, m, n) ? dp[i - 1][j]:0;
                int c2 = isTrue(i, j - 1, m, n) ? dp[i][j - 1]:0;
                dp[i][j] = c1 + c2;
            }
        }
        return dp[m - 1][n - 1];
    }

    public boolean isTrue(int r, int c, int m, int n){
        return r >= 0 && r < m && c >= 0 && c < n;
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] dp = new int[m][n];
        
        int cur = 0;
        for (int i = 0; i < m; ++i){
            cur += grid[i][0];
            dp[i][0] = cur;
        }

        cur = 0;
        for (int i = 0; i < n; ++i){
            cur += grid[0][i];
            dp[0][i] = cur;
        }

        for (int i = 1; i < m; ++i){
            for (int j = 1; j < n; ++j){
                int c1 = isTrue(i - 1, j, m, n)?dp[i - 1][j]:Integer.MAX_VALUE;
                int c2 = isTrue(i, j - 1, m, n)?dp[i][j - 1]:Integer.MAX_VALUE;

                dp[i][j] = Math.min(c1, c2) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    public String longestPalindrome(String s) {
        // dp[i][j] = dp[i + 1][j - 1] && (s[i] == s[j])
        // 1. 极端条件：i = j时，直接回文串 
        // 2. j = i + 1时，直接退化为 s[i] == s[j]
        // 3. 一般性

        int n = s.length();
        int res = 1;
        String resStr = s.charAt(0) + "";
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n - 1; ++i){
            dp[i][i] = true;
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            if (dp[i][i + 1]){
                res = 2;
                resStr = s.substring(i, i + 2);
            }
        }

        dp[n - 1][n - 1] = true; 


        // 找长度为3的回文串，-> n - 1的回文串
        for (int len = 3; len <= n; ++len){
            for (int i = 0; i + len - 1 < n; ++i){
                dp[i][i + len - 1] = dp[i + 1][i + len - 2] && (s.charAt(i) == s.charAt(i + len - 1));
                if (dp[i][i + len - 1]){
                    res = len;
                    resStr = s.substring(i, i + len);
                }
            }
        }

        return resStr;
    }

    // abcde ace
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();

        int[][] dp = new int[m][n];
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for (int i = 0; i < m; ++i){
            dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1: dp[i - 1][0];
        }

        for (int i = 0; i < n; ++i){
            dp[0][i] = text1.charAt(0) == text2.charAt(i)?1: dp[0][i - 1];
        }

        for (int i = 1; i < m; ++i){
            char a = text1.charAt(i);
            for (int j = 1; j < n; ++j){
                char b = text2.charAt(j);
                if (a == b){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int minDistance(String word1, String word2) {
        // 平时只有增删操作，两者相等的时候可以考虑更新操作
        int m = word1.length(), n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; ++i){
            dp[i][0] = i;
        }

        for (int i = 0; i <= n; ++i){
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; ++i){
            for (int j = 1; j <= n; ++j){
                int insert = dp[i - 1][j] + 1;
                int delete = dp[i][j - 1] + 1;
                char c1 = word1.charAt(i), c2 = word2.charAt(j);
                int update = c1 == c2 ? dp[i - 1][j - 1]:dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(insert, Math.min(delete, update));
            }
        }
        return dp[m][n];
    }
}
