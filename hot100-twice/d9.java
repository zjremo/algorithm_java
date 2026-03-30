class Solution {
    public int singleNumber(int[] nums) {
        int e = 0;

        for (int val : nums){
            e ^= val;
        }

        return e;
    }

    public int majorityElement(int[] nums) {
        int candidate = 0, score = 0;

        for (int val: nums){
            if (score == 0){
                candidate = val;
            }
            score = val == candidate ? ++score : --score;
        }

        return candidate;

    }

    public void sortColors(int[] nums) {
        partition(nums, 0, nums.length, 1);
    }

    public void partition(int[] nums, int l, int r, int target){
        int less = l - 1, more = r;
        while (l < more){
            if (nums[l] < target){
                swap(nums, ++less, l++);
            } else if (nums[l] > target){
                swap(nums, --more, l);
            } else{
                ++l;
            }
        }
    }

    public void swap(int[] nums, int l, int r){
        if (l == r)
            return ;
        nums[l] ^= nums[r];
        nums[r] ^= nums[l];
        nums[l] ^= nums[r];
    }

    public void nextPermutation(int[] nums) {
        int n = nums.length;

        int i = n - 2;
        while (i >= 0){ // 找到第一个顺序的
            if (nums[i] < nums[i + 1]){
                break;
            }
            --i;
        }

        if (i == -1){
            Arrays.sort(nums);
            return ;
        }

        int j = n - 1;
        while (j > i + 1){
            if (nums[i] < nums[j]){
                break;
            }
            --j;
        }

        swap(nums, i, j);
        reverse(nums, i + 1, n - 1);
    }

    public void reverse(int[] nums, int l, int r){
        while (l < r){
            swap(nums, l++, r--);
        }
    }

    public int findDuplicate(int[] nums) {
        // 转化为链表找有没有环
        int n = nums.length;

        int f = 0, s = 0;
        do {
            f = nums[nums[f]];
            s = nums[s];
        } while(f != s);
        
        f = 0;
        while (f != s){
            f = nums[f];
            s = nums[s];
        }

        return f;
    }

}
