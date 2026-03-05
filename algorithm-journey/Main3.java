class Solution{
    // 前序遍历 栈实现
    public void preorderTraversal(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode node = root;
        while (!q.isEmpty() || node != null) {
            while (node != null) {
                System.out.println(node.val);
                q.offer(node);
                node = node.left;
            }

            node = q.poll();
            node = node.right;
        }
    }

    // 中序遍历 栈实现
    public void inorderTraversal(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode node = root;

        while (!q.isEmpty() || node != null) {
            while (node != null) {
                q.offer(node);
                node = node.left;
            }

            node = q.poll();
            System.out.println(node.val);
            node = node.right;
        }

    }

    // 后序遍历 一个栈实现
    public void postorderTraversal(TreeNode root){
        Deque<TreeNode> q = new ArrayDeque<>();
        TreeNode node = root;

        TreeNode prev = null;

        while (node != null || !q.isEmpty()) {
            while (node != null) {
                q.push(node);
                node = node.left;
            }

            TreeNode peek = q.peek();
            // 无右子树或者右子树已经刚刚访问过了
            if (peek.right == null || peek.right == prev){
                System.out.println(peek.val);
                q.pop();
                prev = peek;
            } else{
                // 右子树并没有被访问
                node = peek.right;
            }
            
        }
    } 
}
