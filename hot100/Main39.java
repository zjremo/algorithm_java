class Solution {
    // 组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtrack(ans, temp, candidates, target, 0, 0);
        return ans;
    }

    public void backtrack(List<List<Integer>> ans, List<Integer> temp, int[] candidates, int target, int index, int sum){
        if (sum >= target){
            if (sum == target)
                ans.add(new ArrayList<>(temp));
            return ;
        }

        for (int i = index; i < candidates.length; ++i){
            temp.add(candidates[i]);
            backtrack(ans, temp, candidates, target, i, sum + candidates[i]);
            temp.remove(temp.size() - 1);
        }
        
    }
}
