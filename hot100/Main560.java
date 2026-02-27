class Solution {
    // 和为k的子数组
    // 方法一: 暴力破解
    public int subarraySum(int[] nums, int k) {
        int l = 0, n = nums.length;

        int cnt = 0;

        for (; l < n ; ++l){
            int sum = 0, tmp = l;
            while (tmp >= 0){
                sum += nums[tmp];
                if (sum == k)
                    ++cnt;
                --tmp;
            }
        }
        return cnt;

    }

    // 方法二: 前缀和 + 哈希表
    public int subarraySum1(int[] nums, int k) {
        int cnt = 0, pre = 0, n = nums.length;

        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);

        // pre[i] = pre[j] + k 两数之和
        // pre[j] = pre[i] - k = target 哈希表中寻找target
        for (int i = 0; i < n; i++){
            pre += nums[i];
            if (mp.containsKey(pre - k))
                cnt += mp.get(pre - k);
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return cnt;

    }
}
