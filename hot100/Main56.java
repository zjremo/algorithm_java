class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0)
            return new int[0][2];

        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] interval1, int[] interval2){
                return interval1[0] - interval2[0];
            }
        });

        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i){
            int l = intervals[i][0], r = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < l)
                merged.add(new int[]{l, r});
            else
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size()  -1)[1], r);
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
