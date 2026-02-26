class Solution {
	/*
	* 盛最多水的容器: 容量 = Min(左高, 右高) * 宽度
	* 慢指针从0开始，快指针从最右侧开始。然后遵循以下思想: 每次都将最小高度的木板向内侧方向移动。因为我如果移动高度较高的木板，那么高度只可能变小，之间的距离也变小了，这个乘积是会减小的，所以要移动高度较低的木板。
	*/
    public int maxArea(int[] height) {
        int s = 0, f = height.length - 1;
        int max_v = 0;
        while (s != f){
            int v = f - s;
            if (height[s] < height[f]){
                v *= height[s];
                s++;
            }
            else{
                v *= height[f];
                f--;
            }

            max_v = Math.max(max_v, v);
        }
        return max_v;
    }
}
