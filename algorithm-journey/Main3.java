class Solution{
    // Method1: 前序遍历 递归
    public void preorderTraversal2(TreeNode root){
        if (root == null)
            return ;
        System.out.println(root.val);
        preorderTraversal2(root.left);
        preorderTraversal2(root.right);
    }
    // 前序遍历 栈实现
    public void preorderTraversal(TreeNode root){
        Deque<TreeNode> s = new ArrayDeque<>();
        TreeNode node = root;
        while (!s.isEmpty() || node != null) {
            while (node != null) {
                System.out.println(node.val);
                s.push(node);
                node = node.left;
            }

            node = s.pop();
            node = node.right;
        }
    }

    // Method2: 中序遍历 递归实现
    public void inorderTraversal2(TreeNode root){
        if (root == null)
            return ;
        inorderTraversal2(root.left);
        System.out.println(root.val);
        inorderTraversal2(root.right);
    }

    // 中序遍历 栈实现
    public void inorderTraversal(TreeNode root){
        Deque<TreeNode> s = new ArrayDeque<>();
        TreeNode node = root;

        while (!s.isEmpty() || node != null) {
            while (node != null) {
                s.offer(node);
                node = node.left;
            }

            node = s.poll();
            System.out.println(node.val);
            node = node.right;
        }

    }

    // Method3: 后序遍历 递归实现
    public void postorderTraversal2(TreeNode root){
        if (root == null)
            return;
        postorderTraversal2(root.left);
        postorderTraversal2(root.right);
        System.out.println(root.val);
    }

    // 后序遍历 一个栈实现
    // 用栈的后序遍历可以寻找到达一个节点的具体路径
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

    // Method4: 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        return Math.max(leftMax, rightMax) + 1;
    }

    // Method5: 翻转二叉树
    public TreeNode invertTree(TreeNode root){
        if (root == null)
            return null;
        // 交换左右子树
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // Method6: 对称二叉树
    // 1. 向下递归，左子树左对右子树右，左子树右对右子树左
    public boolean dfs(TreeNode leftRoot, TreeNode rightRoot){
        if (leftRoot == null && rightRoot == null)
            return true;

        if (leftRoot == null || rightRoot == null)
            return false;

        return dfs(leftRoot.left, rightRoot.right) && dfs(leftRoot.right, rightRoot.left) && leftRoot.val == rightRoot.val;
    }

    public boolean isSymmetric(TreeNode root){
        if (root == null)
            return true;
        return dfs(root.left, root.right);
    }

    // 2. 队列
    public boolean isSymmetric1(TreeNode root){
        if (root == null)
            return true;

        if (root.left == null && root.right == null)
            return true;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);

        while (!q.isEmpty()) {
            TreeNode n1 = q.poll(), n2 = q.poll();
            // 判断
            if (n1 == null && n2 == null)
                continue;

            if ((n1 == null || n2 == null) || (n1.val != n2.val)){
                return false;
            }

            q.offer(n1.left);
            q.offer(n2.right);
            q.offer(n1.right);
            q.offer(n2.left);
        }

        return true;
    }
}
