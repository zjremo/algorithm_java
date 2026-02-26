class Solution {
	/*
	* 两数之和问题，思路便是使用哈希表。对数组从左向右遍历一遍即可。
	*/
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i]))
                return new int[]{i, hashtable.get(target - nums[i])};
            hashtable.put(nums[i], i);
        }
        return null;

    }
}
