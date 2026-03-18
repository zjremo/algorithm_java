class Solution {
    // 分割等和子集
    // 方法一: 暴力递归 超时
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return false;

        // 排序 从小到大
        Arrays.sort(nums);

        int sum = IntStream.of(nums).sum();
        if (sum % 2 != 0)
            return false;

        int target = sum / 2;
        // 此时转为找元素之和为target的元素
        return backtrace(nums, 0, target, 0, 0);

    }

    public boolean backtrace(int[] nums, int cur, int target, int index, int cnt){
        int n = nums.length;
        // 终止条件: 位置遍历完
        if (index == n){
            if (cur == target && cnt < n)
                return true;
            return false;
        }

        for (int i = index; i < n; ++i){
            cur += nums[i];
            boolean res = backtrace(nums, cur, target, i + 1, cnt + 1);
            if (res)
                return true;
            cur -= nums[i];
        }
        return false;
    }

    // 方法二: 动态规划
    public boolean canPartition2(int[] nums) {
        int n = nums.length;
        if (n < 2){
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num: nums){
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0){
            return false;
        }
        int target = sum / 2;
        if (maxNum > target){
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; ++i){
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; ++i){
            int num = nums[i];
            for (int j = 1; j <= target; ++j){
                if (j < num){
                    dp[i][j] = dp[i - 1][j];
                } else{
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
                }
            }
        }
        return dp[n - 1][target];
    }
}
