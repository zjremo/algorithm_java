class Solution {
    public void swap(int[] nums, int i, int j){
        if (i == j)
            return;
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
