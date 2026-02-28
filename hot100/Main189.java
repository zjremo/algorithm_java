class Solution {
    /*
     * 轮转数组
     */
    public void rotate(int[] nums, int k) {
        // 方法一: 使用额外的数组直接放
        int n = nums.length;
        int[] c_arr = new int[n];
        for (int i = 0; i < n; ++i){
            c_arr[(i + k) % n] = nums[i];
        }
        System.arraycopy(c_arr, 0, nums, 0, n);
    }

    // 方法二: 环状替换
    public void rotate1(int[] nums, int k) {
        // an = kb = lcm(n, k) -> b = lcm(n, k)/k 一次遍历b个元素
        // 一共需要多少次遍历呢? -> n/b = nk/lcm(n,k) = gcd(n, k)
        int l = 0, n = nums.length;
        k %= n;
        int cnt = gcd(k, n);
        while (l < cnt){
            int idx = l , lastv = nums[l];
            while (true){
                idx = (idx + k) % n;
                int tmp = nums[idx];
                nums[idx] = lastv;
                lastv = tmp;
                if (idx == l)
                    break;
            }
            ++l;
        }
    }

    public int gcd(int x, int y){
        // 求解最大公因数
        return y > 0 ? gcd(y, x % y) : x;
    }

    // 方法三: 数组翻转
    public void rotate2(int[] nums, int k){
        // 1 2 3 4 5 6 7
        // 7 6 5 4 3 2 1
        // 5 6 7 1 2 3 4
        int n = nums.length;
        k %= n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int l, int r){
        while (l < r){
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            ++l;
            --r;
        }
    }
    
}
