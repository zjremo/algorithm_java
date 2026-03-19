class Solution {
    // 下一个排列
    // 方法一: 纯暴力
    public void nextPermutation(int[] nums) {
        int[] nums_c = IntStream.of(nums).toArray();
        Arrays.sort(nums_c);
        // 回溯 找到所有的序列 然后按照字典序排列即可
        List<String> paths = new ArrayList<>();
        boolean[] used = new boolean[nums_c.length];

        backtrace(paths, nums_c, used, new StringBuilder());
        
        StringBuilder sb = new StringBuilder();
        for (int v: nums){
            sb.append(v);
        }
        String target = sb.toString();
        String res = "";
        for (int i = 0; i < paths.size(); ++i){
            if (target.equals(paths.get(i))){
                res = paths.get((i + 1) % paths.size());
                break;
            }
        }
        // 拷贝回去
        for (int i = 0; i < res.length(); ++i){
            nums[i] = Integer.parseInt("" + res.charAt(i));
        }
    }

    private Set<String> set = new HashSet<>();
    public void backtrace(List<String> path, int[] nums, boolean[] used, StringBuilder sb){
        if (sb.length() == nums.length){
            String str = sb.toString();
            if (!set.contains(str)){
                set.add(str);
                path.add(str);
            }
            return ;
        }

        for (int i = 0; i < nums.length; ++i){
            if (used[i])
                continue;
            used[i] = true;
            sb.append(nums[i] + "");
            backtrace(path, nums, used, sb);

            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
    }

    // 方法二: 两遍扫描
    // 从后向前找第一个顺序对，然后交换，最后将后面的部分逆序
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int k = n - 1;
        while (k > 0 && nums[k - 1] >= nums[k]){
            --k;
        }

        if (k == 0){
            // 说明全是降序
            reverse(nums, 0, n - 1);
            return ;
        }

        // 此时(k - 1, k)是从后向前的第一个升序对，然后找第一个比k - 1大的
        int r = n - 1;
        while (r > k && nums[r] <= nums[k - 1]){
            --r;
        }

        // (k - 1, r)
        swap(nums, k - 1, r);
        // 然后后半部分其实已经全是降序了，将它变为升序
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int l, int r){
        while (l <= r){
            swap(nums, l++, r--);
        }
    }

    public void swap(int[] nums, int l, int r){
        if (l == r)
            return ;
        nums[l] ^= nums[r];
        nums[r] ^= nums[l];
        nums[l] ^= nums[r];
    }

}
