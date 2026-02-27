class Solution {
    // 矩阵置0
    // 方法一: 使用标记数组
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for ( int i = 0 ; i < m; ++i){
            for ( int j = 0 ; j < n; ++j){
                if (matrix[i][j] == 0){
                    row[i] = col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; ++i)
            if (row[i] == true){
                int tmp = i;
                IntStream.range(0, n).forEach(v -> matrix[tmp][v] = 0); 
            }
        
        
        for (int i = 0; i < n; ++i)
            if (col[i] == true){
                int tmp = i;
                IntStream.range(0, m).forEach(v -> matrix[v][tmp] = 0); 
            }

    }

    // 方法二: 使用两个标记变量
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false, flagRow0 = false;

        for (int i = 0; i < m; ++i){
            if (matrix[i][0] == 0)
                flagCol0 = true;
        }

        for (int j = 0; j < n; ++j){
            if (matrix[0][j] == 0)
                flagRow0 = true;
        }

        for (int i = 1; i < m; ++i){
            for (int j = 1; j < n; ++j){
                if (matrix[i][j] == 0)
                    matrix[0][j] = matrix[i][0] = 0;
            }
        }


        for (int i = 1; i < m; ++i){
            for (int j = 1; j < n; ++j){
                if (matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if (flagRow0){
            for (int i = 0; i < n; i++)
                matrix[0][i] = 0;
        }

        if (flagCol0){
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }
    }
    
}
