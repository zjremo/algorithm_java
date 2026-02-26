class Solution {
	/*
	* 字母异位词分组，还是利用hash表的思想
	*/
    public String sortStr(String str){
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
	// 方法一: hash表构建键的时候利用排序好后的字符串来作为键
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs){
            String keyStr = sortStr(str);
            if (map.containsKey(keyStr)){
                map.get(keyStr).add(str);
            }
            else{
                List<String> valueList = new ArrayList<>();
                valueList.add(str);
                map.put(keyStr, valueList);
            }
        }

        return new ArrayList<>(map.values());
    }

	// 方法二: hash表构建键的时候利用统计字符个数来作为键
	public String getKeyStr(String str){
        int[] cntAlpha = new int[26];
        Arrays.fill(cntAlpha, 0);

        for (char a : str.toCharArray()){
            cntAlpha[a - 'a'] += 1;
        }
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, 26).forEach(i -> sb.append("" + ('a' + i) + cntAlpha[i]));
        return sb.toString();
    }
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs){
            String keyStr = getKeyStr(str);

            List<String> valueList = map.getOrDefault(keyStr, new ArrayList<>());
            valueList.add(str);
            map.put(keyStr, valueList);

        }
        return new ArrayList<>(map.values());
    }
}
