class Solution {
    // 跳跃游戏
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0; // 最远可以到达的距离
        for (int i = 0; i < n; ++i){
            if (i <= rightmost){
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1){
                    return true;
                }
            }
        }
        return false;
    }
}
