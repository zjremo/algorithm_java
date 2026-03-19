class Solution {
    // 只出现一次的数字
    public int singleNumber(int[] nums) {
        int e = 0;
        for (int n: nums){
            e ^= n;
        }
        return e;
    }
}
