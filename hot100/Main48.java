class Solution {
    // 旋转图像
    // 直接采取翻转法来做
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 水平翻转
        int lr = 0, fr = n - 1;

        while (lr < fr){
            for (int i = 0; i < n; ++i){
                matrix[lr][i] ^= matrix[fr][i];
                matrix[fr][i] ^= matrix[lr][i];
                matrix[lr][i] ^= matrix[fr][i];
            }

            ++lr;
            --fr;
        }

        // 主对角线翻转
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < i; ++j){
                matrix[i][j] ^= matrix[j][i];
                matrix[j][i] ^= matrix[i][j];
                matrix[i][j] ^= matrix[j][i];
            }
        }

    }

}
