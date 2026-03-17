class Solution {
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    // 课程表
    // 检查图不能有环
    // dfs向下递归寻找有无环出现
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i){
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] edge: prerequisites){
            edges.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i){
            if (visited[i] == 0)
                dfs(i);
        }
        return valid;
    }

    public void dfs(int u){
        visited[u] = 1;
        for (int v: edges.get(u)){
            if (visited[v] == 0){
                dfs(v);
                if (!valid){
                    return;
                }
            } else if (visited[v] == 1){
                valid = false;
                return ;
            }
        }
        visited[u] = 2; // 此时代表成功
    }
    
    List<List<Integer>> edges; // 存储边
    int[] indeg; // 存储入度
    // 队列来完成，每次将入度为0的放进去，最后检查是否还有节点没有遍历到
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i){
            edges.add(new ArrayList<>());
        }
        indeg = new int[numCourses];
        for (int[] edge: prerequisites){
            edges.get(edge[1]).add(edge[0]);
            ++indeg[edge[0]];
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i){
            if (indeg[i] == 0)
                q.offer(i); // 此时入队列
        }

        int visited = 0; // 统计个数
        while (!q.isEmpty()){
            ++visited;
            int val = q.poll();
            for (int edge: edges.get(val)){
                --indeg[edge];
                if (indeg[edge] == 0){
                    q.offer(edge);
                }
            }
        }
        return visited == numCourses;

    }

}
