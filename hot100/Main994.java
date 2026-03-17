class Solution {
    record Point(int x, int y){}

    // 腐烂的橘子
    public int orangesRotting(int[][] grid) {
        Queue<Point> q = new LinkedList<>();

        int m = grid.length, n = grid[0].length;
        
        int steps = -1;
        boolean[][] isTraverse = initTraverse(m, n);
        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (grid[i][j] == 2){
                    isTraverse[i][j] = true;
                    q.offer(new Point(i, j));
                }
            }
        }


        while (!q.isEmpty()) {
            int size = q.size();
            ++steps;
            for (int i = 0; i < size; ++i){
                Point tmp = q.poll();
                int tmp_r = tmp.x(), tmp_c = tmp.y();
                // 传播
                process(q, tmp_r, tmp_c, m, n, isTraverse, grid);
            }
        }

        // 检查此时是否还有没有腐烂的橘子
        boolean isSuccess = true;
        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (grid[i][j] == 1 && !isTraverse[i][j]){
                    isSuccess = false;
                    break;
                }
            }
        }

        return isSuccess? (steps == -1?0 : steps) : -1;

    }

    public boolean[][] initTraverse(int m, int n){
        boolean[][] isTraverse = new boolean[m][n];
        for (int i = 0; i < m; ++i){
            Arrays.fill(isTraverse[i], false);
        }
        
        return isTraverse;
    }

    public void process(Queue<Point> q, int r, int c, int m, int n, boolean[][] isTraverse, int[][] grid){
        if (c - 1 >= 0 && !isTraverse[r][c - 1] && grid[r][c - 1] != 0){
            q.offer(new Point(r, c - 1));
            isTraverse[r][c - 1] = true;
        }

        if (r - 1 >= 0 && !isTraverse[r - 1][c] && grid[r - 1][c] != 0){
            q.offer(new Point(r - 1, c));
            isTraverse[r - 1][c] = true;
        }

        if (c + 1 < n && !isTraverse[r][c + 1] && grid[r][c + 1] != 0){
            q.offer(new Point(r, c + 1));
            isTraverse[r][c + 1] = true;
        }

        if (r + 1 < m && !isTraverse[r + 1][c] && grid[r + 1][c] != 0){
            q.offer(new Point(r + 1, c));
            isTraverse[r + 1][c] = true;
        }
    }

}
