class Solution {
    // 乘积最大子数组
    // 维护两个dp，一个最大，一个最小，因为里面可能存在负数情况
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] mindp = new int[n + 1];
        int[] maxdp = new int[n + 1];

        mindp[0] = 1;
        maxdp[0] = 1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i){
            if (nums[i] >= 0){
                mindp[i + 1] = Math.min(mindp[i] * nums[i], nums[i]);
                maxdp[i + 1] = Math.max(maxdp[i] * nums[i], nums[i]);
            } else{
                mindp[i + 1] = Math.min(maxdp[i] * nums[i], nums[i]);
                maxdp[i + 1] = Math.max(mindp[i] * nums[i], nums[i]);
            }
            ans = Math.max(ans, maxdp[i + 1]);
        }
        return ans;
    }

    // 方法二: 两个变量维护，因为只与前一状态有关 
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int maxdp = 1, mindp = 1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i){
            if (nums[i] >= 0){
                mindp = Math.min(mindp * nums[i], nums[i]);
                maxdp = Math.max(maxdp * nums[i], nums[i]);
            } else{
                int mintmp = mindp;
                mindp = Math.min(maxdp * nums[i], nums[i]);
                maxdp = Math.max(mintmp * nums[i], nums[i]);
            }
            ans = Math.max(ans, maxdp);
        }
        return ans;
    }
}
