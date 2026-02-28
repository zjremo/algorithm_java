class Solution {
    // 方向枚举类，定义四个方向的坐标变化
    private enum Direction {
        RIGHT(0, 1),    // 向右：行不变，列+1
        DOWN(1, 0),     // 向下：行+1，列不变
        LEFT(0, -1),    // 向左：行不变，列-1
        UP(-1, 0);      // 向上：行-1，列不变

        private final int deltaX;  // 行方向的变化量
        private final int deltaY;  // 列方向的变化量

        Direction(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        public int getDeltaX() {
            return deltaX;
        }

        public int getDeltaY() {
            return deltaY;
        }
    }

    // 螺旋矩阵
    // 方法一: 模拟，自己写的纯暴力版本
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 设置二维数组来设置是否被遍历过
        boolean[][] isSearch = new boolean[m][n];

        IntStream.range(0, m).forEach(i -> Arrays.fill(isSearch[i], false));

        Direction lastd = Direction.RIGHT;
        int r = 0, c = 0;

        List<Integer> list = new ArrayList<>();
        Map<Direction, Direction> map = new HashMap<>();
        map.put(Direction.RIGHT, Direction.DOWN);
        map.put(Direction.DOWN, Direction.LEFT);
        map.put(Direction.LEFT, Direction.UP);
        map.put(Direction.UP, Direction.RIGHT);

        boolean isExit = false;
        while (!isExit){
            list.add(matrix[r][c]);
            isSearch[r][c] = true;

            // 确定方向
            Direction tmp = lastd;
            isExit = true;
            do{
                int tmp_r = r + tmp.getDeltaX(), tmp_c = c + tmp.getDeltaY();

                if (tmp_r < m && tmp_r >= 0 && tmp_c < n && tmp_c >= 0 && !isSearch[tmp_r][tmp_c]){
                    r = tmp_r;
                    c = tmp_c;
                    isExit = false;
                    break;
                }

                tmp = map.get(tmp);
                    
            } while(tmp != lastd);

            if (!isExit)
                lastd = tmp;
        }
        return list;
    }

    // 方法二: 优化后的模拟
    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> list =  new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return list;

        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int total = m * n;

        int r = 0, c = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int lastd = 0;

        for (int i = 0; i < total; ++i){
            list.add(matrix[r][c]);
            visited[r][c] = true;

            int tmp_r = r + directions[lastd][0], tmp_c = c + directions[lastd][1];
            if (tmp_r < 0 || tmp_r >= m || tmp_c < 0 || tmp_c >= n || visited[tmp_r][tmp_c]){
                lastd = (lastd + 1) % 4;
            }
            r += directions[lastd][0];
            c += directions[lastd][1];
        }
        
        return list;
    }

    // 方法三: 按圈来进行模拟，每次就是顺时针转一圈，然后缩小一圈
    public List<Integer> spiralOrder2(int[][] matrix) {

        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return list;

        int m = matrix.length, n = matrix[0].length;

        int top = 0, left = 0, bottom = m - 1, right = n - 1;

        while (left <= right && top <= bottom){
            // 四重循环来模拟顺时针转一圈
            for (int i = left; i <= right; ++i)
                list.add(matrix[top][i]);

            for (int i = top + 1; i <= bottom; ++i)
                list.add(matrix[i][right]);

            if (left < right && top < bottom){
                for (int i = right - 1; i > left; --i)
                    list.add(matrix[bottom][i]);

                for (int i = bottom; i > top; --i)
                    list.add(matrix[i][left]);
            }

            ++left;
            --right;
            ++top;
            --bottom;
        }
        return list;
        
    }
}
