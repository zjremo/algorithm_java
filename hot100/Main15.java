class Solution{
	/*
	* 三数之和
	*/
	public List<List<Integer>> threeSum(int[] nums){
		Arrays.sort(nums);

		List<List<Integer>> res = new ArrayList<>();

		for (int i = 0; i < nums.length; i++){
			// 跳过重复元素
			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			// 双指针 
			int l = i + 1, r = nums.length - 1;
			int target = -nums[i];

			while (l < r){
				int sum = nums[l] + nums[r];
				if (sum == target){
					res.add(Arrays.asList(nums[i], nums[l], nums[r]));
					l++;
					r--;
					// 两边都要遍历到下一个不一样的元素为止
					for (;nums[l] == nums[l-1] && l < r;l++);
					for (;nums[r] == nums[r+1] && r > l;r--);
				} else if (sum < target){
					l++;
				} else {
					r--;
				}

			}
		}
		return res;
	}


}
