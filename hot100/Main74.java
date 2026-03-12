class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 定位可能所在的行
        int m = matrix.length, n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]){
            return false;
        }

        // 每行都进行判断，然后二分查找
        for (int i = 0; i < m; ++i){
            if (target < matrix[i][0] || target > matrix[i][n - 1]){
                continue;
            }
            if (target == searchInsert(matrix, target, n, i)){
                return true;
            }
        }

        return false;
    }

    public int searchInsert(int[][] matrix, int target, int cols, int row){
        int l = 0, r = cols - 1, ans = 0;
        while (l <= r){
            int mid = l + ((r - l) >> 1);
            if (target <= matrix[row][mid]){
                ans = mid;
                r = mid - 1;
            } else{
                l = mid + 1;
            }
        }
        return matrix[row][ans];
    }
}
