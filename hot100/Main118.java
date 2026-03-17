class Solution {
    // 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        // 两边都是1,然后中间由上面部分得到
        // 手动添加前两行
        if (numRows == 0)
            return list;
        if (numRows == 1){
            list.add(new ArrayList<>(Arrays.asList(1))); 
            return list;
        }
        list.add(new ArrayList<>(Arrays.asList(1))); 
        list.add(new ArrayList<>(Arrays.asList(1, 1)));
        for (int i = 2; i < numRows; ++i){
            List<Integer> lastRow = list.get(i - 1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 0; j + 1 < i; ++j){
                row.add(lastRow.get(j) + lastRow.get(j + 1));
            }
            row.add(1);
            list.add(row);
        }
        return list;
    }
}
