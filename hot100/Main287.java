class Solution {
    // 寻找重复数
    // 方法一: 只有重复的两个数异或才为0 超时
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i){
            for (int j = i + 1; j < n; ++j){
                if ((nums[i] ^ nums[j]) == 0){
                    return nums[i];
                }
            }
        }
        return 0;
    }

    // 方法二:快慢指针 
    public int findDuplicate2(int[] nums) {
        int s = 0, f = 0;
        while (true) {
            s = nums[s];
            f = nums[nums[f]];
            if (s == f)
                break;
        }

        s = 0;
        while (s != f) {
            s = nums[s];
            f = nums[f];
        }
        return s;
    }
}
