class Solution {
    private List<List<Integer>> res;
    // 子集问题
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        backTracking(nums, 0, new ArrayList<>());
        return res;
    }


    public void backTracking(int[] nums, int index, List<Integer> list){
        res.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; ++i){
            list.add(nums[i]);
            backTracking(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
