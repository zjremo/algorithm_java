class Solution {
    // 寻找旋转排序数组中的最小值
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    // 3 1 2
    public int findMin(int[] nums, int l, int r){
        if (l > r){
            return Integer.MAX_VALUE;
        }

        // l <= r
        int mid = ((r - l) >> 1) + l;
        int sortMin, unSortMin;
        // 找有序数组
        if (nums[0] <= nums[mid]){
            sortMin = nums[l];
            unSortMin = findMin(nums, mid + 1, r);
        } else{ // 此时另一边有序
            sortMin = nums[mid];
            unSortMin = findMin(nums, l, mid - 1);
        }

        return Math.min(sortMin, unSortMin);

    }
}
