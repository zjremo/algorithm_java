class Solution {
	/*
	* 无重复字符的最长子串
	*/

	// 利用滑动窗口，每次保证窗口内部的都是不重复的字符即可
	// 比如左边界以索引为0的元素为起始，右指针一直移动到再也不能保证不重复字符为止。此时这个窗口内部的都是不重复字符，然后移动左指针，只要将它踢掉即可
    public int lengthOfLongestSubstring(String s) {
		int resLen = 0;
		
		// 滑动窗口，不断向前滑动
		int r = 0, n = s.length();

		// 使用哈希集合来判断每个字符是否出现过
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < n; ++i){
			if (i != 0){
				// 左指针每次向右移动一格相当于移除一个字符
				set.remove(s.charAt(i - 1));
			}
			while (r < n && !set.contains(s.charAt(r))){
				set.add(s.charAt(r));
				++r;
			}
			// 此时i -> r - 1中全是不重复的字符
			resLen = Math.max(resLen, r - i);
		}
		return resLen;

    }
}
