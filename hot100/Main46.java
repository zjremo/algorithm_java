class Solution {
    // 全排列
    public List<List<Integer>> permute(int[] nums) {
        boolean[] flags = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, flags, new ArrayList<>());
        return res;
    }

    public void dfs(int[] nums, List<List<Integer>> res, boolean[] flags, List<Integer> temp){
        if (temp.size() == nums.length){
            // 此时代表已经组装完毕
            res.add(new ArrayList<>(temp));
            return ;
        }

        for (int i = 0; i < nums.length; ++i){
            if (!flags[i]){
                flags[i] = true;
                temp.add(nums[i]);
                dfs(nums, res, flags, temp);
                // 回溯
                flags[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }
}
