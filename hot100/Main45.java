class Solution {
    // 跳跃游戏2
    // 方法一：反向查找出发位置
    public int jump(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; ++i){
                if (i + nums[i] >= position){
                    ++steps;
                    position = i;
                    break;
                }
            }
        }
        return steps;
    }

    // 方法二: 正向查找可到达的最大位置
    // 每次更新覆盖范围的时候，需要加上一次跳跃
    public int jump2(int[] nums) {
        int n = nums.length, maxPosition = 0, steps = 0, end = 0;
        for (int i = 0; i < n - 1; ++i){
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end){ // 到达覆盖范围之后再更新覆盖范围
                end = maxPosition;
                ++steps; // 更新覆盖范围的时候需要加一次跳跃
            }
        }
        return steps;
    }
}
