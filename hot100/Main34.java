class Solution {
    // 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0){
            return new int[]{
                -1, -1
            };
        }
        int idx = searchInsert(nums, target);
        if (target != nums[idx]){
            return new int[]{-1, -1};
        }
        int tmp = idx;
        for (;tmp < nums.length - 1 && nums[tmp + 1] == target; ++tmp);
        return new int[]{idx, tmp};
        
    }

    public int searchInsert(int[] nums, int target){
        int l = 0, r = nums.length - 1, ans = 0;
        while (l <= r){
            int mid = l + ((r - l)>>1);
            if (target <= nums[mid]){
                ans = mid;
                r = mid - 1;
            } else{
                l = mid + 1;
            }
        }
        return ans;
    }
}
