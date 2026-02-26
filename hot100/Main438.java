class Solution {
	// 方法一: 维护每个字符出现的次数 比较两个字符串是否一样直接利用数组比较
	public List<Integer> findAnagrams(String s, String p) {
		int s_len = s.length(), p_len = p.length();

		if (s_len < p_len)
			return new ArrayList<Integer>();

		List<Integer> res = new ArrayList<>();
		int[] s_cnt = new int[26];
		int[] p_cnt = new int[26];

		for (int i = 0; i < p_len; ++i){
			++s_cnt[s.charAt(i) - 'a'];
			++p_cnt[p.charAt(i) - 'a'];
		}

		if (Arrays.equals(s_cnt, p_cnt))
			res.add(0);

		int l = 0;
		while (l < s_len - p_len){
			--s_cnt[s.charAt(l) - 'a'];
			++s_cnt[s.charAt(l + p_len) - 'a'];

			if (Arrays.equals(s_cnt, p_cnt))
				res.add(l + 1);
			++l;
		}
		return res;

    }

	// 方法二: 维护两个字符串之间的字符数量差 不再采用数组比较来进行字符串判断
	public List<Integer> findAnagrams1(String s, String p){
		int s_len = s.length(), p_len = p.length();

		if (s_len < p_len)
			return new ArrayList<>();
		
		List<Integer> res = new ArrayList<>();
		int[] diff_cnt = new int[26];

		for (int i = 0; i < p_len; i++){
			++diff_cnt[s.charAt(i) - 'a'];
			--diff_cnt[p.charAt(i) - 'a'];
		}

		long differ = Arrays.stream(diff_cnt).filter(v -> v!=0).count();

		if (differ == 0)
			res.add(0);

		int l = 0;
		
		while (l < s_len - p_len){
			if (diff_cnt[s.charAt(l) - 'a'] == 1)
				--differ;
			else if (diff_cnt[s.charAt(l) - 'a'] == 0)
				++differ;

			--counts[s.charAt(l) - 'a'];

			if (diff_cnt[s.charAt(l + p_len) - 'a'] == -1)
				--differ;
			else if (diff_cnt[s.charAt(l + p_len) - 'a'] == 0)
				++differ;

			++counts[s.charAt(l + p_len) - 'a'];

			if (differ == 0){
				res.add(i + 1);
			}

		}
		return res;

	}
}
