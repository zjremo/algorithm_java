class Solution {
    private Map<TreeNode, TreeNode> map = new HashMap<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        dfs(root);
        Set<TreeNode> set = new HashSet<>();
        // 先吐一个出来，存到set里面
        while (p != null){
            set.add(p);
            p = map.getOrDefault(p, null);
        }

        while (q != null){
            if (set.contains(q)){
                return q;
            }
            q = map.getOrDefault(q, null);
        }
        return null;
    }

    public void dfs(TreeNode root){
        if (root == null)
            return ;

        if (root.left != null)
            map.put(root.left, root);
        if (root.right != null)
            map.put(root.right, root);
        dfs(root.left);
        dfs(root.right);
    }


    private int[][] directions = new int[][]{
        {0, -1}, {-1, 0}, {0, 1}, {1, 0}
    };

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] traverse = new boolean[m][n];

        int cnt = 0;
        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (grid[i][j] == '1' && !traverse[i][j]){
                    ++cnt;
                    traverse[i][j] = true;
                    dfs(grid, traverse, i, j);
                }
            }
        }

        return cnt;
    }

    public void dfs(char[][] grid, boolean[][] traverse, int r, int c){
        // 尝试四个方向
        int m = grid.length, n = grid[0].length;
        int trys = directions.length;
        for (int i = 0; i < trys; ++i){
            int tmp_r = r + directions[i][0], tmp_c = c + directions[i][1];
            if (tmp_r >= 0 && tmp_r < m && tmp_c >= 0 && tmp_c < n && grid[tmp_r][tmp_c] == '1' && !traverse[tmp_r][tmp_c]){
                traverse[tmp_r][tmp_c] = true;
                dfs(grid, traverse, tmp_r, tmp_c);
            }
        }
    }

    private record Point(int r, int c){

    }

    private int[][] directions = new int[][]{
        {0, -1}, {-1, 0}, {0, 1}, {1, 0}
    };

    public int orangesRotting(int[][] grid) {
        // 找到所有腐烂的橘子，全部入队列
        int m = grid.length, n = grid[0].length;
        Queue<Point> q = new LinkedList<>();

        // 新鲜橘子计数
        int freshCnt = 0;
        for(int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (grid[i][j] == 2){
                    q.offer(new Point(i, j));
                } else if (grid[i][j] == 1){
                    ++freshCnt;
                }
            }
        }

        int steps = 0;
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; ++i){
                Point p = q.poll();
                int r = p.r(), c = p.c();
                // 尝试四个方向
                for (int j = 0; j < 4; ++j){
                    int tmp_r = r + directions[j][0], tmp_c = c + directions[j][1];
                    if (tmp_r >= 0 && tmp_r < m && tmp_c >= 0 && tmp_c < n && grid[tmp_r][tmp_c] == 1){
                        grid[tmp_r][tmp_c] = 2;
                        --freshCnt;
                        q.offer(new Point(tmp_r, tmp_c));
                    }
                }
            }

            if (q.size() != 0) // 这一轮有新鲜橘子感染
                ++steps;
        }

        if(freshCnt > 0)
            return -1;
        
        return steps;
    }

    // [1] -> [0]
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 不能有环，拓扑排序
        int edges = prerequisites.length;
        // 入度统计数组
        int[] enterCnt = new int[numCourses];
        // 边统计Map
        Map<Integer, List<Integer>> map = new HashMap<>();

        // 未遍历过的节点数
        int pointsNotTraverse = numCourses;

        for (int i = 0; i < edges; ++i){
            enterCnt[prerequisites[i][0]] += 1;
            List<Integer> list = map.getOrDefault(prerequisites[i][1], new ArrayList<>());
            list.add(prerequisites[i][0]);
            map.put(prerequisites[i][1], list);
        }

        Queue<Integer> queue = new LinkedList<>();

        // 入度为0的入队列
        for (int i = 0; i < numCourses; ++i){
            if (enterCnt[i] == 0){
                queue.offer(i);
                --pointsNotTraverse;
            }
        }

        // 要遍历完所有的点
        while (!queue.isEmpty()){
            int p = queue.poll();

            List<Integer> p_edges = map.getOrDefault(p, new ArrayList<>());
            for (int p2: p_edges){
                --enterCnt[p2];
                // 此时变化是否有入度为0的
                if (enterCnt[p2] == 0){
                    queue.offer(p2);
                    --pointsNotTraverse;
                }
            }
        }

        if (pointsNotTraverse != 0)
            return false;
        return true;
    }

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return res;
        boolean[] istraverse = new boolean[n];
        backtrace(nums, new ArrayList<>(), istraverse);
        return res;
    }

    public void backtrace(int[] nums, List<Integer> path, boolean[] istraverse){
        if (path.size() == nums.length){
            res.add(new ArrayList<>(path));
        }

        for (int i = 0; i < nums.length; ++i){
            if (!istraverse[i]){
                // 此时可以使用
                istraverse[i] = true;
                path.add(nums[i]);
                backtrace(nums, path, istraverse);
                // 清理现场
                istraverse[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}

class Trie {
    private Trie[] next;
    private int count;
    private int prefix;

    public Trie() {
        prefix = 0;
        count = 0;
        next = new Trie[26];
    }
    
    public void insert(String word) {
        char[] array = word.toCharArray();
        Trie root = this;
        for (char c: array){
            if (root.next[c - 'a'] == null){
                root.next[c - 'a'] = new Trie();
            }

            root = root.next[c - 'a'];
            ++root.prefix;
        }
        ++root.count;
    }
    
    public boolean search(String word) {
        char[] array = word.toCharArray();
        Trie root = this;

        for (char c: array){
            if (root.next[c - 'a'] == null || root.next[c - 'a'].prefix == 0)
                return false;
            root = root.next[c - 'a'];
        }

        return root.count == 0? false:true;
    }
    
    public boolean startsWith(String prefix) {
        char[] array = prefix.toCharArray();
        Trie root = this;

        for (char c: array){
            if (root.next[c - 'a'] == null || root.next[c - 'a'].prefix == 0)
                return false;
            root = root.next[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

