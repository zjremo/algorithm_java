class Solution {
    // 搜索旋转排序数组 4 5 6 7 0 1 2
    public int search(int[] nums, int target) {
        // 特殊情况判断
        int n = nums.length;
        if (n == 0)
            return -1;
        if (n == 1){
            return nums[0] == target? 0 : -1;
        }

        // 二分查找
        int l = 0, r = n - 1;
        while (l <= r){
            int mid = ((r - l) >> 1) + l; 
            if (nums[mid] == target){
                return mid;
            }

            // 找有序数组
            if (nums[0] <= nums[mid]){
                if (nums[0] <= target && target < nums[mid]){
                    r = mid - 1;
                } else{
                    l = mid + 1;
                }
            } else{ // 此时右边必定是有序的
                if (nums[mid] < target && target <= nums[n - 1]){
                    l = mid + 1;
                } else{
                    r = mid - 1;
                }
            }
        }
        return -1;

    }
}
