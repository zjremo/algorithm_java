class Solution {
    // 上下左右
    private int[][] directions = new int[][]{
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public void initUsed(boolean[][] used, int m){
        int i = 0;
        while (i < m) {
            Arrays.fill(used[i], false);
            ++i;
        }
    }
    // 单词搜索
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        // boolean数组 代表是否完成遍历
        boolean[][] used = new boolean[m][n];

        // 以不同的起点开始向下递归
        boolean res = false;
        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (board[i][j] != word.charAt(0))
                    continue;  // 剪枝
                // 填充used数组, 完成重置与初始化
                initUsed(used, m);
                used[i][j] = true;
                res = backtrace(board, new StringBuilder(board[i][j] + ""), used, word, i, j, m, n);
                if (res)
                    return res; // true
            }
        }
        return false;
    }

    public boolean backtrace(char[][] board, StringBuilder sb, boolean[][] used, String target, int row, int col, int m, int n){
        // 每次有条件地添加元素，最终只要长度达标就ok
        if (sb.length() == target.length()){
            return true;
        }

        // 尝试四个方向
        for (int i = 0; i < 4; ++i){
            int tmp_row = row + directions[i][0], tmp_col = col + directions[i][1];
            int idx = sb.length();
            // check condition for tmp_row and tmp_col
            if (tmp_row >= 0 && tmp_row < m && tmp_col >= 0 && tmp_col < n && !used[tmp_row][tmp_col] && board[tmp_row][tmp_col] == target.charAt(idx)){
                used[tmp_row][tmp_col] = true;
                // 加入向后选择的元素 
                sb.append(board[tmp_row][tmp_col] + "");
                boolean res = backtrace(board, sb, used, target, tmp_row, tmp_col, m, n);
                if (res){
                    return true;
                } else{
                    // 此时需要回退，向另一个方向寻找出路
                    sb.deleteCharAt(sb.length() - 1);
                    used[tmp_row][tmp_col] = false;
                }
            }
        }

        // 四个方向都无法进行尝试
        return false;
    }
}
