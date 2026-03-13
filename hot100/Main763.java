class Solution {
    private Map<Character, Integer> map = new HashMap<>();
    // 划分字母区间
    // 首先遍历一遍，获取每个字母的最大索引位置
    // 然后双指针，右指针不断更新最大索引，直到到达相同索引处，此时表示找到了一个区间
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        for (int i = 0; i < n; ++i){
            Character c = s.charAt(i);
            int tmp = map.getOrDefault(c, 0);
            tmp = Math.max(tmp, i);
            map.put(c, tmp);
        }

        int left = 0, right = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; ++i){
            right = Math.max(right, map.get(s.charAt(i)));
            if (i == right){
                // 开始切分区间
                list.add(right - left + 1);
                left = right + 1;
            }
        }
        return list;
    }
}
