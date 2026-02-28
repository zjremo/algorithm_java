class Solution {
    // 方法一: 每一行都二分查找
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row: matrix){
            int idx = bisection(row, target);
            if (idx >= 0){
                return true;
            }
        }
        return false;
    }

    // 二分查找
    public int bisection(int[] arr, int target){
        int l = 0, r = arr.length - 1;
        while (l <= r){
            int m = l + ((r - l) >> 1);
            int num = arr[m];
            if (num == target)
                return m;
            else if (num > target)
                r = m - 1;
            else
                l = m + 1;
        }
        return -1;
    }

    // 方法二: z字型查找
    // 其实类似一种贪心的思想，以一种方式从边角点走完这个矩阵
    // 考虑四个点，左上顶点的左方向和下方向全部是递增的，如果自身比target大就堵死了，右下顶点类似
    // 我们只能选择右上顶点和左下顶点为起始点
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 选择左下顶点为起始点
        // int i = m - 1, j = 0;
        // 选择右上顶点为起始点
        int i = 0, j = n - 1;

        // while (i >= 0 && j < n) {
        //     if (matrix[i][j] == target) return true;
        //     else if (matrix[i][j] > target) --i;
        //     else ++j;
        // }
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] > target) --i;
            else ++j;
        }
        return false;
    }

}
