class Solution {
    // 最长回文子串
    // dp[i][j] = dp[i + 1][j - 1] && s[i] == s[j]
    // 1. dp[i][i] = true
    // 2. dp[i][i + 1] = s[i] == s[i + 1]
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; ++i){
            dp[i][i] = true;
        }

        int max = 1;
        int begin = 0;

        for (int i = 0; i + 1 < n; ++i){
            if (s.charAt(i) == s.charAt(i + 1)){
                dp[i][i + 1] = true;
                max = 2;
                begin = i;
            }
        }

        for (int len = 3; len <= n; ++len){
            for (int i = 0; i <= n - len; ++i){
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]){
                    dp[i][j] = true;

                    if (len > max){
                        max = len;
                        begin = i;
                    }
                }
            }
        }

        return s.substring(begin, begin + max);
    }
}
