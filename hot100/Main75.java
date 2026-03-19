class Solution {
    // 颜色分类
    public void sortColors(int[] nums) {
        int n = nums.length, target = 1;
        int less = -1, more = n, l = 0;
        while (l < more) {
            if (nums[l] < target){
                swap(nums, ++less, l++);
            } else if (nums[l] > target){
                swap(nums, --more, l);
            } else{
                l++;
            }
        }
    }

    public void swap(int[] nums, int l, int r){
        if (l == r)
            return;
        nums[l] ^= nums[r];
        nums[r] ^= nums[l];
        nums[l] ^= nums[r];
    }
}
