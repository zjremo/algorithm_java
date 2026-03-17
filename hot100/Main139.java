class Solution {
    // 单词拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        return backtrace(s, new StringBuilder(), wordDict);
    }

    // 方法一: 暴力递归，超时
    public boolean backtrace(String target, StringBuilder cur, List<String> wordDict){
        if (cur.length() >= target.length()){
            if (cur.length() > target.length())
                return false;
            return cur.toString().equals(target);
        }

        for (String s: wordDict){
            StringBuilder copy = new StringBuilder(cur.toString());
            copy.append(s);
            if (target.startsWith(copy.toString())){
                boolean res = backtrace(target, copy, wordDict);
                if (res)
                    return true;
            }
        }
        return false;
    }

    // 方法二: 动态规划
    // dp[i] = dp[j] && word
    public boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; ++i){
            boolean tmp = false;
            for (String w: wordDict){
                int len = w.length();
                if (len > i){
                    continue;
                }
                tmp = dp[i - len] && s.substring(i - len, i).equals(w);
                if (tmp)
                    break;
            }
            dp[i] = tmp;
        }
        return dp[n];
    }
}
