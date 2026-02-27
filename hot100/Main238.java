class Solution {
    // 除了自身以外数组的乘积
    // 方法一: 最后的结果存放数组当有结果再存放
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        
        int[] l = new int[n];
        int[] r = new int[n];
        l[0] = r[n - 1] = 1;

        for (int i = 1; i < n; i++)
            l[i] = nums[i - 1] * l[i - 1];

        for (int i = n - 2; i >= 0; i--)
            r[i] = nums[i + 1] * r[i + 1];
        

        int t = 0;
        int[] ans = new int[n];
        while (t < n){
            ans[t] = l[t] * r[t];
            ++t;
        }
        return ans;
    }

    // 方法二: 左列表先就直接放到结果数组中，然后右列表用变量替代，从右向左乘
    public int[] productExceptSelf1(int[] nums){
        int n = nums.length;

        int[] ans = new int[n];
        ans[0] = 1;

        for (int i = 1; i < n; i++)
            ans[i] = ans[i - 1] * nums[i - 1];

        int r = 1, t = n - 1;
        while (t >= 0){
            ans[t] *= r;
            r *= nums[t];
            t -- ;
        }
        return ans;
    }
}
