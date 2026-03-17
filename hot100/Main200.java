class Solution {
    // 岛屿问题
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] isTraverse = new boolean[m][n];
        for (int i = 0; i < m; ++i){
            Arrays.fill(isTraverse[i], false);
        }

        int sum = 0;
        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (grid[i][j] == '1' && !isTraverse[i][j]){
                    ++sum;
                    dfs(grid, isTraverse, i, j);
                }
            }
        }
        return sum;
    }

    public void dfs(char[][] grid, boolean[][] isTraverse, int r, int c){
        int m = grid.length, n = grid[0].length;
        // 终止 被访问过，超过界限
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '0' || isTraverse[r][c]){
            return ;
        }

        isTraverse[r][c] = true;
        // 四个方向
        dfs(grid, isTraverse, r, c - 1);
        dfs(grid, isTraverse, r - 1, c);
        dfs(grid, isTraverse, r, c + 1);
        dfs(grid, isTraverse, r + 1, c);
    }
}
